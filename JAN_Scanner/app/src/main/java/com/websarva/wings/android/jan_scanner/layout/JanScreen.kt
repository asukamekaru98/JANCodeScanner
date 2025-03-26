package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun JanScreen(
	innderPadding: PaddingValues
)
{
	JanScreenStatTrans(innderPadding)
}

@Composable
fun JanScreenStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = IsbnScreenRoute.ScannerScreen,
	) {
		composable<IsbnScreenRoute.ScannerScreen> {
			//ISBNScannerFuncMain(
			//	//onBackClick = { navController.popBackStack() }
			//)
		}

		composable<IsbnScreenRoute.SendScreen> {
			//SendScreen(
			//	onBackClick = { navController.popBackStack() }
			//)
		}
	}
}


object JanScreenRoute {
	@Serializable
	data object ScannerScreen

	@Serializable
	data class SendScreen(
		val url: String
	)
}