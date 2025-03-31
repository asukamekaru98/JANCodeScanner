package com.websarva.wings.android.jan_scanner.layout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunction
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionID
import kotlinx.serialization.Serializable
import java.util.SortedMap

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(homeFunctions: SortedMap<HomeFunctionID, List<HomeFunction>>)  {
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
				}
			)
		}

		// 機能画面
		for (section in homeFunctions) {
			for (sample in section.value) {
				composable(route = sample.route) {
					Scaffold(Modifier.fillMaxSize()) { innerPadding ->
						Box(Modifier.padding(innerPadding)) {
							sample.content()
						}
					}
				}
			}
		}
	}
}

@Composable
fun SwitchFunctionScreen(
	id: HomeFunctionID,
	innderPadding: PaddingValues,
)
{

	Log.d("SwitchFunctionScreen", "SwitchFunctionScreen")

	when(id){
		HomeFunctionID.JAN -> {
			JanScreen(
				//innderPadding = innderPadding
			)
		}
		HomeFunctionID.ISBN -> {
			IsbnScreen(
				//innderPadding = innderPadding
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