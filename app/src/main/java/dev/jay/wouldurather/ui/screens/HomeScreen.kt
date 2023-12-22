package dev.jay.wouldurather.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jay.wouldurather.R
import dev.jay.wouldurather.ui.components.FinalGameScreen
import dev.jay.wouldurather.ui.components.LoadingText
import dev.jay.wouldurather.ui.components.NoNetworkComponent
import dev.jay.wouldurather.ui.theme.poppinsHeaderQuestionFontFamily
import dev.jay.wouldurather.ui.viewmodel.DilemmaViewModel
import dev.jay.wouldurather.utilities.CoreUtility
import dev.jay.wouldurather.utilities.ResourceState

@Composable
fun HomeScreen(dilemmaViewModel: DilemmaViewModel = hiltViewModel()) {
    if (CoreUtility.isInternetConnected(LocalContext.current)) {
        val dilemmaRes by dilemmaViewModel.dilemma.collectAsState()

        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
            when (dilemmaRes) {
                is ResourceState.Loading -> {
                    LoadingText()
                }

                is ResourceState.Success -> {
                    val dilemmaResponse = (dilemmaRes as ResourceState.Success).data
                    Log.d(CoreUtility.TAG, "$dilemmaResponse")
                    val votes =
                        CoreUtility.getVotePercentages(dilemmaResponse.option1Votes, dilemmaResponse.option2Votes)
                    SetUpScreen(
                        votePercentage1 = votes.first,
                        question1 = dilemmaResponse.option1,
                        votePercentage2 = votes.second,
                        question2 = dilemmaResponse.option2,
                        showVotesPercentage = false
                    )
                }

                is ResourceState.Error -> {
                    val error = (dilemmaRes as ResourceState.Error)
                    Log.d(CoreUtility.TAG, "HomeScreen ResourceState.Error: $error")
                }
            }
        }
    } else {
        NoNetworkComponent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetUpScreen(
    votePercentage1: String,
    question1: String,
    votePercentage2: String,
    question2: String,
    showVotesPercentage: Boolean
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(
                        id = R.string.would_u_rather
                    ),
                    fontFamily = poppinsHeaderQuestionFontFamily,
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
        )
    }) {
        FinalGameScreen(votePercentage1, question1, votePercentage2, question2, showVotesPercentage)
    }
}