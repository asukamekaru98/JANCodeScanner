package com.websarva.wings.android.jan_scanner.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.jan_scanner.R
import kotlinx.serialization.Serializable

@Composable
fun PrintHistoryStatTrans(
	innderPadding: PaddingValues
) {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = PrintHistoryRoute.ListScreen,
	) {
		composable<PrintHistoryRoute.ListScreen> {
			PrintHistoryScreenBar(
				onPrintHistoryClick = { PrintHistory ->
					navController.navigate(
						PrintHistoryRoute.SendScreen
					)
				},
				innerPadding = innderPadding
			)
		}

		composable<PrintHistoryRoute.SendScreen> {
			ScannerScreen(
				onBackClick = { navController.popBackStack() }
			)
		}
	}
}



object PrintHistoryRoute {
	@Serializable
	data object ListScreen

	@Serializable
	data object SendScreen
}

sealed class LeadingImage {
	data class Resource(@DrawableRes val resId: Int) : LeadingImage()
	data class Url(val url: String) : LeadingImage()
}

data class PrintHistory(
	val overLine: String,
	val headLine: String,
	val supporting: String,
	val leading: LeadingImage,
	val trailing: LeadingImage,
)

val printHistories = listOf(
	PrintHistory(
		overLine = "JANスキャナー",
		headLine = "アクエリアス 500ml",
		supporting = "4902102143578",
		trailing = LeadingImage.Url("https://m.media-amazon.com/images/I/71fxLFqVZwL._AC_SX679_.jpg"),
		leading = LeadingImage.Resource(R.drawable.icon_barcode)
	),
	PrintHistory(
		overLine = "JANスキャナー",
		headLine = "アクエリアス 500ml",
		supporting = "4902102143578",
		trailing = LeadingImage.Resource(R.drawable.icon_barcode),
		leading = LeadingImage.Url("https://m.media-amazon.com/images/I/61xG9R7l9DL.__AC_SX300_SY300_QL70_ML2_.jpg"),
	),
	PrintHistory(
		overLine = "JANスキャナー",
		headLine = "アクエリアス 500ml",
		supporting = "4902102143578",
		trailing = LeadingImage.Resource(R.drawable.icon_barcode),
		leading = LeadingImage.Url("https://m.media-amazon.com/images/I/81RDEVMqBgL._AC_SX679_.jpg"),
	)
)

