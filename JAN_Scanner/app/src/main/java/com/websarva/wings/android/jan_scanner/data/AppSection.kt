package com.websarva.wings.android.jan_scanner.data

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.websarva.wings.android.jan_scanner.R

interface AppSectionInterface {
	val id: String
	val icon: ImageVector
	val title: String
}

enum class HomeSection(
	override val id: String,
	override val icon: ImageVector,
	override val title: String
) : AppSectionInterface {
	HomeMenu(id = "home/menu", icon = Icons.Default.Home, title = "Home"),
	HomeFavorite(id = "home/favorite", icon = Icons.Default.Favorite, title = "Favorite"),
	HomeAbout(id = "home/about", icon = Icons.Default.Info, title = "About")
}

enum class MenuSection(
	override val id: String,
	override val icon: ImageVector = Icons.Default.Warning,
	override val title: String,
	@DrawableRes drawableRes: Int,
): AppSectionInterface{
	Jan(
		id = "home/menu/jan",
		title = "JAN",
		drawableRes = R.drawable.icon_barcode
	),
	Isbn(
		id = "home/menu/isbn",
		drawableRes = R.drawable.icon_book,
		title = "ISBN"
	)
}