package com.websarva.wings.android.jan_scanner.isbnScanner

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionInterface
import com.websarva.wings.android.jan_scanner.layout.ScannerCamera

class FunctionISBNScanner: HomeFunctionInterface {
	@Composable
	override fun FunctionMain(
		onBackClick: () -> Unit
	) {
		FunctionScreen()
	}
}

@Composable
private fun FunctionScreen(){
	Scaffold(
		topBar = {
			Text("JANスキャナー")

		},
		content = { innerPadding ->
			ScannerCamera(
				modifier = Modifier.padding(innerPadding)
			)
		}
	)
}