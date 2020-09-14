
package com.kshirsagar.videorecorder.recorder.params;

import com.kshirsagar.videorecorder.recorder.common.ImageFit;
import com.kshirsagar.videorecorder.recorder.common.ImageScale;
import com.kshirsagar.videorecorder.recorder.common.ImageSize;

 final class AutoValue_VideoTransformerParams extends VideoTransformerParams {

  private final ImageSize videoSize;
  private final ImageFit videoImageFit;
  private final ImageScale videoImageScale;
  private final boolean shouldCropVideo;
  private final boolean shouldPadVideo;

  private AutoValue_VideoTransformerParams(
      ImageSize videoSize,
      ImageFit videoImageFit,
      ImageScale videoImageScale,
      boolean shouldCropVideo,
      boolean shouldPadVideo) {
    this.videoSize = videoSize;
    this.videoImageFit = videoImageFit;
    this.videoImageScale = videoImageScale;
    this.shouldCropVideo = shouldCropVideo;
    this.shouldPadVideo = shouldPadVideo;
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
  public boolean getShouldCropVideo() {
    return shouldCropVideo;
  }

  @Override
  public boolean getShouldPadVideo() {
    return shouldPadVideo;
  }

  @Override
  public String toString() {
    return "VideoTransformerParams{"
        + "videoSize=" + videoSize + ", "
        + "videoImageFit=" + videoImageFit + ", "
        + "videoImageScale=" + videoImageScale + ", "
        + "shouldCropVideo=" + shouldCropVideo + ", "
        + "shouldPadVideo=" + shouldPadVideo
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VideoTransformerParams) {
      VideoTransformerParams that = (VideoTransformerParams) o;
      return (this.videoSize.equals(that.getVideoSize()))
           && (this.videoImageFit.equals(that.getVideoImageFit()))
           && (this.videoImageScale.equals(that.getVideoImageScale()))
           && (this.shouldCropVideo == that.getShouldCropVideo())
           && (this.shouldPadVideo == that.getShouldPadVideo());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.videoSize.hashCode();
    h *= 1000003;
    h ^= this.videoImageFit.hashCode();
    h *= 1000003;
    h ^= this.videoImageScale.hashCode();
    h *= 1000003;
    h ^= this.shouldCropVideo ? 1231 : 1237;
    h *= 1000003;
    h ^= this.shouldPadVideo ? 1231 : 1237;
    return h;
  }

  @Override
  public VideoTransformerParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends VideoTransformerParams.Builder {
    private ImageSize videoSize;
    private ImageFit videoImageFit;
    private ImageScale videoImageScale;
    private Boolean shouldCropVideo;
    private Boolean shouldPadVideo;
    Builder() {
    }
    private Builder(VideoTransformerParams source) {
      this.videoSize = source.getVideoSize();
      this.videoImageFit = source.getVideoImageFit();
      this.videoImageScale = source.getVideoImageScale();
      this.shouldCropVideo = source.getShouldCropVideo();
      this.shouldPadVideo = source.getShouldPadVideo();
    }
    @Override
    public VideoTransformerParams.Builder setVideoSize(ImageSize videoSize) {
      if (videoSize == null) {
        throw new NullPointerException("Null videoSize");
      }
      this.videoSize = videoSize;
      return this;
    }
    @Override
    public VideoTransformerParams.Builder setVideoImageFit(ImageFit videoImageFit) {
      if (videoImageFit == null) {
        throw new NullPointerException("Null videoImageFit");
      }
      this.videoImageFit = videoImageFit;
      return this;
    }
    @Override
    public VideoTransformerParams.Builder setVideoImageScale(ImageScale videoImageScale) {
      if (videoImageScale == null) {
        throw new NullPointerException("Null videoImageScale");
      }
      this.videoImageScale = videoImageScale;
      return this;
    }
    @Override
    public VideoTransformerParams.Builder setShouldCropVideo(boolean shouldCropVideo) {
      this.shouldCropVideo = shouldCropVideo;
      return this;
    }
    @Override
    public VideoTransformerParams.Builder setShouldPadVideo(boolean shouldPadVideo) {
      this.shouldPadVideo = shouldPadVideo;
      return this;
    }
    @Override
    public VideoTransformerParams build() {
      String missing = "";
      if (this.videoSize == null) {
        missing += " videoSize";
      }
      if (this.videoImageFit == null) {
        missing += " videoImageFit";
      }
      if (this.videoImageScale == null) {
        missing += " videoImageScale";
      }
      if (this.shouldCropVideo == null) {
        missing += " shouldCropVideo";
      }
      if (this.shouldPadVideo == null) {
        missing += " shouldPadVideo";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_VideoTransformerParams(
          this.videoSize,
          this.videoImageFit,
          this.videoImageScale,
          this.shouldCropVideo,
          this.shouldPadVideo);
    }
  }

}
