package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun HomeScreenStatTrans(
	//listType: ListType,
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = Route.HomeListScreen,
	) {
		composable<Route.HomeListScreen> {
			HomeScreen(
				onHomeFunctionClick = { HomeFunction ->
					navController.navigate(
						Route.HomeFunctionScreen(HomeFunction.name)
					)
				},
				//listType = listType,
				innerPadding = innderPadding
			)
		}
		composable<Route.HomeFunctionScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack() }
			)
		}
		composable<Route.HomeSendScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack() }
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