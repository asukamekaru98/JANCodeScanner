package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.websarva.wings.android.jan_scanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
	onBackClick: () -> Unit,
) {

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("スキャン")
				},
				navigationIcon = {
					IconButton(onClick = onBackClick) {
						Icon(
							painter = painterResource(R.drawable.arrow_back),
							contentDescription = "戻る"
						)
					}
				}
			)
		},
		bottomBar = {
			BottomAppBar(
				containerColor = MaterialTheme.colorScheme.primaryContainer,
				contentColor = MaterialTheme.colorScheme.onPrimaryContainer,

			){
				Text("BottomAppBar")
			}
		},
	) { innerPadding ->
		ScannerCamera(
			modifier = Modifier.padding(innerPadding)
		)
	}
}

@Composable
private fun ScannerCamera(
	modifier: Modifier = Modifier,
) {

}