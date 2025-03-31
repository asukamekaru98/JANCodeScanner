package com.websarva.wings.android.jan_scanner.homeFunctions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.layout.IsbnScreen
import com.websarva.wings.android.jan_scanner.layout.JanScreen

enum class HomeFunctionID {
	JAN,
	ISBN,
	WEATHER,
	HOGE;

	// このロジックで、ユニークな値にしている
	val value: Int
		get() = this.ordinal
}

data class HomeFunction(
	val id: HomeFunctionID,
	val title: String,
	@DrawableRes val icon: Int,
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
).groupBy { it.id }.toSortedMap()

@Composable
fun HomeFunction(
	name: String,

	) {

}