package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun MainApp(){
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Route.ScannerScreen,
	) {
		composable<Route.ScannerScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
		composable<Route.PrintHistoryScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
		composable<Route.ProdInfoScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
	}
}

object Route {
	@Serializable
	data object ScannerScreen

	@Serializable
	data class PrintHistoryScreen(
		val name: String
	)

	@Serializable
	data class ProdInfoScreen(
		val url: String
	)
}
