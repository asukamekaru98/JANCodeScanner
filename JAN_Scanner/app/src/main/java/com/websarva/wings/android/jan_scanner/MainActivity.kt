package com.websarva.wings.android.jan_scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunction
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionID
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctions
import com.websarva.wings.android.jan_scanner.layout.HomeMenu
import com.websarva.wings.android.jan_scanner.ui.theme.JAN_ScannerTheme
import java.util.SortedMap

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			JAN_ScannerTheme {
				HomeScreen(homeFunctions = HomeFunctions)
			}
		}
	}
}

@Composable
fun HomeScreen(homeFunctions: SortedMap<String, List<HomeFunction>>)  {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = HomeFunctionID.HOME.toString(),
	) {
		// 機能メニュー
		composable(route = HomeFunctionID.HOME.toString()) {
			HomeMenu(
				homeFunctions = homeFunctions,
				onHomeFunctionClick = { homeFunction ->
					navController.navigate(
						homeFunction.route
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