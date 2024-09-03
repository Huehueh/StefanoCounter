package eu.kinol.stefanocounter.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.kinol.stefanocounter.data.ProfileScreenState
import eu.kinol.stefanocounter.profile4Week
import kotlin.math.absoluteValue


@Composable
fun MainContent(profileData: ProfileScreenState) {
    val nestedScrollInteropConnection: NestedScrollConnection = rememberNestedScrollInteropConnection()
    val scrollState = rememberScrollState()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollInteropConnection)
            .systemBarsPadding()
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                ProfileInfo(profileData = profileData)
            }
        }
    }
}

fun ordinalOf(i: Int) : String{
    val iAbs = i.absoluteValue // if you want negative ordinals, or just use i
    return "$i" + if (iAbs % 100 in 11..13) "th" else when (iAbs % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}

fun toRoman(int: Int) : String{
    return when(int) {
        1 -> "I"
        2 -> "II"
        3 -> "III"
        else -> ""
    }
}


@Composable
fun Photo(profileData: ProfileScreenState) {
    profileData.photo?.let {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(
                    start = 5.dp,
                    top = 5.dp,
                    end = 5.dp
                )
                .clip(CircleShape),
            painter = painterResource(id = it),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileInfo(profileData: ProfileScreenState,
                modifier: Modifier = Modifier
){
    Box(modifier = modifier,

        ){
        Column(
            modifier = modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            profileData.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Photo(profileData = profileData)
            Spacer(modifier = Modifier.height(8.dp))
            Week(profileData = profileData)
            Spacer(modifier = Modifier.height(8.dp))
            Month(profileData = profileData)
            Spacer(modifier = Modifier.height(8.dp))
            Trimester(profileData = profileData)
            profileData.childDescription?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodySmall
                )

            }
        }
    }


}

@Preview(widthDp = 360, heightDp = 680)
@Composable
fun ProfileInfoPreview() {

    ProfileInfo(
        profileData = profile4Week,
        modifier =  Modifier
            .background(MaterialTheme.colorScheme.background))
}

@Composable
fun Week(profileData: ProfileScreenState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(BorderStroke(3.dp, MaterialTheme.colorScheme.secondary))
//            .background(MaterialTheme.colorScheme.background, shape = CircleShape)
            .clip(CircleShape)
            .padding(5.dp)
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ordinalOf(profileData.week) ,
                modifier = modifier,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(text = "week",
                modifier = modifier,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

}

@Composable
fun Month(profileData: ProfileScreenState, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.End) {
        Text(
            text = ordinalOf(profileData.month),
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "month",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun Trimester(profileData: ProfileScreenState, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = toRoman(profileData.trimester),
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "trimester",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}