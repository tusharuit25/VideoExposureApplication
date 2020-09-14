
package com.kshirsagar.videorecorder.activity.params;

import androidx.annotation.ColorInt;

 final class AutoValue_ActivityThemeParams extends ActivityThemeParams {

  private final int statusBarColor;
  private final int toolbarColor;
  private final int toolbarWidgetColor;
  private final int progressColor;

  private AutoValue_ActivityThemeParams(
      int statusBarColor,
      int toolbarColor,
      int toolbarWidgetColor,
      int progressColor) {
    this.statusBarColor = statusBarColor;
    this.toolbarColor = toolbarColor;
    this.toolbarWidgetColor = toolbarWidgetColor;
    this.progressColor = progressColor;
  }

  @ColorInt
  @Override
  public int getStatusBarColor() {
    return statusBarColor;
  }

  @ColorInt
  @Override
  public int getToolbarColor() {
    return toolbarColor;
  }

  @ColorInt
  @Override
  public int getToolbarWidgetColor() {
    return toolbarWidgetColor;
  }

  @ColorInt
  @Override
  public int getProgressColor() {
    return progressColor;
  }

  @Override
  public String toString() {
    return "ActivityThemeParams{"
        + "statusBarColor=" + statusBarColor + ", "
        + "toolbarColor=" + toolbarColor + ", "
        + "toolbarWidgetColor=" + toolbarWidgetColor + ", "
        + "progressColor=" + progressColor
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ActivityThemeParams) {
      ActivityThemeParams that = (ActivityThemeParams) o;
      return (this.statusBarColor == that.getStatusBarColor())
           && (this.toolbarColor == that.getToolbarColor())
           && (this.toolbarWidgetColor == that.getToolbarWidgetColor())
           && (this.progressColor == that.getProgressColor());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.statusBarColor;
    h *= 1000003;
    h ^= this.toolbarColor;
    h *= 1000003;
    h ^= this.toolbarWidgetColor;
    h *= 1000003;
    h ^= this.progressColor;
    return h;
  }

  @Override
  public ActivityThemeParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends ActivityThemeParams.Builder {
    private Integer statusBarColor;
    private Integer toolbarColor;
    private Integer toolbarWidgetColor;
    private Integer progressColor;
    Builder() {
    }
    private Builder(ActivityThemeParams source) {
      this.statusBarColor = source.getStatusBarColor();
      this.toolbarColor = source.getToolbarColor();
      this.toolbarWidgetColor = source.getToolbarWidgetColor();
      this.progressColor = source.getProgressColor();
    }
    @Override
    public ActivityThemeParams.Builder setStatusBarColor(int statusBarColor) {
      this.statusBarColor = statusBarColor;
      return this;
    }
    @Override
    public ActivityThemeParams.Builder setToolbarColor(int toolbarColor) {
      this.toolbarColor = toolbarColor;
      return this;
    }
    @Override
    public ActivityThemeParams.Builder setToolbarWidgetColor(int toolbarWidgetColor) {
      this.toolbarWidgetColor = toolbarWidgetColor;
      return this;
    }
    @Override
    public ActivityThemeParams.Builder setProgressColor(int progressColor) {
      this.progressColor = progressColor;
      return this;
    }
    @Override
    public ActivityThemeParams build() {
      String missing = "";
      if (this.statusBarColor == null) {
        missing += " statusBarColor";
      }
      if (this.toolbarColor == null) {
        missing += " toolbarColor";
      }
      if (this.toolbarWidgetColor == null) {
        missing += " toolbarWidgetColor";
      }
      if (this.progressColor == null) {
        missing += " progressColor";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_ActivityThemeParams(
          this.statusBarColor,
          this.toolbarColor,
          this.toolbarWidgetColor,
          this.progressColor);
    }
  }

}
