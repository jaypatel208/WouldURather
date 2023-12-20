package dev.jay.wouldurather.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jay.wouldurather.ui.components.LoadingText
import dev.jay.wouldurather.ui.components.NoNetworkComponent
import dev.jay.wouldurather.ui.viewmodel.DilemmaViewModel
import dev.jay.wouldurather.utilities.CoreUtility
import dev.jay.wouldurather.utilities.ResourceState

@Composable
fun HomeScreen(dilemmaViewModel: DilemmaViewModel = hiltViewModel()) {
    if (CoreUtility.isInternetConnected(LocalContext.current)) {
        val dilemmaRes by dilemmaViewModel.dilemma.collectAsState()

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            when (dilemmaRes) {
                is ResourceState.Loading -> {
                    LoadingText()
                }

                is ResourceState.Success -> {
                    val dilemmaResponse = (dilemmaRes as ResourceState.Success).data
                    Log.d(CoreUtility.TAG, "$dilemmaResponse")
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