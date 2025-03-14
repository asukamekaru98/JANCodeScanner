package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun HomeStatTrans(

){
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Route.HomeListScreen,
	) {
		composable<Route.HomeListScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
		composable<Route.HomeFunctionScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
		composable<Route.HomeSendScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack()}
			)
		}
	}
}

object Route {
	@Serializable
	data object HomeListScreen

	@Serializable
	data class HomeFunctionScreen(
		val name: String
	)

	@Serializable
	data class HomeSendScreen(
		val url: String
	)
}