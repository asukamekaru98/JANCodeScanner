package com.websarva.wings.android.jan_scanner.layout

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

enum class MainScreenTab(
	val id: String,
	val icon: ImageVector,
	val label: String
) {
	Home(
		id = "main/home",
		icon = Icons.Default.Home,
		label = "Home"
	),
	List(
		id = "main/list",
		icon = Icons.Default.Search,
		label = "List"
	),
	Settings(
		id = "main/about",
		icon = Icons.Default.Person,
		label = "About"
	)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JanScreen(
	//innderPadding: PaddingValues
) {
	val nestedNavController = rememberNavController()
	var selectedItem by remember { mutableIntStateOf(0) }
	Scaffold(
		bottomBar = {
			NavigationBar {
				MainScreenTab.values().forEachIndexed { index, item ->
					NavigationBarItem(
						icon = { Icon(item.icon, contentDescription = item.label) },
						label = { Text(item.label) },
						selected = selectedItem == index,
						onClick = { selectedItem = index }
					)
				}
			}
		}
	) {
		Box(modifier = Modifier.padding(it)) {
			Text("main")

		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
	JanScreen()
}

@Composable
fun JanScreenStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = JanScreenRoute.ScannerScreen,
	) {
		composable<JanScreenRoute.ScannerScreen> {
			Log.d("JanScreenStatTrans", "ScannerScreen")

			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(JanScreenRoute.SendScreen)
				}
			)
		}

		composable<JanScreenRoute.SendScreen> {
			Log.d("JanScreenStatTrans", "SendScreen")

			NextBackButton(
				onBackClick = {
					navController.popBackStack()
				},
				onNextClick = {
					navController.navigate(JanScreenRoute.SendScreen)
				}
			)
		}
	}
}


object JanScreenRoute {
	@Serializable
	data object ScannerScreen

	@Serializable
	data object SendScreen
	//data class SendScreen(
	//	val url: String
	//)
}