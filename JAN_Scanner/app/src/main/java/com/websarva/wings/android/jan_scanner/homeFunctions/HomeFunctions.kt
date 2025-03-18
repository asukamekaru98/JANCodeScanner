package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.isbnScanner.FunctionISBNScanner

data class HomeFunction(
	val name: String,                           // 機能名
	val homeFunction: HomeFunctionInterface,    // 機能
	@DrawableRes val icon: Int,                 // 選択中のアイコン画像
)

val HomeFunctions = listOf(
	HomeFunction(
		name = "JANスキャナー",
		homeFunction = FunctionISBNScanner(),
		icon = R.drawable.icon_barcode
	),
	HomeFunction(
		name = "ISBNスキャナー",
		homeFunction = FunctionISBNScanner(),
		icon = R.drawable.icon_book
	),
	HomeFunction(
		name = "天気",
		homeFunction = FunctionISBNScanner(),
		icon = R.drawable.icon_weather
	),
)