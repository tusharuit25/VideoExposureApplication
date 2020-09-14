
package com.kshirsagar.videorecorder.activity.params;

 final class AutoValue_InteractionParams extends InteractionParams {

  private final long maxFileSizeBytes;
  private final int minRecordingMillis;
  private final int maxRecordingMillis;
  private final int tapToFocusHoldTimeMillis;
  private final float tapToFocusSizePercent;

  private AutoValue_InteractionParams(
      long maxFileSizeBytes,
      int minRecordingMillis,
      int maxRecordingMillis,
      int tapToFocusHoldTimeMillis,
      float tapToFocusSizePercent) {
    this.maxFileSizeBytes = maxFileSizeBytes;
    this.minRecordingMillis = minRecordingMillis;
    this.maxRecordingMillis = maxRecordingMillis;
    this.tapToFocusHoldTimeMillis = tapToFocusHoldTimeMillis;
    this.tapToFocusSizePercent = tapToFocusSizePercent;
  }

  @Override
  public long getMaxFileSizeBytes() {
    return maxFileSizeBytes;
  }

  @Override
  public int getMinRecordingMillis() {
    return minRecordingMillis;
  }

  @Override
  public int getMaxRecordingMillis() {
    return maxRecordingMillis;
  }

  @Override
  public int getTapToFocusHoldTimeMillis() {
    return tapToFocusHoldTimeMillis;
  }

  @Override
  public float getTapToFocusSizePercent() {
    return tapToFocusSizePercent;
  }

  @Override
  public String toString() {
    return "InteractionParams{"
        + "maxFileSizeBytes=" + maxFileSizeBytes + ", "
        + "minRecordingMillis=" + minRecordingMillis + ", "
        + "maxRecordingMillis=" + maxRecordingMillis + ", "
        + "tapToFocusHoldTimeMillis=" + tapToFocusHoldTimeMillis + ", "
        + "tapToFocusSizePercent=" + tapToFocusSizePercent
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof InteractionParams) {
      InteractionParams that = (InteractionParams) o;
      return (this.maxFileSizeBytes == that.getMaxFileSizeBytes())
           && (this.minRecordingMillis == that.getMinRecordingMillis())
           && (this.maxRecordingMillis == that.getMaxRecordingMillis())
           && (this.tapToFocusHoldTimeMillis == that.getTapToFocusHoldTimeMillis())
           && (Float.floatToIntBits(this.tapToFocusSizePercent) == Float.floatToIntBits(that.getTapToFocusSizePercent()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= (int) ((this.maxFileSizeBytes >>> 32) ^ this.maxFileSizeBytes);
    h *= 1000003;
    h ^= this.minRecordingMillis;
    h *= 1000003;
    h ^= this.maxRecordingMillis;
    h *= 1000003;
    h ^= this.tapToFocusHoldTimeMillis;
    h *= 1000003;
    h ^= Float.floatToIntBits(this.tapToFocusSizePercent);
    return h;
  }

  @Override
  public InteractionParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends InteractionParams.Builder {
    private Long maxFileSizeBytes;
    private Integer minRecordingMillis;
    private Integer maxRecordingMillis;
    private Integer tapToFocusHoldTimeMillis;
    private Float tapToFocusSizePercent;
    Builder() {
    }
    private Builder(InteractionParams source) {
      this.maxFileSizeBytes = source.getMaxFileSizeBytes();
      this.minRecordingMillis = source.getMinRecordingMillis();
      this.maxRecordingMillis = source.getMaxRecordingMillis();
      this.tapToFocusHoldTimeMillis = source.getTapToFocusHoldTimeMillis();
      this.tapToFocusSizePercent = source.getTapToFocusSizePercent();
    }
    @Override
    public InteractionParams.Builder setMaxFileSizeBytes(long maxFileSizeBytes) {
      this.maxFileSizeBytes = maxFileSizeBytes;
      return this;
    }
    @Override
    public InteractionParams.Builder setMinRecordingMillis(int minRecordingMillis) {
      this.minRecordingMillis = minRecordingMillis;
      return this;
    }
    @Override
    public InteractionParams.Builder setMaxRecordingMillis(int maxRecordingMillis) {
      this.maxRecordingMillis = maxRecordingMillis;
      return this;
    }
    @Override
    public InteractionParams.Builder setTapToFocusHoldTimeMillis(int tapToFocusHoldTimeMillis) {
      this.tapToFocusHoldTimeMillis = tapToFocusHoldTimeMillis;
      return this;
    }
    @Override
    public InteractionParams.Builder setTapToFocusSizePercent(float tapToFocusSizePercent) {
      this.tapToFocusSizePercent = tapToFocusSizePercent;
      return this;
    }
    @Override
    InteractionParams autoBuild() {
      String missing = "";
      if (this.maxFileSizeBytes == null) {
        missing += " maxFileSizeBytes";
      }
      if (this.minRecordingMillis == null) {
        missing += " minRecordingMillis";
      }
      if (this.maxRecordingMillis == null) {
        missing += " maxRecordingMillis";
      }
      if (this.tapToFocusHoldTimeMillis == null) {
        missing += " tapToFocusHoldTimeMillis";
      }
      if (this.tapToFocusSizePercent == null) {
        missing += " tapToFocusSizePercent";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_InteractionParams(
          this.maxFileSizeBytes,
          this.minRecordingMillis,
          this.maxRecordingMillis,
          this.tapToFocusHoldTimeMillis,
          this.tapToFocusSizePercent);
    }
  }

}
