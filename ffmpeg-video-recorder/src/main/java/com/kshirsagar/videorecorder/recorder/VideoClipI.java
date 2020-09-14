package com.kshirsagar.videorecorder.recorder;

import com.kshirsagar.videorecorder.camera.CameraControllerI;

import java.io.File;

/**
 * Video clip with some additional metadata.
 */
interface VideoClipI {
    File getFile();
    CameraControllerI.Facing getFacing();
    int getOrientationDegrees();
}
