package com.example.utility;

import androidx.annotation.NonNull;
import com.google.auto.value.*;
import java.io.File;
import java.io.Serializable;



@AutoValue
public abstract class VideoFile implements Comparable<VideoFile>, Serializable {

    public abstract File getVideoFile();
    public abstract File getThumbnailFile();

    public void delete() {
        getVideoFile().delete();
        getThumbnailFile().delete();
    }

    @Override
    public int compareTo(@NonNull VideoFile file) {
        return (int)(file.getVideoFile().lastModified() - getVideoFile().lastModified());
    }

    public static VideoFile create(File videoFile, File thumbnailFile) {
        return new AutoValue_VideoFile(videoFile,thumbnailFile);
    }
}