package com.kshirsagar.videorecorder.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.kshirsagar.videorecorder.BuildConfig;
import com.kshirsagar.videorecorder.R;
import com.kshirsagar.videorecorder.activity.manager.TapToFocusManager;
import com.kshirsagar.videorecorder.activity.params.ActivityThemeParams;
import com.kshirsagar.videorecorder.activity.params.FFmpegPreviewActivityParams;
import com.kshirsagar.videorecorder.activity.params.FFmpegRecorderActivityParams;
import com.kshirsagar.videorecorder.activity.params.InteractionParamsI;
import com.kshirsagar.videorecorder.activity.params.RecorderActivityThemeParamsI;
import com.kshirsagar.videorecorder.camera.CameraController;
import com.kshirsagar.videorecorder.camera.CameraControllerI;
import com.kshirsagar.videorecorder.recorder.FFmpegFrameRecorder;
import com.kshirsagar.videorecorder.recorder.MediaClipsRecorder;
import com.kshirsagar.videorecorder.recorder.VideoTransformerTask;
import com.kshirsagar.videorecorder.recorder.params.CameraParams;
import com.kshirsagar.videorecorder.recorder.params.RecorderParamsI;
import com.kshirsagar.videorecorder.ui.CameraPreviewView;
import com.kshirsagar.videorecorder.ui.CameraTapAreaView;
import com.kshirsagar.videorecorder.ui.ProgressSectionsView;
import com.kshirsagar.videorecorder.ui.ViewUtil;
import com.kshirsagar.videorecorder.util.Util;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity for recording audio and video
 */
