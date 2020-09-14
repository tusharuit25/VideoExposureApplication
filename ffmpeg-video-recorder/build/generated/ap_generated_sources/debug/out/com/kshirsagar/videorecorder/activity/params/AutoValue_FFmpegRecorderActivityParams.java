
package com.kshirsagar.videorecorder.activity.params;

import com.kshirsagar.videorecorder.recorder.params.RecorderParams;
import com.google.common.base.Optional;

 final class AutoValue_FFmpegRecorderActivityParams extends FFmpegRecorderActivityParams {

  private final String videoOutputFileUri;
  private final Optional<String> videoThumbnailOutputFileUri;
  private final InteractionParams interactionParams;
  private final RecorderParams recorderParams;
  private final RecorderActivityThemeParams themeParams;

  private AutoValue_FFmpegRecorderActivityParams(
      String videoOutputFileUri,
      Optional<String> videoThumbnailOutputFileUri,
      InteractionParams interactionParams,
      RecorderParams recorderParams,
      RecorderActivityThemeParams themeParams) {
    this.videoOutputFileUri = videoOutputFileUri;
    this.videoThumbnailOutputFileUri = videoThumbnailOutputFileUri;
    this.interactionParams = interactionParams;
    this.recorderParams = recorderParams;
    this.themeParams = themeParams;
  }

  @Override
  public String getVideoOutputFileUri() {
    return videoOutputFileUri;
  }

  @Override
  public Optional<String> getVideoThumbnailOutputFileUri() {
    return videoThumbnailOutputFileUri;
  }

  @Override
  public InteractionParams getInteractionParams() {
    return interactionParams;
  }

  @Override
  public RecorderParams getRecorderParams() {
    return recorderParams;
  }

  @Override
  public RecorderActivityThemeParams getThemeParams() {
    return themeParams;
  }

  @Override
  public String toString() {
    return "FFmpegRecorderActivityParams{"
        + "videoOutputFileUri=" + videoOutputFileUri + ", "
        + "videoThumbnailOutputFileUri=" + videoThumbnailOutputFileUri + ", "
        + "interactionParams=" + interactionParams + ", "
        + "recorderParams=" + recorderParams + ", "
        + "themeParams=" + themeParams
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FFmpegRecorderActivityParams) {
      FFmpegRecorderActivityParams that = (FFmpegRecorderActivityParams) o;
      return (this.videoOutputFileUri.equals(that.getVideoOutputFileUri()))
           && (this.videoThumbnailOutputFileUri.equals(that.getVideoThumbnailOutputFileUri()))
           && (this.interactionParams.equals(that.getInteractionParams()))
           && (this.recorderParams.equals(that.getRecorderParams()))
           && (this.themeParams.equals(that.getThemeParams()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.videoOutputFileUri.hashCode();
    h *= 1000003;
    h ^= this.videoThumbnailOutputFileUri.hashCode();
    h *= 1000003;
    h ^= this.interactionParams.hashCode();
    h *= 1000003;
    h ^= this.recorderParams.hashCode();
    h *= 1000003;
    h ^= this.themeParams.hashCode();
    return h;
  }

  @Override
  public FFmpegRecorderActivityParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends FFmpegRecorderActivityParams.Builder {
    private String videoOutputFileUri;
    private Optional<String> videoThumbnailOutputFileUri = Optional.absent();
    private InteractionParams.Builder interactionParamsBuilder$;
    private InteractionParams interactionParams;
    private RecorderParams.Builder recorderParamsBuilder$;
    private RecorderParams recorderParams;
    private RecorderActivityThemeParams.Builder themeParamsBuilder$;
    private RecorderActivityThemeParams themeParams;
    Builder() {
    }
    private Builder(FFmpegRecorderActivityParams source) {
      this.videoOutputFileUri = source.getVideoOutputFileUri();
      this.videoThumbnailOutputFileUri = source.getVideoThumbnailOutputFileUri();
      this.interactionParams = source.getInteractionParams();
      this.recorderParams = source.getRecorderParams();
      this.themeParams = source.getThemeParams();
    }
    @Override
    public FFmpegRecorderActivityParams.Builder setVideoOutputFileUri(String videoOutputFileUri) {
      if (videoOutputFileUri == null) {
        throw new NullPointerException("Null videoOutputFileUri");
      }
      this.videoOutputFileUri = videoOutputFileUri;
      return this;
    }
    @Override
    public FFmpegRecorderActivityParams.Builder setVideoThumbnailOutputFileUri(Optional<String> videoThumbnailOutputFileUri) {
      if (videoThumbnailOutputFileUri == null) {
        throw new NullPointerException("Null videoThumbnailOutputFileUri");
      }
      this.videoThumbnailOutputFileUri = videoThumbnailOutputFileUri;
      return this;
    }
    @Override
    public FFmpegRecorderActivityParams.Builder setInteractionParams(InteractionParams interactionParams) {
      if (interactionParams == null) {
        throw new NullPointerException("Null interactionParams");
      }
      if (interactionParamsBuilder$ != null) {
        throw new IllegalStateException("Cannot set interactionParams after calling interactionParamsBuilder()");
      }
      this.interactionParams = interactionParams;
      return this;
    }
    @Override
    public InteractionParams.Builder interactionParamsBuilder() {
      if (interactionParamsBuilder$ == null) {
        if (interactionParams == null) {
          interactionParamsBuilder$ = InteractionParams.builder();
        } else {
          interactionParamsBuilder$ = interactionParams.toBuilder();
          interactionParams = null;
        }
      }
      return interactionParamsBuilder$;
    }
    @Override
    public FFmpegRecorderActivityParams.Builder setRecorderParams(RecorderParams recorderParams) {
      if (recorderParams == null) {
        throw new NullPointerException("Null recorderParams");
      }
      if (recorderParamsBuilder$ != null) {
        throw new IllegalStateException("Cannot set recorderParams after calling recorderParamsBuilder()");
      }
      this.recorderParams = recorderParams;
      return this;
    }
    @Override
    public RecorderParams.Builder recorderParamsBuilder() {
      if (recorderParamsBuilder$ == null) {
        if (recorderParams == null) {
          recorderParamsBuilder$ = RecorderParams.builder();
        } else {
          recorderParamsBuilder$ = recorderParams.toBuilder();
          recorderParams = null;
        }
      }
      return recorderParamsBuilder$;
    }
    @Override
    public FFmpegRecorderActivityParams.Builder setThemeParams(RecorderActivityThemeParams themeParams) {
      if (themeParams == null) {
        throw new NullPointerException("Null themeParams");
      }
      if (themeParamsBuilder$ != null) {
        throw new IllegalStateException("Cannot set themeParams after calling themeParamsBuilder()");
      }
      this.themeParams = themeParams;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder themeParamsBuilder() {
      if (themeParamsBuilder$ == null) {
        if (themeParams == null) {
          themeParamsBuilder$ = RecorderActivityThemeParams.builder();
        } else {
          themeParamsBuilder$ = themeParams.toBuilder();
          themeParams = null;
        }
      }
      return themeParamsBuilder$;
    }
    @Override
    public FFmpegRecorderActivityParams build() {
      if (interactionParamsBuilder$ != null) {
        this.interactionParams = interactionParamsBuilder$.build();
      } else if (this.interactionParams == null) {
        InteractionParams.Builder interactionParams$builder = InteractionParams.builder();
        this.interactionParams = interactionParams$builder.build();
      }
      if (recorderParamsBuilder$ != null) {
        this.recorderParams = recorderParamsBuilder$.build();
      } else if (this.recorderParams == null) {
        RecorderParams.Builder recorderParams$builder = RecorderParams.builder();
        this.recorderParams = recorderParams$builder.build();
      }
      if (themeParamsBuilder$ != null) {
        this.themeParams = themeParamsBuilder$.build();
      } else if (this.themeParams == null) {
        RecorderActivityThemeParams.Builder themeParams$builder = RecorderActivityThemeParams.builder();
        this.themeParams = themeParams$builder.build();
      }
      String missing = "";
      if (this.videoOutputFileUri == null) {
        missing += " videoOutputFileUri";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_FFmpegRecorderActivityParams(
          this.videoOutputFileUri,
          this.videoThumbnailOutputFileUri,
          this.interactionParams,
          this.recorderParams,
          this.themeParams);
    }
  }

}
