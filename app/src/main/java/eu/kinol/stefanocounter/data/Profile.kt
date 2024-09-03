package eu.kinol.stefanocounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.kinol.stefanocounter.Constants.DATABASE_TABLE
import java.time.LocalDate

@Entity(tableName = DATABASE_TABLE)
data class Profile (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val profileName: String,
    val birthDate: LocalDate
) {

}