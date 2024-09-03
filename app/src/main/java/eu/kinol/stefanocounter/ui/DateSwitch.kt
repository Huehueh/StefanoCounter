package eu.kinol.stefanocounter.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.kinol.stefanocounter.data.CalculationDate


@Composable
fun DateSwitch(
    calculationDate: CalculationDate,
    onCalculationDateChange:(CalculationDate)->Unit
) {

    var cal by remember {
        mutableStateOf(calculationDate)
    }
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            modifier = Modifier
                .height(100.dp)
                .scale(2f),
            checked = (cal == CalculationDate.BIRTH),
            onCheckedChange = { checked ->
                Log.i("AAAA", "checked $checked")
                cal = if (checked) CalculationDate.BIRTH else CalculationDate.PERIOD
                onCalculationDateChange(cal)
            },
            thumbContent = if (cal == CalculationDate.BIRTH) {
                {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            },
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                checkedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedBorderColor = MaterialTheme.colorScheme.secondary,
                checkedBorderColor = MaterialTheme.colorScheme.secondary,
                checkedIconColor = MaterialTheme.colorScheme.inverseOnSurface,
                uncheckedIconColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        )
        Text(text = cal.mname())
    }
}

@Preview
@Composable
fun DateSwitchPreviewBirth() {
    DateSwitch(
        calculationDate = CalculationDate.BIRTH,
        onCalculationDateChange = {}
    )
}

@Preview
@Composable
fun DateSwitchPreviewPeriod() {
    DateSwitch(
        calculationDate = CalculationDate.PERIOD,
        onCalculationDateChange = {}
    )
}