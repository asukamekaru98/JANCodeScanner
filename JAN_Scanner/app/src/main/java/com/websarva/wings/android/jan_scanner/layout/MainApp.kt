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
				onClick = { navController.popBackStack()}
			)
		}
		composable<Route.PrefectureDetail>(
			enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
			exitTransition = null,
			popEnterTransition = null,
			popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }
		) { navBackStackEntry ->
			val prefectureDetail: Route.PrefectureDetail
					= navBackStackEntry.toRoute()
			PrefectureDetailScreen(
				prefectureName = prefectureDetail.name,
				onBackClick = { navController.popBackStack() },
				onOpenWikiClick = { wikiUrl ->
					navController.navigate(
						Route.WikiView(url = wikiUrl)
					)
				}
			)
		}
	}
}

object Route {
	@Serializable
	data object ScannerScreen

	@Serializable
	data class PrefectureDetail(
		val name: String
	)

	@Serializable
	data class WikiView(
		val url: String
	)
}