public class FFmpegRecorderActivity extends AbstractDynamicStyledActivity implements
        OnClickListener,
        OnTouchListener,
        CameraControllerI.CameraListener,
        MediaClipsRecorder.MediaClipsRecorderListener,
        MediaClipsRecorder.MediaRecorderConfigurer,
        ProgressSectionsView.ProgressSectionsProvider,
        SurfaceHolder.Callback {

    public static final String REQUEST_PARAMS_KEY =
            BuildConfig.APPLICATION_ID + ".FFmpegRecorderActivityParams";

    public static final int RESULT_ERROR = RESULT_FIRST_USER;
    public static final String RESULT_ERROR_PATH_KEY = "error";
    public static final String RESULT_THUMBNAIL_URI_KEY = "thumbnail";

    protected static final String LOG_TAG = "FFmpegRecorderActivity";
    protected static final int PREVIEW_ACTIVITY_RESULT = 10000;
    protected static final int FOCUS_WEIGHT = 1000;

    // User params
    FFmpegRecorderActivityParams mParams;

    // UI variables
    protected ProgressSectionsView mProgressView;
    protected CameraPreviewView mCameraPreviewView;
    protected CameraTapAreaView mTapToFocusView;
    protected AppCompatImageButton mRecordButton;
    protected AppCompatImageButton mFlashButton;
    protected AppCompatImageButton mSwitchCameraButton;
    protected CircularProgressView mProgressBar;
    protected TextView mProgressText;
    protected AppCompatImageButton mNextButton;

    // State variables
    protected ActivityOrientationEventListener mOrientationEventListener;
    protected TapToFocusManager mFocusManager;
    protected int mContextOrientation;
    protected int mOpenCameraOrientationDegrees;
    protected MediaClipsRecorder mMediaClipsRecorder;
    protected CameraControllerI mCameraController;
    protected int mOriginalRequestedOrientation;
    protected OpenCameraTask mOpenCameraTask;
    protected SaveVideoTask mSaveVideoTask;
    protected File mVideoOutputFile;
    protected File mVideoThumbnailOutputFile;
    private SeekBar seekBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!extractIntentParams()) {
            return;
        }
        layoutView();
        setupToolbar((Toolbar) findViewById(R.id.toolbar));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        InteractionParamsI interactionParams =  getInteractionParams();
        RecorderActivityThemeParamsI themeParams = getThemeParams();
        mProgressView = (ProgressSectionsView) findViewById(R.id.recorder_progress);
        mProgressView.setMinProgress(interactionParams.getMinRecordingMillis());
        mProgressView.setMaxProgress(interactionParams.getMaxRecordingMillis());
        mProgressView.setProgressColor(themeParams.getProgressColor());
        mProgressView.setCursorColor(themeParams.getProgressCursorColor());
        mProgressView.setMinProgressColor(themeParams.getProgressMinProgressColor());
        mProgressView.setProvider(this);

        // TODO: Support Camera 2
        mCameraController = new CameraController(FFmpegRecorderActivity.this);
        mCameraController.addListener(this);

        mCameraPreviewView = (CameraPreviewView) findViewById(R.id.recorder_view);
        mCameraPreviewView.getHolder().addCallback(this);
        mCameraPreviewView.setParams(mParams.getRecorderParams());
        mCameraPreviewView.setVisibility(View.INVISIBLE);

        mTapToFocusView = (CameraTapAreaView) findViewById(R.id.tap_to_focus_view);
        mTapToFocusView.setTapSizePercent(getInteractionParams().getTapToFocusSizePercent());

        mFocusManager = new TapToFocusManager(mCameraController, mCameraPreviewView, mTapToFocusView,
                FOCUS_WEIGHT, mParams.getInteractionParams().getTapToFocusHoldTimeMillis());

        mRecordButton = (AppCompatImageButton) findViewById(R.id.record_button);
        mRecordButton.setOnTouchListener(FFmpegRecorderActivity.this);

        mSwitchCameraButton = (AppCompatImageButton) findViewById(R.id.switch_camera_button);
        mSwitchCameraButton.setOnClickListener(FFmpegRecorderActivity.this);
        mSwitchCameraButton.setColorFilter(themeParams.getWidgetColor());

        mFlashButton = (AppCompatImageButton) findViewById(R.id.flash_button);
        mFlashButton.setOnClickListener(this);
        mFlashButton.setColorFilter(themeParams.getWidgetColor());

        mProgressBar = (CircularProgressView) findViewById(R.id.progress_bar) ;
        mProgressBar.setColor(themeParams.getProgressColor());
        mProgressText = (TextView) findViewById(R.id.progress_text) ;
    
        mNextButton = (AppCompatImageButton) findViewById(R.id.next_button);
        mNextButton.setColorFilter(themeParams.getToolbarWidgetColor());
        mNextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaClipsRecorder.getRecordedMillis() > 0) {
                    saveRecording();
                }
            }
        });

        mOrientationEventListener = new ActivityOrientationEventListener();

        mOriginalRequestedOrientation = getRequestedOrientation();



        seekBar.incrementProgressBy(1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                //Set the system brightness using the brightness variable value  
               
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //Nothing handled here  
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                float decimalProgress = (float) progress;
                mCameraController.setExposure(decimalProgress,0);
                Toast.makeText(FFmpegRecorderActivity.this, "Camera Brightness changed...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected boolean extractIntentParams() {
        mParams = (FFmpegRecorderActivityParams)
                getIntent().getSerializableExtra(REQUEST_PARAMS_KEY);
        if (mParams == null) {
            onError(new InvalidParameterException(
                    "Intent did not have FFmpegRecorderActivity.REQUEST_PARAMS_KEY set."));
            return false;
        }
        return true;
    }

    protected InteractionParamsI getInteractionParams() {
        return mParams.getInteractionParams();
    }

    protected RecorderParamsI getRecorderParams() {
        return mParams.getRecorderParams();
    }

    @Override
    protected RecorderActivityThemeParamsI getThemeParams() {
        return mParams.getThemeParams();
    }

    @Override
    protected void layoutView() {
        setContentView(R.layout.activity_ffmpeg_recorder);
    }

    @Override
    protected void setupToolbar(Toolbar toolbar) {
        Drawable navButtonDrawable =
                ContextCompat.getDrawable(this, R.drawable.ic_close_white_24dp);
        toolbar.setNavigationIcon(ViewUtil.tintDrawable(
                navButtonDrawable, getThemeParams().getToolbarWidgetColor()));
        super.setupToolbar(toolbar);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    protected void initRecorders() {
        mProgressView.setVisibility(View.GONE);
        mNextButton.setVisibility(View.INVISIBLE);

        mMediaClipsRecorder = new MediaClipsRecorder(this, getCacheDir());
        mMediaClipsRecorder.setMediaCLipstRecorderListener(this);

        setRequestedOrientation(mOriginalRequestedOrientation);

        openCamera(getRecorderParams().getVideoCameraFacing());
    }

    @Override
    public void configureMediaRecorder(MediaRecorder recorder) {
        Log.v(LOG_TAG, String.format("Remaining millis %d",
                getInteractionParams().getMaxRecordingMillis()
                        - mMediaClipsRecorder.getRecordedMillis()));
        // Camera must be set first
        mCameraController.setMediaRecorder(recorder);
        // Then other config
        Util.setMediaRecorderEncoderParams(recorder, getRecorderParams());
        Util.setMediaRecorderInteractionParams(recorder, getInteractionParams(),
                (int) mMediaClipsRecorder.getRecordedMillis(), mMediaClipsRecorder.getRecordedBytes());
        Util.setMediaRecorderCameraParams(recorder, mCameraController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mOrientationEventListener.enable();
        initRecorders();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PREVIEW_ACTIVITY_RESULT:
                switch (resultCode) {
                    case RESULT_OK:
                        previewAccepted();
                        break;
                    case RESULT_CANCELED:
                    default:
                        discardRecording();
                        initRecorders();
                        supportInvalidateOptionsMenu();
                        break;
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mMediaClipsRecorder.getRecordedMillis() > 0) {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle(R.string.are_you_sure)
                    .setMessage(R.string.discard_video_msg)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            discardRecordingAndFinish();
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        } else {
            discardRecordingAndFinish();
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopRecording();
        mMediaClipsRecorder.deleteClips();
        mCameraController.closeCamera();
        releaseResources();
        mOrientationEventListener.disable();
    }

    protected void openCamera(CameraControllerI.Facing facing) {
        if (mOpenCameraTask != null) {
            return;
        }

        Log.d(LOG_TAG, String.format("Opening camera facing %s", facing));
        showProgress(R.string.initializing);
        mCameraPreviewView.setPreviewSize(null);
        mOpenCameraOrientationDegrees = mOrientationEventListener.mOrientationDegrees;
        mOpenCameraTask = new OpenCameraTask();
        mOpenCameraTask.execute(Preconditions.checkNotNull(facing));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setCameraPreviewDisplayIfReady();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}

    @Override
    public void onCameraOpen() {
        mCameraPreviewView.setPreviewSize(mCameraController.getPreviewSize());
        setCameraPreviewDisplayIfReady();
        mCameraController.startPreview();
    }

    protected void setCameraPreviewDisplayIfReady() {
        if (mCameraController.isCameraOpen()) {
            try {
                mCameraController.setPreviewDisplay(mCameraPreviewView.getHolder());
            } catch (IOException exception) {
                Log.e(LOG_TAG, "Error setting camera preview display", exception);
            }
        }
    }

    @Override
    public void onCameraClose() {
        mCameraPreviewView.setPreviewSize(null);
    }

    @Override
    public void onCameraStartPreview() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideProgress();
                mCameraPreviewView.setVisibility(View.VISIBLE);
            }
        });
        Log.d(LOG_TAG, "Ready to record");
    }

    @Override
    public void onCameraStopPreview() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCameraPreviewView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onFlashModeChanged(CameraControllerI.FlashMode flashMode) {}

    @Override
    public void onCameraFocusOnRect(Rect rect) {}

    @Override
    public void onCameraAutoFocus() {}

    protected void showProgress(@StringRes int progressTextRes) {
        if (!mProgressBar.isIndeterminate()) {
            mProgressBar.setIndeterminate(true);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressText.setVisibility(View.VISIBLE);
        mProgressText.setText(progressTextRes);
        hideControls();
    }

    protected void hideUI() {
        mProgressView.setVisibility(View.GONE);
        hideControls();
    }

    protected void hideControls() {
        mRecordButton.setVisibility(View.INVISIBLE);
        mSwitchCameraButton.setVisibility(View.INVISIBLE);
        mFlashButton.setVisibility(View.INVISIBLE);
    }

    protected void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mProgressText.setVisibility(View.GONE);

        mProgressView.setVisibility(View.VISIBLE);
        mRecordButton.setVisibility(View.VISIBLE);
        if (mCameraController.getCameraCount() > 1) {
            mSwitchCameraButton.setVisibility(View.VISIBLE);
        }
        if (mCameraController.supportsFlashMode(CameraControllerI.FlashMode.ON)) {
            mFlashButton.setVisibility(View.VISIBLE);
        } else {
            mFlashButton.setVisibility(View.INVISIBLE);
        }
    }

    protected void startRecording() {
        synchronized (mCameraController) {
            Log.v(LOG_TAG, "Start recording");
            try {
                mCameraController.unlock();
            } catch (Throwable e) {
                Log.e(LOG_TAG, "Error unlocking the camera when starting recording");
            }
            mMediaClipsRecorder.start();
            mSwitchCameraButton.setVisibility(View.INVISIBLE);
            // Lock the orientation the first time we start recording if there is no request orientation

            if (mMediaClipsRecorder.getClips().isEmpty() && mOriginalRequestedOrientation == -1) {
                setRequestedOrientation(getResources().getConfiguration().orientation);
            }
            mFocusManager.cancelDelayedAutoFocus();
        }
    }

    @Override
    public boolean hasCurrentProgress() {
        return mMediaClipsRecorder.isRecording();
    }

    @Override
    public List<Integer> getProgressSections() {
        List<MediaClipsRecorder.Clip> clips = mMediaClipsRecorder.getClips();
        ArrayList<Integer> progressList = Lists.newArrayListWithCapacity(
                clips.size() + (mMediaClipsRecorder.isRecording() ? 1 : 0));
        long totalRecordedMillis = 0;
        for (MediaClipsRecorder.Clip clip : clips) {
            totalRecordedMillis += clip.getDurationMillis();
            progressList.add((int) clip.getDurationMillis());
        }
        if (mMediaClipsRecorder.isRecording()) {
            progressList.add((int) mMediaClipsRecorder.getCurrentRecordedTimeMillis());
        }
        if (mNextButton.getVisibility() == View.INVISIBLE) {
            long minRecordingMillis = getInteractionParams().getMinRecordingMillis();
            if (totalRecordedMillis > minRecordingMillis) {
                Log.v(LOG_TAG, "Reached min recording time");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNextButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        }
        return progressList;
    }

    @Override
    public void onMediaRecorderMaxDurationReached() {
        Log.d(LOG_TAG, "Max recording time reached");
        saveRecording();
    }

    @Override
    public void onMediaRecorderMaxFileSizeReached() {
        Log.d(LOG_TAG, "Max file size reached");
        saveRecording();
    }

    protected void stopRecordingAndPrepareForNext() {
        if (!mMediaClipsRecorder.isRecording()) {
            return;
        }
        stopRecording();
        mFocusManager.autoFocusAfterDelay();
    }

    protected void stopRecording() {
        synchronized (mCameraController) {
            if (mParams == null || !mMediaClipsRecorder.isRecording()) {
                return;
            }
            Log.v(LOG_TAG, "Stop recording");
            mMediaClipsRecorder.stop();
            try {
                mCameraController.lock();
            } catch (Throwable e) {
                Log.e(LOG_TAG, "Error locking the camera when stopping recording");
            }
            if (mSaveVideoTask == null && mCameraController.getCameraCount() > 1) {
                mSwitchCameraButton.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mSaveVideoTask == null && mCameraController.isCameraOpen()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startRecording();
                    break;
                case MotionEvent.ACTION_UP:
                    stopRecordingAndPrepareForNext();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float x = event.getX();
                    float y = event.getY();
                    if (x < 0 || x > v.getWidth() || y < 0 || y > v.getHeight()) {
                        stopRecordingAndPrepareForNext();
                    }
                    break;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.flash_button) {
            if (!mCameraController.isCameraOpen()) {
                return;
            }
            CameraControllerI.FlashMode flashMode;
            switch (mCameraController.getFlashMode()) {
                case ON:
                    flashMode = CameraControllerI.FlashMode.OFF;
                    break;
                case OFF:
                default:
                    flashMode = CameraControllerI.FlashMode.ON;
                    break;
            }
            updateCameraFlash(flashMode);
        } else if (v.getId() == R.id.switch_camera_button) {
            if (!mCameraController.isCameraOpen()) {
                return;
            }
            CameraControllerI.Facing cameraFacing =
                    mCameraController.getCameraFacing() == CameraControllerI.Facing.BACK
                            ? CameraControllerI.Facing.FRONT
                            : CameraControllerI.Facing.BACK;
            openCamera(cameraFacing);
        }
    }

    protected void updateCameraFlash(CameraControllerI.FlashMode flashMode) {
        CameraControllerI cameraManager = mCameraController;
        if (cameraManager.setFlashMode(flashMode)) {
            @DrawableRes int resId = flashMode == CameraControllerI.FlashMode.ON
                    ? R.drawable.ic_flash_on_white_36dp
                    : R.drawable.ic_flash_off_white_36dp;
            mFlashButton.setImageDrawable(ContextCompat.getDrawable(this, resId));
        }
    }

    @Override
    public void onMediaRecorderError(final Exception e) {
        Log.e(LOG_TAG, "Media recorder error", e);
        onError(e);
    }

    public void onError(final Exception e) {
        discardRecording();
        Intent intent = new Intent();
        intent.putExtra(RESULT_ERROR_PATH_KEY, e);
        setResult(RESULT_ERROR, intent);
        finish();
    }

    public void discardRecordingAndFinish() {
        discardRecording();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void finish() {
        if (mFocusManager != null) {
            mFocusManager.close();
        }
        super.finish();
    }

    public void discardRecording() {
        Log.i(LOG_TAG, "Discard recording");
        if (mParams == null) {
            return;
        }
        if (mSaveVideoTask != null) {
            mSaveVideoTask.cancel(true);
            mSaveVideoTask = null;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideUI();
            }
        });
        releaseResources();
        mMediaClipsRecorder.deleteClips();
        if (mVideoOutputFile != null && mVideoOutputFile.exists()) {
            mVideoOutputFile.delete();
            mVideoOutputFile = null;
        }
        if (mVideoThumbnailOutputFile != null && mVideoThumbnailOutputFile.exists()) {
            mVideoThumbnailOutputFile.delete();
            mVideoThumbnailOutputFile = null;
        }
    }

    protected void saveRecording() {
        stopRecording();
        if (mSaveVideoTask == null) {
            Log.i(LOG_TAG, "Saving recording");
            showProgress(R.string.preparing);

            // Start the task slightly delayed so that the UI can update first
            mSaveVideoTask = new SaveVideoTask();
            mSaveVideoTask.execute();
        }
    }

    protected void startPreviewActivity() {
        Log.i(LOG_TAG, "Saved recording. Starting preview");
        Intent previewIntent = new Intent(this, FFmpegPreviewActivity.class);
        previewIntent.putExtra(
                FFmpegPreviewActivity.REQUEST_PARAMS_KEY,
                FFmpegPreviewActivityParams.builder()
                    .setVideoFileUri(mVideoOutputFile)
                    .setThemeParams(ActivityThemeParams.Builder.mergeOnlyClass(
                            ActivityThemeParams.builder(), getThemeParams()).build())
                    .setConfirmation(true)
                    .build());
        startActivityForResult(previewIntent, PREVIEW_ACTIVITY_RESULT);
    }

    protected void previewAccepted() {
        Log.d(LOG_TAG, "Preview accepted");
        Intent resultIntent = new Intent();
        resultIntent.setData(Uri.fromFile(mVideoOutputFile));
        if (mVideoThumbnailOutputFile != null) {
            resultIntent.putExtra(RESULT_THUMBNAIL_URI_KEY, Uri.fromFile(mVideoThumbnailOutputFile));
        }
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    protected void releaseResources() {
        if (mParams == null) {
            return;
        }
        mMediaClipsRecorder.release();
        // Try to lock the camera if we can so that we can close it
        try {
            mCameraController.lock();
        } catch (Throwable expected) {}
        mCameraController.closeCamera();
    }

    protected class ActivityOrientationEventListener extends OrientationEventListener {
        protected int mOrientationDegrees;

        public ActivityOrientationEventListener() {
            super(FFmpegRecorderActivity.this);
        }

        @Override
        public void onOrientationChanged(int orientation)
        {
            // Check that the configuration orientation matches the size orientation to ensure
            // re-layouts have already happened.
            // Also check if the orientation degrees don't match the degrees when we opened the
            // camera.
            mOrientationDegrees = ViewUtil.getContextRotation(FFmpegRecorderActivity.this);
            boolean isDegreesLandscape = Util.isLandscapeAngle(mOrientationDegrees);
            View decorView = getWindow().getDecorView();
            boolean isSizeLandscape = decorView.getMeasuredWidth() > decorView.getMeasuredHeight();
            if (isDegreesLandscape == isSizeLandscape
                    && mOpenCameraOrientationDegrees != mOrientationDegrees
                    && mCameraController.isCameraOpen()) {
                openCamera(mCameraController.getCameraFacing());
            }
        }
    }

    protected class OpenCameraTask extends AsyncTask<CameraControllerI.Facing, Void, Exception> {

        @Override
        protected Exception doInBackground(CameraControllerI.Facing[] params) {
            try {
                CameraControllerI.Facing facing = Preconditions.checkNotNull(params[0]);
                CameraParams.Builder cameraParamsBuilder =
                        CameraParams.Builder.merge(CameraParams.builder(), getRecorderParams());
                if (getRecorderParams().getVideoCameraFacing() != facing) {
                    cameraParamsBuilder.setVideoCameraFacing(facing);
                }
                mCameraController
                        .openCamera(cameraParamsBuilder.build(), mOpenCameraOrientationDegrees);
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            mMediaClipsRecorder.setFacing(mCameraController.getCameraFacing());
            mMediaClipsRecorder.setViewOrientationDegrees(
                    mCameraController.getPreviewDisplayOrientationDegrees());
            mOpenCameraTask = null;
            if (e != null) {
                Log.e(LOG_TAG, "Error opening camera", e);
                onError(e);
            }
        }
    }

    protected class SaveVideoTask extends AsyncTask<Object, Object, Exception>
            implements VideoTransformerTask.TaskListener {

        private VideoTransformerTask mVideoTransformerTask;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            releaseResources();

            Log.d(LOG_TAG, "VideoRecorder length " + mMediaClipsRecorder.getRecordedMillis());
        }

        @Override
        protected Exception doInBackground(Object... params) {
            mVideoOutputFile = new File(Uri.parse(mParams.getVideoOutputFileUri()).getPath());

            mVideoThumbnailOutputFile = mParams.getVideoThumbnailOutputFileUri().isPresent()
                    ? new File(Uri.parse(mParams.getVideoThumbnailOutputFileUri().get()).getPath())
                    : null;

            FFmpegFrameRecorder recorder =
                    Util.createFrameRecorder(mVideoOutputFile, getRecorderParams());
            mVideoTransformerTask = new VideoTransformerTask(
                    recorder, getRecorderParams(), mMediaClipsRecorder.getClips());
            mVideoTransformerTask.setProgressListener(this);

            try {
                mVideoTransformerTask.run();
                mMediaClipsRecorder.deleteClips();
                publishProgress();
                // Create video thumbnail
                if (mVideoThumbnailOutputFile != null) {
                    MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
                    metadataRetriever.setDataSource(mVideoOutputFile.getAbsolutePath());
                    Bitmap frame = metadataRetriever.getFrameAtTime(
                            0, MediaMetadataRetriever.OPTION_NEXT_SYNC);
                    FileOutputStream outputStream = new FileOutputStream(mVideoThumbnailOutputFile);
                    frame.compress(CompressFormat.JPEG, 100, outputStream);
                }
                return null;
            } catch (Throwable e) {
                return (e instanceof Exception) ? (Exception) e : new RuntimeException(e);
            }
        }

        @Override
        public void onStart() {
            publishProgress(0f);
        }

        @Override
        public void onProgress(int progress, int total) {
            publishProgress(progress, total);
        }

        @Override
        public void onDone() {
            publishProgress(1f);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);

            if (values.length == 0) {
                if (!mProgressBar.isIndeterminate()) {
                    mProgressBar.setIndeterminate(true);
                    mProgressBar.startAnimation();
                    mProgressText.setText(R.string.saving_video);
                }
            } else {
                if (mProgressBar.isIndeterminate()) {
                    mProgressBar.setIndeterminate(false);
                }
                float progress = getFloatValue(values[0]);
                if (values.length > 1) {
                    progress /= getFloatValue(values[1]);
                }
                mProgressBar.setProgress(progress * mProgressBar.getMaxProgress());
                mProgressText.setText(
                        String.format(getString(R.string.encoding_percent), (int)(100 * progress)));
            }
        }

        protected float getFloatValue(Object o) {
            if (o instanceof Number) {
                return ((Number) o).floatValue();
            }
            throw new InvalidParameterException(String.format("Invalid progress value %s", o));
        }

        @Override
        protected void onPostExecute(Exception result) {
            super.onPostExecute(result);
            mVideoTransformerTask = null;

            if (result == null) {
                startPreviewActivity();
            } else {
                Log.e(LOG_TAG, "Error saving video", result);
                onError(result);
            }
        }
    }
}