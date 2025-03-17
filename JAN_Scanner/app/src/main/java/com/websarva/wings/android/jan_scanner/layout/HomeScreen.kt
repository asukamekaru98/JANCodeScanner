package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.websarva.wings.android.jan_scanner.homeFunctions.HomeFunctions


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
	homeFunctions: List<HomeFunction> = HomeFunctions,
	onHomeFunctionClick: (HomeFunction) -> Unit = {},
	//listType: ListType,
	innerPadding: PaddingValues
) {
	var listType by rememberSaveable { mutableStateOf(ListType.Column) }

	//var showDialog by remember { mutableStateOf(false) }
	//if (showDialog) {
	//	ListTypeSelectionDialog(
	//		listType = listType,
	//		onConfirm = { newListType ->
	//			listType = newListType
	//			showDialog = false
	//		},
	//		onDismiss = {
	//			showDialog = false
	//		}
	//	)
	//}
	Scaffold(
		topBar =
		{
			TopAppBar(
				title =
				{
					Text("ホーム")
				},
				actions =
				{
					IconButton(
						onClick =
						{
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
	) { paddingValues ->
		HomeScreenList(
			listType = listType,
			homeFunctions = homeFunctions,
			onHomeFunctionClick = onHomeFunctionClick,
			modifier = Modifier
				.padding(innerPadding)
				.padding(paddingValues)
		)
	}


}


@Composable
private fun HomeScreenList(
	listType: ListType,
	homeFunctions: List<HomeFunction>,
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
	homeFunctions: List<HomeFunction>,
	onHomeFunctionClick: (HomeFunction) -> Unit,
	modifier: Modifier = Modifier,
) {
	LazyColumn(modifier = modifier) {
		items(homeFunctions) { homeFunction ->
			ListItem(
				headlineContent = { Text(homeFunction.name) },
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

@Composable
private fun ColumnList(
	homeFunctions: List<HomeFunction>,
	onHomeFunctionClick: (HomeFunction) -> Unit,
	modifier: Modifier
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp),
		modifier = modifier
	) {
		items(homeFunctions) { homeFunction ->
			//Card(
			//	modifier = Modifier
			//		.fillMaxWidth()
			//		.clickable(onClickLabel = "詳細を確認する") {
			//			onHomeFunctionClick(homeFunction)
			//		},
			//	elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
			//	shape = MaterialTheme.shapes.medium
			//){
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
				Text(text = homeFunction.name)
			}
			//}
		}
	}
}

@Composable
private fun ListTypeSelectionDialog(
	listType: ListType,
	onConfirm: (ListType) -> Unit,
	onDismiss: () -> Unit,
) {
	AlertDialog(
		text = {
			val text = when (listType) {
				ListType.Column -> "グリッド表示に変更しますか？"
				ListType.Grid -> "リスト表示に変更しますか？"
			}
			Text(text)
		},
		confirmButton = {
			TextButton(
				onClick = {
					val newListType = when (listType) {
						ListType.Column -> ListType.Grid
						ListType.Grid -> ListType.Column
					}
					onConfirm(newListType)
				}
			) { Text("はい") }
		},
		dismissButton = {
			TextButton(onClick = onDismiss) { Text("いいえ") }
		},
		onDismissRequest = onDismiss
	)
}