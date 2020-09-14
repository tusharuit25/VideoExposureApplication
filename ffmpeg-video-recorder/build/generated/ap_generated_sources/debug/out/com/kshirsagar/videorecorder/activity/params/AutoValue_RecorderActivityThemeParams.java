
package com.kshirsagar.videorecorder.activity.params;

import androidx.annotation.ColorInt;

 final class AutoValue_RecorderActivityThemeParams extends RecorderActivityThemeParams {

  private final int statusBarColor;
  private final int toolbarColor;
  private final int toolbarWidgetColor;
  private final int progressColor;
  private final int progressCursorColor;
  private final int progressMinProgressColor;
  private final int widgetColor;

  private AutoValue_RecorderActivityThemeParams(
      int statusBarColor,
      int toolbarColor,
      int toolbarWidgetColor,
      int progressColor,
      int progressCursorColor,
      int progressMinProgressColor,
      int widgetColor) {
    this.statusBarColor = statusBarColor;
    this.toolbarColor = toolbarColor;
    this.toolbarWidgetColor = toolbarWidgetColor;
    this.progressColor = progressColor;
    this.progressCursorColor = progressCursorColor;
    this.progressMinProgressColor = progressMinProgressColor;
    this.widgetColor = widgetColor;
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

  @ColorInt
  @Override
  public int getProgressCursorColor() {
    return progressCursorColor;
  }

  @ColorInt
  @Override
  public int getProgressMinProgressColor() {
    return progressMinProgressColor;
  }

  @ColorInt
  @Override
  public int getWidgetColor() {
    return widgetColor;
  }

  @Override
  public String toString() {
    return "RecorderActivityThemeParams{"
        + "statusBarColor=" + statusBarColor + ", "
        + "toolbarColor=" + toolbarColor + ", "
        + "toolbarWidgetColor=" + toolbarWidgetColor + ", "
        + "progressColor=" + progressColor + ", "
        + "progressCursorColor=" + progressCursorColor + ", "
        + "progressMinProgressColor=" + progressMinProgressColor + ", "
        + "widgetColor=" + widgetColor
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RecorderActivityThemeParams) {
      RecorderActivityThemeParams that = (RecorderActivityThemeParams) o;
      return (this.statusBarColor == that.getStatusBarColor())
           && (this.toolbarColor == that.getToolbarColor())
           && (this.toolbarWidgetColor == that.getToolbarWidgetColor())
           && (this.progressColor == that.getProgressColor())
           && (this.progressCursorColor == that.getProgressCursorColor())
           && (this.progressMinProgressColor == that.getProgressMinProgressColor())
           && (this.widgetColor == that.getWidgetColor());
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
    h *= 1000003;
    h ^= this.progressCursorColor;
    h *= 1000003;
    h ^= this.progressMinProgressColor;
    h *= 1000003;
    h ^= this.widgetColor;
    return h;
  }

  @Override
  public RecorderActivityThemeParams.Builder toBuilder() {
    return new Builder(this);
  }

  static final class Builder extends RecorderActivityThemeParams.Builder {
    private Integer statusBarColor;
    private Integer toolbarColor;
    private Integer toolbarWidgetColor;
    private Integer progressColor;
    private Integer progressCursorColor;
    private Integer progressMinProgressColor;
    private Integer widgetColor;
    Builder() {
    }
    private Builder(RecorderActivityThemeParams source) {
      this.statusBarColor = source.getStatusBarColor();
      this.toolbarColor = source.getToolbarColor();
      this.toolbarWidgetColor = source.getToolbarWidgetColor();
      this.progressColor = source.getProgressColor();
      this.progressCursorColor = source.getProgressCursorColor();
      this.progressMinProgressColor = source.getProgressMinProgressColor();
      this.widgetColor = source.getWidgetColor();
    }
    @Override
    public RecorderActivityThemeParams.Builder setStatusBarColor(int statusBarColor) {
      this.statusBarColor = statusBarColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setToolbarColor(int toolbarColor) {
      this.toolbarColor = toolbarColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setToolbarWidgetColor(int toolbarWidgetColor) {
      this.toolbarWidgetColor = toolbarWidgetColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setProgressColor(int progressColor) {
      this.progressColor = progressColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setProgressCursorColor(int progressCursorColor) {
      this.progressCursorColor = progressCursorColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setProgressMinProgressColor(int progressMinProgressColor) {
      this.progressMinProgressColor = progressMinProgressColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams.Builder setWidgetColor(int widgetColor) {
      this.widgetColor = widgetColor;
      return this;
    }
    @Override
    public RecorderActivityThemeParams build() {
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
      if (this.progressCursorColor == null) {
        missing += " progressCursorColor";
      }
      if (this.progressMinProgressColor == null) {
        missing += " progressMinProgressColor";
      }
      if (this.widgetColor == null) {
        missing += " widgetColor";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_RecorderActivityThemeParams(
          this.statusBarColor,
          this.toolbarColor,
          this.toolbarWidgetColor,
          this.progressColor,
          this.progressCursorColor,
          this.progressMinProgressColor,
          this.widgetColor);
    }
  }

}
