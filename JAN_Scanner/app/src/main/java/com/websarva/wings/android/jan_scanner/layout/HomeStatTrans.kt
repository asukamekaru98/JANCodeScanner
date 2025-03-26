package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionID
import kotlinx.serialization.Serializable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
	Scaffold(
		topBar = {
			TopAppBar(title = { Text("ホーム") })
		}
	) { innerPadding ->
		HomeScreenStatTrans(
			innderPadding = innerPadding
		)
	}
}

@Composable
fun HomeScreenStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = HomeScreenRoute.ListScreen,
	) {
		// 機能メニュー
		composable<HomeScreenRoute.ListScreen> {
			HomeMenu(
				onHomeFunctionClick = { homeFunction ->
					navController.navigate(
						HomeScreenRoute.FunctionScreen(id = homeFunction.id)
					)
				},
				innerPadding = innderPadding
			)
		}

		// 機能画面
		composable<HomeScreenRoute.FunctionScreen> { homeBackStackEntry ->
			val functionScreen: HomeScreenRoute.FunctionScreen = homeBackStackEntry.toRoute()

			SwitchFunctionScreen(functionScreen.id, innderPadding)

		}
	}
}

@Composable
fun SwitchFunctionScreen(
	id: HomeFunctionID,
	innderPadding: PaddingValues,
)
{
	when(id){
		HomeFunctionID.JAN -> {
			JanScreen(
				innderPadding = innderPadding
			)
		}
		HomeFunctionID.ISBN -> {
			IsbnScreen(
				innderPadding = innderPadding
			)
		}
		else -> {
			throw IllegalArgumentException("存在しない機能が選択されました")
		}
	}
}


object HomeScreenRoute {
	@Serializable
	data object ListScreen

	@Serializable
	//data object FunctionScreen
	data class FunctionScreen(
		val id: HomeFunctionID
	)
}