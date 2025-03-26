package com.websarva.wings.android.jan_scanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.websarva.wings.android.jan_scanner.layout.HomeScreen
import com.websarva.wings.android.jan_scanner.ui.theme.JAN_ScannerTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			JAN_ScannerTheme {
				HomeScreen()
			}
		}
	}
}

