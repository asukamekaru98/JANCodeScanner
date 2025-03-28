package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.layout.IsbnScreen
import com.websarva.wings.android.jan_scanner.layout.JanScreen

enum class HomeFunctionID(val value: Int) {
	JAN(0),
	ISBN(1),
	WEATHER(2),
	HOGE(1)
}

object HomeFunctionRoute {
	const val ListScreen = "list"
	const val FunctionScreen = "function/{id}"
	const val WeatherScreen = "weather"
	const val IsbnScreen = "isbn"
	const val JanScreen = "jan"
	const val HogeScreen = "hoge"
	const val SendScreen = "send"
	const val ScannerScreen = "scanner"
	const val ResultScreen = "result"
	const val SettingScreen = "setting"
	const val HelpScreen = "help"
	const val AboutScreen = "about"
	const val LogScreen = "log"
}


data class HomeFunction(
	val id: HomeFunctionID,
	val title: String,   // 機能名
	@DrawableRes val icon: Int, // 選択中のアイコン画像
	val content: @Composable () -> Unit
)

val HomeFunctions = listOf(
	HomeFunction(
		id = HomeFunctionID.JAN,
		title = "JANスキャナー",
		icon = R.drawable.icon_barcode,
		content = { JanScreen() }
	),
	HomeFunction(
		id = HomeFunctionID.ISBN,
		title = "ISBNスキャナー",
		icon = R.drawable.icon_book,
		content = { IsbnScreen() }
	),
	//HomeFunction(
	//	id = HomeFunctionID.WEATHER,
	//	title = "天気",
	//	icon = R.drawable.icon_weather
	//),
)

@Composable
fun HomeFunction(
	name: String,

	) {

}