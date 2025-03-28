package com.websarva.wings.android.jan_scanner.layout

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JanScreen(
	//innderPadding: PaddingValues
)
{
	Log.d("JanScreen", "JanScreen")

	Scaffold(
		topBar = {
			TopAppBar(title = { Text("JANスキャナー") })
		}
	){innerPadding->
		JanScreenStatTrans(innerPadding)
	}


}

@Composable
fun JanScreenStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = JanScreenRoute.ScannerScreen,
	) {
		composable<JanScreenRoute.ScannerScreen> {
			Log.d("JanScreenStatTrans", "ScannerScreen")

			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(JanScreenRoute.SendScreen)
				}
			)
		}

		composable<JanScreenRoute.SendScreen> {
			Log.d("JanScreenStatTrans", "SendScreen")

			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(JanScreenRoute.SendScreen)
				}
			)
		}
	}
}


object JanScreenRoute {
	@Serializable
	data object ScannerScreen

	@Serializable
	data object SendScreen
	//data class SendScreen(
	//	val url: String
	//)
}