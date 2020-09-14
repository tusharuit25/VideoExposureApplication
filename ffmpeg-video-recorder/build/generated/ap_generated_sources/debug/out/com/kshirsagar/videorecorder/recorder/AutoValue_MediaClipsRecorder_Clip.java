
package com.kshirsagar.videorecorder.recorder;

import com.kshirsagar.videorecorder.camera.CameraControllerI;
import java.io.File;

 final class AutoValue_MediaClipsRecorder_Clip extends MediaClipsRecorder.Clip {

  private final File file;
  private final CameraControllerI.Facing facing;
  private final int orientationDegrees;
  private final long durationMillis;
  private final long bytes;

  AutoValue_MediaClipsRecorder_Clip(
      File file,
      CameraControllerI.Facing facing,
      int orientationDegrees,
      long durationMillis,
      long bytes) {
    if (file == null) {
      throw new NullPointerException("Null file");
    }
    this.file = file;
    if (facing == null) {
      throw new NullPointerException("Null facing");
    }
    this.facing = facing;
    this.orientationDegrees = orientationDegrees;
    this.durationMillis = durationMillis;
    this.bytes = bytes;
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public CameraControllerI.Facing getFacing() {
    return facing;
  }

  @Override
  public int getOrientationDegrees() {
    return orientationDegrees;
  }

  @Override
  public long getDurationMillis() {
    return durationMillis;
  }

  @Override
  public long getBytes() {
    return bytes;
  }

  @Override
  public String toString() {
    return "Clip{"
        + "file=" + file + ", "
        + "facing=" + facing + ", "
        + "orientationDegrees=" + orientationDegrees + ", "
        + "durationMillis=" + durationMillis + ", "
        + "bytes=" + bytes
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof MediaClipsRecorder.Clip) {
      MediaClipsRecorder.Clip that = (MediaClipsRecorder.Clip) o;
      return (this.file.equals(that.getFile()))
           && (this.facing.equals(that.getFacing()))
           && (this.orientationDegrees == that.getOrientationDegrees())
           && (this.durationMillis == that.getDurationMillis())
           && (this.bytes == that.getBytes());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.file.hashCode();
    h *= 1000003;
    h ^= this.facing.hashCode();
    h *= 1000003;
    h ^= this.orientationDegrees;
    h *= 1000003;
    h ^= (int) ((this.durationMillis >>> 32) ^ this.durationMillis);
    h *= 1000003;
    h ^= (int) ((this.bytes >>> 32) ^ this.bytes);
    return h;
  }

}
