package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.layout.IsbnScreen
import com.websarva.wings.android.jan_scanner.layout.JanScreen

enum class HomeFunctionID {
	HOME,
	JAN,
	ISBN,
	WEATHER,
	HOGE;

	// このロジックで、ユニークな値にしている
	val value: Int
		get() = this.ordinal
}


data class HomeFunction(
	/*val id: HomeFunctionID,*/
	@DrawableRes val icon: Int,
	val title: String,
	val route: String,
	val content: @Composable () -> Unit
)

val HomeFunctions = listOf(
	HomeFunction(
		icon = R.drawable.icon_barcode,
		title = "JANスキャナー",
		route = HomeFunctionID.JAN.toString(),
		content = { JanScreen() }
	),
	HomeFunction(
		icon = R.drawable.icon_book,
		title = "ISBNスキャナー",
		route = HomeFunctionID.ISBN.toString(),
		content = { IsbnScreen() }
	),
	//HomeFunction(
	//	id = HomeFunctionID.WEATHER,
	//	title = "天気",
	//	icon = R.drawable.icon_weather
	//),
).groupBy { it.route }.toSortedMap()