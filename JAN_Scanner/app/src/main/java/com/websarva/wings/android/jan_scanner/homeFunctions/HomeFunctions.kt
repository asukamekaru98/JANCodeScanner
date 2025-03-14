package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.homeFunctions.HogeFunction
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctionInterface

data class HomeFunction(
	val name: String,                                    // 機能名
	val homeFunctionInterface: HomeFunctionInterface,   // 機能
	@DrawableRes val icon: Int,                   // 選択中のアイコン画像
)

val HomeFunctions = listOf(
	HomeFunction(
		name = "JANスキャナー",
		homeFunctionInterface = HogeFunction(),
		icon = R.drawable.icon_barcode
	),
	HomeFunction(
		name = "ISBNスキャナー",
		homeFunctionInterface = HogeFunction(),
		icon = R.drawable.icon_book
	),
	HomeFunction(
		name = "天気",
		homeFunctionInterface = HogeFunction(),
		icon = R.drawable.icon_weather
	),
)