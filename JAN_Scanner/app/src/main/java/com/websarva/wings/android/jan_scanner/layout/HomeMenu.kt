package com.websarva.wings.android.jan_scanner.layout

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.websarva.wings.android.jan_scanner.R
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunction
import java.util.SortedMap

enum class ListType {
	Column, Grid
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeMenu(
	homeFunctions: SortedMap<String, List<HomeFunction>>,
	onHomeFunctionClick: (HomeFunction) -> Unit = {},
	//innerPadding: PaddingValues
) {
	Log.d("HomeMenu", "HomeMenu")

	var listType by rememberSaveable { mutableStateOf(ListType.Column) }
	//val homeFunctions = HomeFunctions

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("ホーム")
				},
				actions = {
					IconButton(
						onClick = {
							listType = when (listType) {
								ListType.Column -> ListType.Grid
								ListType.Grid -> ListType.Column
							}
						}
					)
					{
						// listType を key にして確実に再生成されるようにする
						key(listType)
						{
							Icon(
								painter = when (listType) {
									ListType.Column -> painterResource(R.drawable.grid_view)
									ListType.Grid -> painterResource(R.drawable.column_view)
								},
								contentDescription = "表示切り替え"
							)
						}
					}
				}
			)
		},
	) { innerPadding ->
		HomeScreenList(
			listType = listType,
			homeFunctions = homeFunctions,
			onHomeFunctionClick = onHomeFunctionClick,
			modifier = Modifier
				//.padding(innerPadding)
				.padding(innerPadding)
		)
	}


}


@Composable
private fun HomeScreenList(
	listType: ListType,
	homeFunctions: SortedMap<String, List<HomeFunction>>,
	onHomeFunctionClick: (HomeFunction) -> Unit,
	modifier: Modifier
) {
	if (listType == ListType.Column) {
		GridList(
			homeFunctions = homeFunctions,
			onHomeFunctionClick = onHomeFunctionClick,
			modifier = modifier
		)
	} else {
		ColumnList(
			homeFunctions = homeFunctions,
			onHomeFunctionClick = onHomeFunctionClick,
			modifier = modifier
		)
	}
}

@Composable
private fun GridList(
	homeFunctions: SortedMap<String, List<HomeFunction>>,
	onHomeFunctionClick: (HomeFunction) -> Unit,
	modifier: Modifier = Modifier,
) {
	LazyColumn(modifier = modifier) {
		for ((id, list) in homeFunctions) {
			items(list) { homeFunction ->
				ListItem(
					headlineContent = { Text(homeFunction.title) },
					leadingContent = {
						Icon(
							painterResource(homeFunction.icon),
							contentDescription = null,
							tint = MaterialTheme.colorScheme.primary,
							modifier = Modifier.width(60.dp)
						)
					},
					modifier = Modifier.clickable(
						onClickLabel = "",
					) {
						onHomeFunctionClick(homeFunction)
					}
				)
			}
		}
	}

}

@Composable
private fun ColumnList(
	homeFunctions:  SortedMap<String, List<HomeFunction>>,
	onHomeFunctionClick: (HomeFunction) -> Unit,
	modifier: Modifier
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp),
		modifier = modifier
	) {
		for ((id, list) in homeFunctions) {

			items(list) { homeFunction ->
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.fillMaxWidth()
						.clickable(onClickLabel = "詳細を確認する") {
							onHomeFunctionClick(homeFunction)
						}
				) {
					Icon(
						painterResource(homeFunction.icon),
						contentDescription = null,
						tint = MaterialTheme.colorScheme.primary,
						modifier = Modifier
							.fillMaxWidth()
							.aspectRatio(1f)
							.scale(0.9f)

					)
					Text(text = homeFunction.title)
				}
				//}
			}
		}
	}
}
