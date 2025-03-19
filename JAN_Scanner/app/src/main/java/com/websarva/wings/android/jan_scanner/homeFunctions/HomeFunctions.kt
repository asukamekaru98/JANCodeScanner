package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.websarva.wings.android.jan_scanner.R

data class HomeFunction(
	val name: String,   // 機能名
	fun functionval : @Composable,
	@DrawableRes val icon: Int, // 選択中のアイコン画像
)

val HomeFunctions = listOf(
	HomeFunction(
		name = "JANスキャナー",
		icon = R.drawable.icon_barcode
	),
	HomeFunction(
		name = "ISBNスキャナー",
		icon = R.drawable.icon_book
	),
	HomeFunction(
		name = "天気",
		icon = R.drawable.icon_weather
	),
)

@Composable
fun HomeFunction(
	name:String,

){

}