
package com.kshirsagar.videorecorder.recorder.params;

import com.kshirsagar.videorecorder.camera.CameraControllerI;
import com.kshirsagar.videorecorder.recorder.common.ImageFit;
import com.kshirsagar.videorecorder.recorder.common.ImageScale;
import com.kshirsagar.videorecorder.recorder.common.ImageSize;
import com.google.common.base.Optional;

 final class AutoValue_CameraParams extends CameraParams {

  private final CameraControllerI.Facing videoCameraFacing;
  private final Optional<Integer> videoFrameRate;
  private final ImageSize videoSize;
  private final ImageFit videoImageFit;
  private final ImageScale videoImageScale;

  private AutoValue_CameraParams(
      CameraControllerI.Facing videoCameraFacing,
      Optional<Integer> videoFrameRate,
      ImageSize videoSize,
      ImageFit videoImageFit,
      ImageScale videoImageScale) {
    this.videoCameraFacing = videoCameraFacing;
    this.videoFrameRate = videoFrameRate;
    this.videoSize = videoSize;
    this.videoImageFit = videoImageFit;
    this.videoImageScale = videoImageScale;
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
  public ImageFit getVideoImageFit() {
    return videoImageFit;
  }

  @Override
  public ImageScale getVideoImageScale() {
    return videoImageScale;
  }

  @Override
  public String toString() {
    return "CameraParams{"
        + "videoCameraFacing=" + videoCameraFacing + ", "
        + "videoFrameRate=" + videoFrameRate + ", "
        + "videoSize=" + videoSize + ", "
        + "videoImageFit=" + videoImageFit + ", "
        + "videoImageScale=" + videoImageScale
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof CameraParams) {
      CameraParams that = (CameraParams) o;
      return (this.videoCameraFacing.equals(that.getVideoCameraFacing()))
           && (this.videoFrameRate.equals(that.getVideoFrameRate()))
           && (this.videoSize.equals(that.getVideoSize()))
           && (this.videoImageFit.equals(that.getVideoImageFit()))
           && (this.videoImageScale.equals(that.getVideoImageScale()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.videoCameraFacing.hashCode();
    h *= 1000003;
    h ^= this.videoFrameRate.hashCode();
    h *= 1000003;
    h ^= this.videoSize.hashCode();
    h *= 1000003;
    h ^= this.videoImageFit.hashCode();
    h *= 1000003;
    h ^= this.videoImageScale.hashCode();
    return h;
  }

  @Override
  public CameraParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends CameraParams.Builder {
    private CameraControllerI.Facing videoCameraFacing;
    private Optional<Integer> videoFrameRate = Optional.absent();
    private ImageSize videoSize;
    private ImageFit videoImageFit;
    private ImageScale videoImageScale;
    Builder() {
    }
    private Builder(CameraParams source) {
      this.videoCameraFacing = source.getVideoCameraFacing();
      this.videoFrameRate = source.getVideoFrameRate();
      this.videoSize = source.getVideoSize();
      this.videoImageFit = source.getVideoImageFit();
      this.videoImageScale = source.getVideoImageScale();
    }
    @Override
    public CameraParams.Builder setVideoCameraFacing(CameraControllerI.Facing videoCameraFacing) {
      if (videoCameraFacing == null) {
        throw new NullPointerException("Null videoCameraFacing");
      }
      this.videoCameraFacing = videoCameraFacing;
      return this;
    }
    @Override
    public CameraParams.Builder setVideoFrameRate(Optional<Integer> videoFrameRate) {
      if (videoFrameRate == null) {
        throw new NullPointerException("Null videoFrameRate");
      }
      this.videoFrameRate = videoFrameRate;
      return this;
    }
    @Override
    public CameraParams.Builder setVideoSize(ImageSize videoSize) {
      if (videoSize == null) {
        throw new NullPointerException("Null videoSize");
      }
      this.videoSize = videoSize;
      return this;
    }
    @Override
    public CameraParams.Builder setVideoImageFit(ImageFit videoImageFit) {
      if (videoImageFit == null) {
        throw new NullPointerException("Null videoImageFit");
      }
      this.videoImageFit = videoImageFit;
      return this;
    }
    @Override
    public CameraParams.Builder setVideoImageScale(ImageScale videoImageScale) {
      if (videoImageScale == null) {
        throw new NullPointerException("Null videoImageScale");
      }
      this.videoImageScale = videoImageScale;
      return this;
    }
    @Override
    public CameraParams build() {
      String missing = "";
      if (this.videoCameraFacing == null) {
        missing += " videoCameraFacing";
      }
      if (this.videoSize == null) {
        missing += " videoSize";
      }
      if (this.videoImageFit == null) {
        missing += " videoImageFit";
      }
      if (this.videoImageScale == null) {
        missing += " videoImageScale";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_CameraParams(
          this.videoCameraFacing,
          this.videoFrameRate,
          this.videoSize,
          this.videoImageFit,
          this.videoImageScale);
    }
  }

}
