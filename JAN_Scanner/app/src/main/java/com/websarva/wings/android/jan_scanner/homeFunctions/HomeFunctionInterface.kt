package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.compose.runtime.Composable

interface HomeFunctionInterface {
	@Composable fun FunctionMain(
		onBackClick: () -> Unit
	)
}