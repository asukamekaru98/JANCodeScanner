package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.websarva.wings.android.jan_scanner.R

enum class HomeFunctionID(val value: Int) {
	JAN(0),
	ISBN(1),
	WEATHER(2),
}


data class HomeFunction(
	val id: HomeFunctionID,
	val name: String,   // 機能名
	@DrawableRes val icon: Int, // 選択中のアイコン画像
)

val HomeFunctions = listOf(
	HomeFunction(
		id = HomeFunctionID.JAN,
		name = "JANスキャナー",
		icon = R.drawable.icon_barcode
	),
	HomeFunction(
		id = HomeFunctionID.ISBN,
		name = "ISBNスキャナー",
		icon = R.drawable.icon_book
	),
	HomeFunction(
		id = HomeFunctionID.WEATHER,
		name = "天気",
		icon = R.drawable.icon_weather
	),
)

@Composable
fun HomeFunction(
	name:String,

){

}