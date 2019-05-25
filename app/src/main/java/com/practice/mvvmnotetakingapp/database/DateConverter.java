package com.practice.mvvmnotetakingapp.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long dateToTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date timeStampToDate(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }
}
