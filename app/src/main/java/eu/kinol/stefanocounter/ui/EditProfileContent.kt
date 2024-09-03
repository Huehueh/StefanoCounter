package eu.kinol.stefanocounter.ui

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.kinol.stefanocounter.Constants
import eu.kinol.stefanocounter.R
import eu.kinol.stefanocounter.data.CalculationDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    profileName: String,
    onProfileNameChange:(String)->Unit,
    calculationDate: CalculationDate,
    onCalculationDateChange: (CalculationDate)->Unit,
    date: LocalDate,
    onDateChange:(LocalDate)->Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .border(2.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp))
            .padding(3.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.9f),
                value = profileName, 
                onValueChange = onProfileNameChange,
                label = { Text(text = stringResource(id = R.string.profile_name))},
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium
            )
            DateSwitch(calculationDate, onCalculationDateChange)
            MyCalendar (date, onDateChange)
        }
    }
}

@Composable
@Preview
fun EditProfileContentPreview() {
    EditProfileContent(
        profileName = "hue",
        onProfileNameChange = {},
        calculationDate = CalculationDate.BIRTH,
        onCalculationDateChange = {},
        date = LocalDate.now(),
        onDateChange = {}
    )
}

@Composable
fun MyCalendar(date:LocalDate, setupDate:(LocalDate) -> Unit) {

    var chosenDate by remember { mutableStateOf(LocalDate.now()) }
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyy")

    Calendar.getInstance().let {
        val mDatePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                chosenDate = LocalDate.of(mYear, mMonth+1, mDayOfMonth)
                setupDate(chosenDate)
            },
            it.get(Calendar.YEAR),
            it.get(Calendar.MONTH),
            it.get(Calendar.YEAR)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                mDatePickerDialog.show()
            }) {
                Text(text = Constants.chooseDate)
            }

            Text(
                text = chosenDate.format(formatter),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}