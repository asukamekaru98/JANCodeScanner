package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
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
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MainApp() {

	// 選択したタブのインデックスを保存する変数
	var selectedBottomTab by remember {
		mutableIntStateOf(0)
	}

	Scaffold(
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

enum class ListType {
	Column, Grid
}

@Composable
private fun SwitchTab(
	selectedBottomTab: Int,
	innderPadding: PaddingValues,
) {


	when (selectedBottomTab) {
		BottomTab.HOME.value -> {
			HomeScreenStatTrans(
				//listType= listType,
				innderPadding = innderPadding
			)
		}

		BottomTab.HISTORY.value -> {

		}
		/*
		BottomTab.SETTING.value -> {

		}
		*/
		else -> {
			throw IllegalArgumentException("存在しないタブが選択されました")
		}
	}
}

/**
 * ボトムタブ
 */
enum class BottomTab(val value: Int) {
	HOME(0),
	HISTORY(1),
	//SETTING(2),
}

/**
 * ボトムタブのアイテム
 */
data class BottomNavItem(
	val title: String,
	val route: String,
	val selectedIcon: ImageVector,
	val unSelectedIcon: ImageVector,
)

val bottomNavItems = listOf(
	BottomNavItem(
		title = "ホーム",
		route = "scanner",
		selectedIcon = Icons.Filled.Home,
		unSelectedIcon = Icons.Outlined.Home,
	),
	BottomNavItem(
		title = "履歴",
		route = "scanner",
		selectedIcon = Icons.Filled.Refresh,
		unSelectedIcon = Icons.Outlined.Refresh,
	),
	/*BottomNavItem(
		title = "設定",
		route = "scnner",
		selectedIcon = Icons.Filled.Settings,
		unSelectedIcon = Icons.Outlined.Settings,
	),*/
)