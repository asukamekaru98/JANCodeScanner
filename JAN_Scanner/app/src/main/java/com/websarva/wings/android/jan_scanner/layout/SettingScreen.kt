package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingScreenBar() {
	TopAppBar(
		title = {
			Text("設定")
		}
	)
}