package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionInterface
import kotlinx.serialization.Serializable

@Composable
fun HomeScreenStatTrans(
	//listType: ListType,
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = HomeRoute.ListScreen,
	) {
		composable<HomeRoute.ListScreen> {
			HomeScreen(
				onHomeFunctionClick = { HomeFunction ->
					navController.navigate(
						HomeRoute.FunctionScreen(HomeFunction.homeFunction)
					)
				},
				//listType = listType,
				innerPadding = innderPadding
			)
		}
		composable<HomeRoute.FunctionScreen>(

		) { homeBackStackEntry ->
			val homeFunction: HomeRoute.FunctionScreen
			= homeBackStackEntry.toRoute()

			homeFunction.homeFunction.FunctionMain(
				onBackClick = { navController.popBackStack() }
			)



			//val HomeFunction =
		}
		composable<HomeRoute.SendScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack() }
			)
		}
	}
}



object HomeRoute {
	@Serializable
	data object ListScreen

	@Serializable
	data class FunctionScreen(
		val homeFunction: HomeFunctionInterface
	)

	@Serializable
	data class SendScreen(
		val url: String
	)
}