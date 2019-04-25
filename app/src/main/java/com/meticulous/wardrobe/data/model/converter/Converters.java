package com.meticulous.wardrobe.data.model.converter;

import android.arch.persistence.room.TypeConverter;

import java.io.File;

/**
 * Created by c74241 on 8/14/17.
 */

public class Converters {
    @TypeConverter
    public static File getFileLocation(String fileLocation){
        return new File(fileLocation);
    }

    @TypeConverter
    public static String setFileLocation(File uri){
        return uri.getPath();
    }
}
