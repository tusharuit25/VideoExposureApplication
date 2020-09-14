
package com.kshirsagar.videorecorder.activity.params;

 final class AutoValue_FFmpegPreviewActivityParams extends FFmpegPreviewActivityParams {

  private final String videoFileUri;
  private final boolean confirmation;
  private final ActivityThemeParams themeParams;

  private AutoValue_FFmpegPreviewActivityParams(
      String videoFileUri,
      boolean confirmation,
      ActivityThemeParams themeParams) {
    this.videoFileUri = videoFileUri;
    this.confirmation = confirmation;
    this.themeParams = themeParams;
  }

  @Override
  public String getVideoFileUri() {
    return videoFileUri;
  }

  @Override
  public boolean isConfirmation() {
    return confirmation;
  }

  @Override
  public ActivityThemeParams getThemeParams() {
    return themeParams;
  }

  @Override
  public String toString() {
    return "FFmpegPreviewActivityParams{"
        + "videoFileUri=" + videoFileUri + ", "
        + "confirmation=" + confirmation + ", "
        + "themeParams=" + themeParams
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof FFmpegPreviewActivityParams) {
      FFmpegPreviewActivityParams that = (FFmpegPreviewActivityParams) o;
      return (this.videoFileUri.equals(that.getVideoFileUri()))
           && (this.confirmation == that.isConfirmation())
           && (this.themeParams.equals(that.getThemeParams()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.videoFileUri.hashCode();
    h *= 1000003;
    h ^= this.confirmation ? 1231 : 1237;
    h *= 1000003;
    h ^= this.themeParams.hashCode();
    return h;
  }

  @Override
  public FFmpegPreviewActivityParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends FFmpegPreviewActivityParams.Builder {
    private String videoFileUri;
    private Boolean confirmation;
    private ActivityThemeParams.Builder themeParamsBuilder$;
    private ActivityThemeParams themeParams;
    Builder() {
    }
    private Builder(FFmpegPreviewActivityParams source) {
      this.videoFileUri = source.getVideoFileUri();
      this.confirmation = source.isConfirmation();
      this.themeParams = source.getThemeParams();
    }
    @Override
    public FFmpegPreviewActivityParams.Builder setVideoFileUri(String videoFileUri) {
      if (videoFileUri == null) {
        throw new NullPointerException("Null videoFileUri");
      }
      this.videoFileUri = videoFileUri;
      return this;
    }
    @Override
    public FFmpegPreviewActivityParams.Builder setConfirmation(boolean confirmation) {
      this.confirmation = confirmation;
      return this;
    }
    @Override
    public FFmpegPreviewActivityParams.Builder setThemeParams(ActivityThemeParams themeParams) {
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
    public ActivityThemeParams.Builder themeParamsBuilder() {
      if (themeParamsBuilder$ == null) {
        if (themeParams == null) {
          themeParamsBuilder$ = ActivityThemeParams.builder();
        } else {
          themeParamsBuilder$ = themeParams.toBuilder();
          themeParams = null;
        }
      }
      return themeParamsBuilder$;
    }
    @Override
    public FFmpegPreviewActivityParams build() {
      if (themeParamsBuilder$ != null) {
        this.themeParams = themeParamsBuilder$.build();
      } else if (this.themeParams == null) {
        ActivityThemeParams.Builder themeParams$builder = ActivityThemeParams.builder();
        this.themeParams = themeParams$builder.build();
      }
      String missing = "";
      if (this.videoFileUri == null) {
        missing += " videoFileUri";
      }
      if (this.confirmation == null) {
        missing += " confirmation";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_FFmpegPreviewActivityParams(
          this.videoFileUri,
          this.confirmation,
          this.themeParams);
    }
  }

}
