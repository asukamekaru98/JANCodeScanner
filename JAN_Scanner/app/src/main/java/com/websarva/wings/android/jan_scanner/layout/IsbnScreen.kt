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
fun IsbnScreen(
	//innderPadding: PaddingValues
)
{
	Log.d("JanScreenStatTrans", "ISBN")
	Scaffold(
		topBar = {
			TopAppBar(title = { Text("JANスキャナー") })
		}
	) { innerPadding ->
		IsbnScreenStatTrans(innerPadding)
	}
}

@Composable
fun IsbnScreenStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = IsbnScreenRoute.ScannerScreen,
	) {
		composable<IsbnScreenRoute.ScannerScreen> {
			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(IsbnScreenRoute.SendScreen)
				}
			)
		}

		composable<IsbnScreenRoute.SendScreen> {
			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(IsbnScreenRoute.SendScreen)
				}
			)
		}
	}
}


object IsbnScreenRoute {
	@Serializable
	data object ScannerScreen

	@Serializable
	data object SendScreen
	//data class SendScreen(
	//	val url: String
	//)
}