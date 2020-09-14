
package com.kshirsagar.videorecorder.recorder.params;

import com.google.common.base.Optional;

 final class AutoValue_EncoderParams extends EncoderParams {

  private final Optional<Integer> audioBitrate;
  private final Optional<Integer> audioChannelCount;
  private final EncoderParamsI.AudioCodec audioCodec;
  private final Optional<Integer> audioSamplingRateHz;
  private final Optional<Integer> videoFrameRate;
  private final Optional<Integer> videoBitrate;
  private final EncoderParamsI.VideoCodec videoCodec;
  private final EncoderParamsI.OutputFormat outputFormat;

  private AutoValue_EncoderParams(
      Optional<Integer> audioBitrate,
      Optional<Integer> audioChannelCount,
      EncoderParamsI.AudioCodec audioCodec,
      Optional<Integer> audioSamplingRateHz,
      Optional<Integer> videoFrameRate,
      Optional<Integer> videoBitrate,
      EncoderParamsI.VideoCodec videoCodec,
      EncoderParamsI.OutputFormat outputFormat) {
    this.audioBitrate = audioBitrate;
    this.audioChannelCount = audioChannelCount;
    this.audioCodec = audioCodec;
    this.audioSamplingRateHz = audioSamplingRateHz;
    this.videoFrameRate = videoFrameRate;
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
  public Optional<Integer> getVideoFrameRate() {
    return videoFrameRate;
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
    return "EncoderParams{"
        + "audioBitrate=" + audioBitrate + ", "
        + "audioChannelCount=" + audioChannelCount + ", "
        + "audioCodec=" + audioCodec + ", "
        + "audioSamplingRateHz=" + audioSamplingRateHz + ", "
        + "videoFrameRate=" + videoFrameRate + ", "
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
    if (o instanceof EncoderParams) {
      EncoderParams that = (EncoderParams) o;
      return (this.audioBitrate.equals(that.getAudioBitrate()))
           && (this.audioChannelCount.equals(that.getAudioChannelCount()))
           && (this.audioCodec.equals(that.getAudioCodec()))
           && (this.audioSamplingRateHz.equals(that.getAudioSamplingRateHz()))
           && (this.videoFrameRate.equals(that.getVideoFrameRate()))
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
    h ^= this.videoFrameRate.hashCode();
    h *= 1000003;
    h ^= this.videoBitrate.hashCode();
    h *= 1000003;
    h ^= this.videoCodec.hashCode();
    h *= 1000003;
    h ^= this.outputFormat.hashCode();
    return h;
  }

  @Override
  public EncoderParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends EncoderParams.Builder {
    private Optional<Integer> audioBitrate = Optional.absent();
    private Optional<Integer> audioChannelCount = Optional.absent();
    private EncoderParamsI.AudioCodec audioCodec;
    private Optional<Integer> audioSamplingRateHz = Optional.absent();
    private Optional<Integer> videoFrameRate = Optional.absent();
    private Optional<Integer> videoBitrate = Optional.absent();
    private EncoderParamsI.VideoCodec videoCodec;
    private EncoderParamsI.OutputFormat outputFormat;
    Builder() {
    }
    private Builder(EncoderParams source) {
      this.audioBitrate = source.getAudioBitrate();
      this.audioChannelCount = source.getAudioChannelCount();
      this.audioCodec = source.getAudioCodec();
      this.audioSamplingRateHz = source.getAudioSamplingRateHz();
      this.videoFrameRate = source.getVideoFrameRate();
      this.videoBitrate = source.getVideoBitrate();
      this.videoCodec = source.getVideoCodec();
      this.outputFormat = source.getOutputFormat();
    }
    @Override
    public EncoderParams.Builder setAudioBitrate(Optional<Integer> audioBitrate) {
      if (audioBitrate == null) {
        throw new NullPointerException("Null audioBitrate");
      }
      this.audioBitrate = audioBitrate;
      return this;
    }
    @Override
    public EncoderParams.Builder setAudioChannelCount(Optional<Integer> audioChannelCount) {
      if (audioChannelCount == null) {
        throw new NullPointerException("Null audioChannelCount");
      }
      this.audioChannelCount = audioChannelCount;
      return this;
    }
    @Override
    public EncoderParams.Builder setAudioCodec(EncoderParamsI.AudioCodec audioCodec) {
      if (audioCodec == null) {
        throw new NullPointerException("Null audioCodec");
      }
      this.audioCodec = audioCodec;
      return this;
    }
    @Override
    public EncoderParams.Builder setAudioSamplingRateHz(Optional<Integer> audioSamplingRateHz) {
      if (audioSamplingRateHz == null) {
        throw new NullPointerException("Null audioSamplingRateHz");
      }
      this.audioSamplingRateHz = audioSamplingRateHz;
      return this;
    }
    @Override
    public EncoderParams.Builder setVideoFrameRate(Optional<Integer> videoFrameRate) {
      if (videoFrameRate == null) {
        throw new NullPointerException("Null videoFrameRate");
      }
      this.videoFrameRate = videoFrameRate;
      return this;
    }
    @Override
    public EncoderParams.Builder setVideoBitrate(Optional<Integer> videoBitrate) {
      if (videoBitrate == null) {
        throw new NullPointerException("Null videoBitrate");
      }
      this.videoBitrate = videoBitrate;
      return this;
    }
    @Override
    public EncoderParams.Builder setVideoCodec(EncoderParamsI.VideoCodec videoCodec) {
      if (videoCodec == null) {
        throw new NullPointerException("Null videoCodec");
      }
      this.videoCodec = videoCodec;
      return this;
    }
    @Override
    public EncoderParams.Builder setOutputFormat(EncoderParamsI.OutputFormat outputFormat) {
      if (outputFormat == null) {
        throw new NullPointerException("Null outputFormat");
      }
      this.outputFormat = outputFormat;
      return this;
    }
    @Override
    EncoderParams autoBuild() {
      String missing = "";
      if (this.audioCodec == null) {
        missing += " audioCodec";
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
      return new AutoValue_EncoderParams(
          this.audioBitrate,
          this.audioChannelCount,
          this.audioCodec,
          this.audioSamplingRateHz,
          this.videoFrameRate,
          this.videoBitrate,
          this.videoCodec,
          this.outputFormat);
    }
  }

}
