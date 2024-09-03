package eu.kinol.stefanocounter.data

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.kinol.stefanocounter.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.roundToInt

enum class CalculationDate(val intVal:Int)
{
    PERIOD(0) {
        override fun mname(): String = Constants.periodDate
    },
    BIRTH(1) {
        override fun mname(): String = Constants.birthDate
    };
    abstract fun mname() : String
    companion object {
        fun from(findValue: Int): CalculationDate = entries.first { it.intVal == findValue }
        fun mlist():List<String> = entries.map{it.mname()}
    }
}

@Immutable
data class ProfileScreenState(
    val week: Int,
    val month: Int,
    val trimester: Int,
    val name: String?,
    @DrawableRes val photo:Int?,
    val childDescription: String?,
    val todo: String?
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {


    var calculationDate = mutableStateOf(CalculationDate.PERIOD)
    var date = mutableStateOf(LocalDate.now())
    var birthDate = mutableStateOf(LocalDate.now())

    val pregnancyDays = 280

//    private val _profile = MutableStateFlow<Profile>()
//    val profile:StateFlow<Profile> = _profile
//
//    fun loadProfile() {
//        try {
//            viewModelScope.launch {
//                repository.getProfile.collect{
//                    _profile.value = it
//                }
//            }
//        }
//        catch (e:Exception) {
//            Log.i("Error $e loading profile")
//        }
//    }

    fun CalculateWeek(days: Int) : Int{
        return floor(days / 7.0).roundToInt()
    }

    fun CalculateWeekBasedOnPeriod(currentDate: LocalDate, periodDate:LocalDate) {
        val period = Period.between(periodDate, currentDate)
        val days = period.months * 30 + period.days
        birthDate.value = periodDate.plusDays(pregnancyDays.toLong())
    }

    fun CalculateWeekBasedOnBirthDate(currentDate: LocalDate, birthDate:LocalDate) {
        this.birthDate.value = birthDate
        val period = Period.between(currentDate, birthDate)
        val days = pregnancyDays - (period.months * 30 + period.days)
    }


    fun CalculateWeek(date: LocalDate) {
        val currentDate = LocalDate.now()
        if (calculationDate.value == CalculationDate.PERIOD)
        {
            CalculateWeekBasedOnPeriod(currentDate, date)
//            setupOutput("Obecnie mamy ${weeks+1} tydzień ($weeks t, $daysToWeek d). \nPrzewidywany termin porodu to ${birth.format(
//                DateTimeFormatter.ISO_DATE)}")

        }
        else if (calculationDate.value == CalculationDate.BIRTH)
        {
            CalculateWeekBasedOnBirthDate(currentDate, date)
//            setupOutput("Obecnie mamy ${weeks+1} tydzień ($weeks t, $daysToWeek d).")
        }
    }
}