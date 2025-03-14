package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.websarva.wings.android.jan_scanner.R
import kotlinx.serialization.Serializable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainApp(){

	// 選択したタブのインデックスを保存する変数
	var selectedBottomTab by remember {
		mutableIntStateOf(0)
	}

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("スキャン")
				}
			)
		},
		bottomBar = {
			NavigationBar {
				bottomNavItems.forEachIndexed { index, bottomNavItem ->
					NavigationBarItem(
						selected = (index == selectedBottomTab),
						onClick = {
							selectedBottomTab = index
						},
						icon = {
							Icon(
								if (index == selectedBottomTab) {
									bottomNavItem.selectedIcon
								} else {
									bottomNavItem.unSelectedIcon
								},
								contentDescription = bottomNavItem.title
							)
						},
						label = {
							Text(
								text = bottomNavItem.title
							)
						}

					)
				}
			}
		},
	) { innerPadding ->
		SwitchTab(
			selectedBottomTab = selectedBottomTab,
			innderPadding = innerPadding
		)
	}
}

@Composable
private fun SwitchTab(
	selectedBottomTab: Int,
	innderPadding: PaddingValues,
){

	when(selectedBottomTab){
		BottomTab.HOME.value -> {
			HomeStatTrans(
				innderPadding = innderPadding
			)
		}
		BottomTab.HISTORY.value -> {

		}
		BottomTab.SETTING.value -> {

		}
		else -> {
			throw IllegalArgumentException("存在しないタブが選択されました")
		}
	}
}

data class BottomNavItem(
	val title: String,
	val route: String,
	val selectedIcon: ImageVector,
	val unSelectedIcon: ImageVector,
)