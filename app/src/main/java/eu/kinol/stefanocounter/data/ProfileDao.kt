package eu.kinol.stefanocounter.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert
    suspend fun addProfile(profile: Profile)

    @Query("SELECT * FROM profiles_table LIMIT 1")
    fun getProfile() : Flow<Profile>
}