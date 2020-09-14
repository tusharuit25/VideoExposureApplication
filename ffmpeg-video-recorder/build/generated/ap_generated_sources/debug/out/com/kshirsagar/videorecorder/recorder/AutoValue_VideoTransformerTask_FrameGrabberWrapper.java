
package com.kshirsagar.videorecorder.recorder;

import org.bytedeco.javacv.FrameGrabber;

 final class AutoValue_VideoTransformerTask_FrameGrabberWrapper extends VideoTransformerTask.FrameGrabberWrapper {

  private final FrameGrabber frameGrabber;
  private final VideoTransformerTask.FilterParams filterParams;

  AutoValue_VideoTransformerTask_FrameGrabberWrapper(
      FrameGrabber frameGrabber,
      VideoTransformerTask.FilterParams filterParams) {
    if (frameGrabber == null) {
      throw new NullPointerException("Null frameGrabber");
    }
    this.frameGrabber = frameGrabber;
    if (filterParams == null) {
      throw new NullPointerException("Null filterParams");
    }
    this.filterParams = filterParams;
  }

  @Override
  public FrameGrabber getFrameGrabber() {
    return frameGrabber;
  }

  @Override
  public VideoTransformerTask.FilterParams getFilterParams() {
    return filterParams;
  }

  @Override
  public String toString() {
    return "FrameGrabberWrapper{"
        + "frameGrabber=" + frameGrabber + ", "
        + "filterParams=" + filterParams
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VideoTransformerTask.FrameGrabberWrapper) {
      VideoTransformerTask.FrameGrabberWrapper that = (VideoTransformerTask.FrameGrabberWrapper) o;
      return (this.frameGrabber.equals(that.getFrameGrabber()))
           && (this.filterParams.equals(that.getFilterParams()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.frameGrabber.hashCode();
    h *= 1000003;
    h ^= this.filterParams.hashCode();
    return h;
  }

}
