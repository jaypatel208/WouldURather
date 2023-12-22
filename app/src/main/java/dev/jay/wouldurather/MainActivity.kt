package dev.jay.wouldurather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.jay.wouldurather.ui.navigation.AppNavigationGraph
import dev.jay.wouldurather.ui.theme.WouldURatherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            WouldURatherTheme {
                SetStatusBarColor(color = Color.Black)
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    AppEntryPoint()
                }
            }
        }
    }
}

@Composable
private fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color
        )
    }
}

@Composable
private fun AppEntryPoint() {
    AppNavigationGraph()
}