package eu.kinol.stefanocounter.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

    suspend fun addProfile(profile: Profile) = profileDao.addProfile(profile)
    val getProfile:Flow<Profile> = profileDao.getProfile()
}