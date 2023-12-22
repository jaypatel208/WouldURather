package dev.jay.wouldurather.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.jay.wouldurather.R
import dev.jay.wouldurather.ui.theme.BlueChoice
import dev.jay.wouldurather.ui.theme.RedChoice
import dev.jay.wouldurather.ui.theme.poppinsOrFontFamily
import dev.jay.wouldurather.ui.theme.poppinsPercentageFontFamily
import dev.jay.wouldurather.ui.theme.poppinsQuestionFontFamily

@Composable
fun LoadingText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.loading),
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun NoNetworkComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_network), contentDescription = null
        )

        NormalTextComponent(textValue = stringResource(R.string.no_internet), centerAligned = true)
    }
}

@Composable
fun NormalTextComponent(textValue: String, centerAligned: Boolean = false) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(), text = textValue, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (centerAligned) TextAlign.Center else TextAlign.Start
        )
    )
}

@Composable
fun ChoiceComponent(color: Color, shoeVotesPercentage: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (shoeVotesPercentage) {
                Text(
                    text = "89 %",
                    color = Color.White,
                    fontFamily = poppinsPercentageFontFamily,
                    fontSize = 64.sp
                )
            }

            Text(
                text = "Ability to heal others and not yourself",
                color = Color.White,
                fontFamily = poppinsQuestionFontFamily,
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 16.dp),
                lineHeight = 30.sp,
                maxLines = 3
            )
        }
    }
}

@Composable
fun OrDividerComponent() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .height(5.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.or),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = poppinsOrFontFamily
                )
            }
        }
    }
}

@Composable
fun FinalGameScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        TwoChoicesScreen()
        OrDividerComponent()
    }
}

@Composable
fun TwoChoicesScreen() {
    Column {
        Row(modifier = Modifier.weight(1f)) {
            ChoiceComponent(color = RedChoice, shoeVotesPercentage = true)
        }
        Row(modifier = Modifier.weight(1f)) {
            ChoiceComponent(color = BlueChoice, shoeVotesPercentage = true)
        }
    }
}