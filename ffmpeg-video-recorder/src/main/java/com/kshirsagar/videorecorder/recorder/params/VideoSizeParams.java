package com.kshirsagar.videorecorder.recorder.params;

import com.kshirsagar.videorecorder.recorder.common.ImageSize;

/**
 * Common utils for {@link VideoSizeParamsI}.
 */
public final class VideoSizeParams {

    private VideoSizeParams() {}

    public static final class Builder {

        public static <T extends VideoSizeParamsI.BuilderI<T>> T setOnlyClassDefaults(T builder) {
            return builder.setVideoSize(ImageSize.UNDEFINED);
        }

        public static <T extends VideoSizeParamsI.BuilderI<T>> T mergeOnlyClass(
                T builder, VideoSizeParamsI params) {
            return builder.setVideoSize(params.getVideoSize());
        }

        private Builder() {}
    }
}
