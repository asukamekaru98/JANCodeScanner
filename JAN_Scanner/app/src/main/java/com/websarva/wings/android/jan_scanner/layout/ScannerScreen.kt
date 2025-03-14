package com.websarva.wings.android.jan_scanner.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.scanner.BarcodeScannerActivity
import kotlinx.coroutines.launch

enum class BottomTab(val value: Int){
	HOME(0),
	HISTORY(1),
	SETTING(2),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
	onBackClick: () -> Unit,
) {


}



@Preview
@Composable
private fun ScannerCamera(
	modifier: Modifier = Modifier,
) {
	val context = LocalContext.current
	val intent = Intent(context, BarcodeScannerActivity::class.java)

	Box(modifier = modifier) {

	}
	LaunchedEffect(Unit){
		context.startActivity(intent)
	}
}

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
	BottomNavItem(
		title = "設定",
		route = "scnner",
		selectedIcon = Icons.Filled.Settings,
		unSelectedIcon = Icons.Outlined.Settings,
	),
)

