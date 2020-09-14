
package com.kshirsagar.videorecorder.recorder.params;

import com.kshirsagar.videorecorder.camera.CameraControllerI;
import com.kshirsagar.videorecorder.recorder.common.ImageFit;
import com.kshirsagar.videorecorder.recorder.common.ImageScale;
import com.kshirsagar.videorecorder.recorder.common.ImageSize;
import com.google.common.base.Optional;

 final class AutoValue_RecorderParams extends RecorderParams {

  private final Optional<Integer> audioBitrate;
  private final Optional<Integer> audioChannelCount;
  private final EncoderParamsI.AudioCodec audioCodec;
  private final Optional<Integer> audioSamplingRateHz;
  private final CameraControllerI.Facing videoCameraFacing;
  private final Optional<Integer> videoFrameRate;
  private final ImageSize videoSize;
  private final ImageScale videoImageScale;
  private final ImageFit videoImageFit;
  private final boolean shouldCropVideo;
  private final boolean shouldPadVideo;
  private final Optional<Integer> videoBitrate;
  private final EncoderParamsI.VideoCodec videoCodec;
  private final EncoderParamsI.OutputFormat outputFormat;

  private AutoValue_RecorderParams(
      Optional<Integer> audioBitrate,
      Optional<Integer> audioChannelCount,
      EncoderParamsI.AudioCodec audioCodec,
      Optional<Integer> audioSamplingRateHz,
      CameraControllerI.Facing videoCameraFacing,
      Optional<Integer> videoFrameRate,
      ImageSize videoSize,
      ImageScale videoImageScale,
      ImageFit videoImageFit,
      boolean shouldCropVideo,
      boolean shouldPadVideo,
      Optional<Integer> videoBitrate,
      EncoderParamsI.VideoCodec videoCodec,
      EncoderParamsI.OutputFormat outputFormat) {
    this.audioBitrate = audioBitrate;
    this.audioChannelCount = audioChannelCount;
    this.audioCodec = audioCodec;
    this.audioSamplingRateHz = audioSamplingRateHz;
    this.videoCameraFacing = videoCameraFacing;
    this.videoFrameRate = videoFrameRate;
    this.videoSize = videoSize;
    this.videoImageScale = videoImageScale;
    this.videoImageFit = videoImageFit;
    this.shouldCropVideo = shouldCropVideo;
    this.shouldPadVideo = shouldPadVideo;
    this.videoBitrate = videoBitrate;
    this.videoCodec = videoCodec;
    this.outputFormat = outputFormat;
  }

  @Override
  public Optional<Integer> getAudioBitrate() {
    return audioBitrate;
  }

  @Override
  public Optional<Integer> getAudioChannelCount() {
    return audioChannelCount;
  }

  @Override
  public EncoderParamsI.AudioCodec getAudioCodec() {
    return audioCodec;
  }

  @Override
  public Optional<Integer> getAudioSamplingRateHz() {
    return audioSamplingRateHz;
  }

  @Override
  public CameraControllerI.Facing getVideoCameraFacing() {
    return videoCameraFacing;
  }

  @Override
  public Optional<Integer> getVideoFrameRate() {
    return videoFrameRate;
  }

  @Override
  public ImageSize getVideoSize() {
    return videoSize;
  }

  @Override
  public ImageScale getVideoImageScale() {
    return videoImageScale;
  }

  @Override
  public ImageFit getVideoImageFit() {
    return videoImageFit;
  }

  @Override
  public boolean getShouldCropVideo() {
    return shouldCropVideo;
  }

  @Override
  public boolean getShouldPadVideo() {
    return shouldPadVideo;
  }

  @Override
  public Optional<Integer> getVideoBitrate() {
    return videoBitrate;
  }

  @Override
  public EncoderParamsI.VideoCodec getVideoCodec() {
    return videoCodec;
  }

  @Override
  public EncoderParamsI.OutputFormat getOutputFormat() {
    return outputFormat;
  }

  @Override
  public String toString() {
    return "RecorderParams{"
        + "audioBitrate=" + audioBitrate + ", "
        + "audioChannelCount=" + audioChannelCount + ", "
        + "audioCodec=" + audioCodec + ", "
        + "audioSamplingRateHz=" + audioSamplingRateHz + ", "
        + "videoCameraFacing=" + videoCameraFacing + ", "
        + "videoFrameRate=" + videoFrameRate + ", "
        + "videoSize=" + videoSize + ", "
        + "videoImageScale=" + videoImageScale + ", "
        + "videoImageFit=" + videoImageFit + ", "
        + "shouldCropVideo=" + shouldCropVideo + ", "
        + "shouldPadVideo=" + shouldPadVideo + ", "
        + "videoBitrate=" + videoBitrate + ", "
        + "videoCodec=" + videoCodec + ", "
        + "outputFormat=" + outputFormat
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RecorderParams) {
      RecorderParams that = (RecorderParams) o;
      return (this.audioBitrate.equals(that.getAudioBitrate()))
           && (this.audioChannelCount.equals(that.getAudioChannelCount()))
           && (this.audioCodec.equals(that.getAudioCodec()))
           && (this.audioSamplingRateHz.equals(that.getAudioSamplingRateHz()))
           && (this.videoCameraFacing.equals(that.getVideoCameraFacing()))
           && (this.videoFrameRate.equals(that.getVideoFrameRate()))
           && (this.videoSize.equals(that.getVideoSize()))
           && (this.videoImageScale.equals(that.getVideoImageScale()))
           && (this.videoImageFit.equals(that.getVideoImageFit()))
           && (this.shouldCropVideo == that.getShouldCropVideo())
           && (this.shouldPadVideo == that.getShouldPadVideo())
           && (this.videoBitrate.equals(that.getVideoBitrate()))
           && (this.videoCodec.equals(that.getVideoCodec()))
           && (this.outputFormat.equals(that.getOutputFormat()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.audioBitrate.hashCode();
    h *= 1000003;
    h ^= this.audioChannelCount.hashCode();
    h *= 1000003;
    h ^= this.audioCodec.hashCode();
    h *= 1000003;
    h ^= this.audioSamplingRateHz.hashCode();
    h *= 1000003;
    h ^= this.videoCameraFacing.hashCode();
    h *= 1000003;
    h ^= this.videoFrameRate.hashCode();
    h *= 1000003;
    h ^= this.videoSize.hashCode();
    h *= 1000003;
    h ^= this.videoImageScale.hashCode();
    h *= 1000003;
    h ^= this.videoImageFit.hashCode();
    h *= 1000003;
    h ^= this.shouldCropVideo ? 1231 : 1237;
    h *= 1000003;
    h ^= this.shouldPadVideo ? 1231 : 1237;
    h *= 1000003;
    h ^= this.videoBitrate.hashCode();
    h *= 1000003;
    h ^= this.videoCodec.hashCode();
    h *= 1000003;
    h ^= this.outputFormat.hashCode();
    return h;
  }

  @Override
  public RecorderParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends RecorderParams.Builder {
    private Optional<Integer> audioBitrate = Optional.absent();
    private Optional<Integer> audioChannelCount = Optional.absent();
    private EncoderParamsI.AudioCodec audioCodec;
    private Optional<Integer> audioSamplingRateHz = Optional.absent();
    private CameraControllerI.Facing videoCameraFacing;
    private Optional<Integer> videoFrameRate = Optional.absent();
    private ImageSize videoSize;
    private ImageScale videoImageScale;
    private ImageFit videoImageFit;
    private Boolean shouldCropVideo;
    private Boolean shouldPadVideo;
    private Optional<Integer> videoBitrate = Optional.absent();
    private EncoderParamsI.VideoCodec videoCodec;
    private EncoderParamsI.OutputFormat outputFormat;
    Builder() {
    }
    private Builder(RecorderParams source) {
      this.audioBitrate = source.getAudioBitrate();
      this.audioChannelCount = source.getAudioChannelCount();
      this.audioCodec = source.getAudioCodec();
      this.audioSamplingRateHz = source.getAudioSamplingRateHz();
      this.videoCameraFacing = source.getVideoCameraFacing();
      this.videoFrameRate = source.getVideoFrameRate();
      this.videoSize = source.getVideoSize();
      this.videoImageScale = source.getVideoImageScale();
      this.videoImageFit = source.getVideoImageFit();
      this.shouldCropVideo = source.getShouldCropVideo();
      this.shouldPadVideo = source.getShouldPadVideo();
      this.videoBitrate = source.getVideoBitrate();
      this.videoCodec = source.getVideoCodec();
      this.outputFormat = source.getOutputFormat();
    }
    @Override
    public RecorderParams.Builder setAudioBitrate(int audioBitrate) {
      this.audioBitrate = Optional.of(audioBitrate);
      return this;
    }
    @Override
    public RecorderParams.Builder setAudioBitrate(Optional<Integer> audioBitrate) {
      if (audioBitrate == null) {
        throw new NullPointerException("Null audioBitrate");
      }
      this.audioBitrate = audioBitrate;
      return this;
    }
    @Override
    public RecorderParams.Builder setAudioChannelCount(Optional<Integer> audioChannelCount) {
      if (audioChannelCount == null) {
        throw new NullPointerException("Null audioChannelCount");
      }
      this.audioChannelCount = audioChannelCount;
      return this;
    }
    @Override
    public RecorderParams.Builder setAudioCodec(EncoderParamsI.AudioCodec audioCodec) {
      if (audioCodec == null) {
        throw new NullPointerException("Null audioCodec");
      }
      this.audioCodec = audioCodec;
      return this;
    }
    @Override
    public RecorderParams.Builder setAudioSamplingRateHz(Optional<Integer> audioSamplingRateHz) {
      if (audioSamplingRateHz == null) {
        throw new NullPointerException("Null audioSamplingRateHz");
      }
      this.audioSamplingRateHz = audioSamplingRateHz;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoCameraFacing(CameraControllerI.Facing videoCameraFacing) {
      if (videoCameraFacing == null) {
        throw new NullPointerException("Null videoCameraFacing");
      }
      this.videoCameraFacing = videoCameraFacing;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoFrameRate(Optional<Integer> videoFrameRate) {
      if (videoFrameRate == null) {
        throw new NullPointerException("Null videoFrameRate");
      }
      this.videoFrameRate = videoFrameRate;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoSize(ImageSize videoSize) {
      if (videoSize == null) {
        throw new NullPointerException("Null videoSize");
      }
      this.videoSize = videoSize;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoImageScale(ImageScale videoImageScale) {
      if (videoImageScale == null) {
        throw new NullPointerException("Null videoImageScale");
      }
      this.videoImageScale = videoImageScale;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoImageFit(ImageFit videoImageFit) {
      if (videoImageFit == null) {
        throw new NullPointerException("Null videoImageFit");
      }
      this.videoImageFit = videoImageFit;
      return this;
    }
    @Override
    public RecorderParams.Builder setShouldCropVideo(boolean shouldCropVideo) {
      this.shouldCropVideo = shouldCropVideo;
      return this;
    }
    @Override
    public RecorderParams.Builder setShouldPadVideo(boolean shouldPadVideo) {
      this.shouldPadVideo = shouldPadVideo;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoBitrate(Optional<Integer> videoBitrate) {
      if (videoBitrate == null) {
        throw new NullPointerException("Null videoBitrate");
      }
      this.videoBitrate = videoBitrate;
      return this;
    }
    @Override
    public RecorderParams.Builder setVideoCodec(EncoderParamsI.VideoCodec videoCodec) {
      if (videoCodec == null) {
        throw new NullPointerException("Null videoCodec");
      }
      this.videoCodec = videoCodec;
      return this;
    }
    @Override
    public RecorderParams.Builder setOutputFormat(EncoderParamsI.OutputFormat outputFormat) {
      if (outputFormat == null) {
        throw new NullPointerException("Null outputFormat");
      }
      this.outputFormat = outputFormat;
      return this;
    }
    @Override
    RecorderParams autoBuild() {
      String missing = "";
      if (this.audioCodec == null) {
        missing += " audioCodec";
      }
      if (this.videoCameraFacing == null) {
        missing += " videoCameraFacing";
      }
      if (this.videoSize == null) {
        missing += " videoSize";
      }
      if (this.videoImageScale == null) {
        missing += " videoImageScale";
      }
      if (this.videoImageFit == null) {
        missing += " videoImageFit";
      }
      if (this.shouldCropVideo == null) {
        missing += " shouldCropVideo";
      }
      if (this.shouldPadVideo == null) {
        missing += " shouldPadVideo";
      }
      if (this.videoCodec == null) {
        missing += " videoCodec";
      }
      if (this.outputFormat == null) {
        missing += " outputFormat";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_RecorderParams(
          this.audioBitrate,
          this.audioChannelCount,
          this.audioCodec,
          this.audioSamplingRateHz,
          this.videoCameraFacing,
          this.videoFrameRate,
          this.videoSize,
          this.videoImageScale,
          this.videoImageFit,
          this.shouldCropVideo,
          this.shouldPadVideo,
          this.videoBitrate,
          this.videoCodec,
          this.outputFormat);
    }
  }

}
