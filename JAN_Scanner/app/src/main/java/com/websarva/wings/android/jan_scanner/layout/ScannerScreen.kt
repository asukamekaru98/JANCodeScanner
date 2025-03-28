package com.websarva.wings.android.jan_scanner.layout

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.websarva.wings.android.jan_scanner.scanner.BarcodeScannerActivity


@Composable
fun ScannerScreen(
	onBackClick: () -> Unit,
) {

}



@Preview
@Composable
fun ScannerCamera(
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


