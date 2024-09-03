package eu.kinol.stefanocounter.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId



class Converters {
    private val zoneId: ZoneId = ZoneId.systemDefault()

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return if (value == null) null else LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}