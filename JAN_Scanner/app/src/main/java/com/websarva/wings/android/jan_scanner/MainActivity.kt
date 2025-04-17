package com.websarva.wings.android.jan_scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.websarva.wings.android.jan_scanner.data.HomeSection
import com.websarva.wings.android.jan_scanner.layout.HomeMenu
import com.websarva.wings.android.jan_scanner.layout.IsbnScreenStatTrans
import com.websarva.wings.android.jan_scanner.layout.JanScreen
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




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
	val navController = rememberNavController()
	val backStack by navController.currentBackStackEntryAsState()
	val currentRoute = backStack?.destination?.route

	// Home/Favorite/About のみボトムバー表示
	val showBottomBar = HomeSection.entries.any { it.id == currentRoute }

	Scaffold(
		bottomBar = {
			if (showBottomBar) {
				NavigationBar {
					HomeSection.entries.forEach { tab ->
						NavigationBarItem(
							icon = { Icon(tab.icon, contentDescription = tab.title) },
							label = { Text(tab.title) },
							selected = currentRoute == tab.id,
							onClick = { navController.navigate(tab.id) }
						)
					}
				}
			}
		}
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = HomeSection.HomeMenu.id,  // "home/home"
			modifier = Modifier.padding(innerPadding)
		) {
			// ─── Home メニュー
			composable(HomeSection.HomeMenu.id) { // "home/home"
				HomeMenu(
					innerPadding = innerPadding,
					onListClick = { hf ->
						// HomeFunction.route は "JAN"／"ISBN" なので小文字化
						navController.navigate(hf.id)
					}
				)
			}

			// ─── Favorite / About
			composable(HomeSection.HomeFavorite.id) {
				TopAppBar(title = { Text("お気に入り") })
			}
			composable(HomeSection.HomeAbout.id) {
				TopAppBar(title = { Text("About") })
			}

			// ─── JAN／ISBN 画面（ボトムバーは非表示）
			composable("home/menu/jan") {
				JanScreen(innerPadding)
			}
			composable("home/menu/isbn") {
				IsbnScreenStatTrans(innerPadding)
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