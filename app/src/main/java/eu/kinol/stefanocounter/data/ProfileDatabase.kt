package eu.kinol.stefanocounter.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@Database(entities = [Profile::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProfileDatabase:RoomDatabase() {
    abstract fun profileDao(): ProfileDao

}