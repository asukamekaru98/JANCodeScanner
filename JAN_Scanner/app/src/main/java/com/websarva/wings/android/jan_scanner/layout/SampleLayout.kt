package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NextBackButton(
	onBackClick: () -> Unit,
	onNextClick: () -> Unit
) {
	Row {
		Button(
			onClick = onBackClick,
			colors = ButtonDefaults.buttonColors(),
		) {
			Text("戻る")
		}
		Spacer(modifier = Modifier.width(8.dp)) // ボタン間の間隔を調整
		Button(
			onClick = onNextClick,
			colors = ButtonDefaults.buttonColors(),
		) {
			Text("進む")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun Prev() {
	NextBackButton(
		onBackClick = {},
		onNextClick = {}
	)
}