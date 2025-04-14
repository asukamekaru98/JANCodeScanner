package com.websarva.wings.android.jan_scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.websarva.wings.android.jan_scanner.layout.HomeMenu
import com.websarva.wings.android.jan_scanner.ui.theme.JAN_ScannerTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			JAN_ScannerTheme {
				AppNavHost()
				//HomeScreen(homeFunctions = HomeFunctions)
			}
		}
	}
}

@Composable
fun AppNavHost(
	navController: NavHostController = rememberNavController(),
	startDestination: String = "home"
) {
	NavHost(navController = navController, startDestination = startDestination) {
		homeScreen()
	}
}

private fun NavGraphBuilder.homeScreen() {
	navigation(route = "home", startDestination = "home/entry") {
		composable("home/entry") {
			HomeScreen()
		}
	}
}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
	val nestedNavController = rememberNavController()
	val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
	val currentTab = navBackStackEntry?.destination?.route
	//var selectedItem by remember { mutableIntStateOf(0) }
	Scaffold(
		bottomBar = {
			NavigationBar {
				MainScreenTab.entries.forEachIndexed { index, item ->
					NavigationBarItem(
						icon = { Icon(item.icon, contentDescription = item.label) },
						label = { Text(item.label) },
						selected = currentTab == item.id,
						onClick = { nestedNavController.navigate(item.id) }
					)
				}
			}
		}
	) {
		Box(modifier = Modifier.padding(it)) {
			NavHost(
				navController = nestedNavController,
				startDestination = "main/home",
				modifier = Modifier,
			) {
				composable("main/home") {
					Text("main/home")
				}
				composable("main/list") {
					HomeMenu()
				// Text("main/list")
				}
				composable("main/about") {
					Text("main/about")
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
	JAN_ScannerTheme {
		HomeScreen()
	}
}


/*
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
 */