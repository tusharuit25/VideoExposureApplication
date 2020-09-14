
package com.kshirsagar.videorecorder.recorder;

import com.kshirsagar.videorecorder.camera.CameraControllerI;
import com.kshirsagar.videorecorder.recorder.common.ImageSize;

 final class AutoValue_VideoTransformerTask_FilterParams extends VideoTransformerTask.FilterParams {

  private final ImageSize imageSize;
  private final int frameRate;
  private final CameraControllerI.Facing facing;
  private final int rotationDegrees;

  AutoValue_VideoTransformerTask_FilterParams(
      ImageSize imageSize,
      int frameRate,
      CameraControllerI.Facing facing,
      int rotationDegrees) {
    if (imageSize == null) {
      throw new NullPointerException("Null imageSize");
    }
    this.imageSize = imageSize;
    this.frameRate = frameRate;
    if (facing == null) {
      throw new NullPointerException("Null facing");
    }
    this.facing = facing;
    this.rotationDegrees = rotationDegrees;
  }

  @Override
  public ImageSize getImageSize() {
    return imageSize;
  }

  @Override
  public int getFrameRate() {
    return frameRate;
  }

  @Override
  public CameraControllerI.Facing getFacing() {
    return facing;
  }

  @Override
  public int getRotationDegrees() {
    return rotationDegrees;
  }

  @Override
  public String toString() {
    return "FilterParams{"
        + "imageSize=" + imageSize + ", "
        + "frameRate=" + frameRate + ", "
        + "facing=" + facing + ", "
        + "rotationDegrees=" + rotationDegrees
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof VideoTransformerTask.FilterParams) {
      VideoTransformerTask.FilterParams that = (VideoTransformerTask.FilterParams) o;
      return (this.imageSize.equals(that.getImageSize()))
           && (this.frameRate == that.getFrameRate())
           && (this.facing.equals(that.getFacing()))
           && (this.rotationDegrees == that.getRotationDegrees());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.imageSize.hashCode();
    h *= 1000003;
    h ^= this.frameRate;
    h *= 1000003;
    h ^= this.facing.hashCode();
    h *= 1000003;
    h ^= this.rotationDegrees;
    return h;
  }

}
