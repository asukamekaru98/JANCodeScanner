package com.websarva.wings.android.jan_scanner.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.websarva.wings.android.jan_scanner.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PrintHistoryScreenBar(
	onPrintHistoryClick: (PrintHistory) -> Unit = {},
	innerPadding: PaddingValues
) {
	Scaffold(
		topBar =
		{
			TopAppBar(
				title =
				{
					Text("履歴")
				}
			)
		},
	) { paddingValues ->
		GridList(
			printHistories = printHistories,
			onPrintHistoryClick = onPrintHistoryClick,
			modifier = Modifier
				.padding(innerPadding)
				.padding(paddingValues)
		)
	}
}

@Composable
private fun GridList(
	printHistories: List<PrintHistory>,
	onPrintHistoryClick: (PrintHistory) -> Unit,
	modifier: Modifier = Modifier,
) {
	LazyColumn(modifier = modifier) {
		items(printHistories) { printHistory ->
			ListItem(
				overlineContent = { Text(printHistory.overLine) },
				headlineContent = { Text(printHistory.headLine) },
				supportingContent = { Text(printHistory.supporting) },
				leadingContent = {
					when (val leading = printHistory.leading) {
						is LeadingImage.Resource -> {
							Icon(
								painterResource(leading.resId),
								contentDescription = null,
								tint = MaterialTheme.colorScheme.primary,
								modifier = Modifier.width(60.dp)
							)
						}

						is LeadingImage.Url -> {
							AsyncImage(
								model = ImageRequest.Builder(LocalContext.current)
									.data(leading.url)
									.crossfade(true)
									.build(),
								contentDescription = null,
								modifier = Modifier.size(48.dp)
							)
						}

						null -> {
							Icon(
								painter = painterResource(R.drawable.ic_launcher_foreground),
								contentDescription = null,
								tint = MaterialTheme.colorScheme.primary,
								modifier = Modifier.width(60.dp)
							)
						}

					}


					//Icon(
					//	painterResource(printHistory.leading),
					//	contentDescription = null,
					//	tint = MaterialTheme.colorScheme.primary,
					//	modifier = Modifier.width(60.dp)
					//)
				},
				trailingContent = {
					when (val trailing = printHistory.trailing) {
						is LeadingImage.Resource -> {
							Icon(
								painterResource(trailing.resId),
								contentDescription = null,
								tint = MaterialTheme.colorScheme.primary,
								modifier = Modifier.width(60.dp)
							)
						}

						is LeadingImage.Url -> {
							AsyncImage(
								model = ImageRequest.Builder(LocalContext.current)
									.data(trailing.url)
									.crossfade(true)
									.build(),
								contentDescription = null,
								modifier = Modifier.size(48.dp)
							)
						}

						null -> {
							Icon(
								painter = painterResource(R.drawable.ic_launcher_foreground),
								contentDescription = null,
								tint = MaterialTheme.colorScheme.primary,
								modifier = Modifier.width(60.dp)
							)
						}

					}
				},
				modifier = Modifier.clickable(
					onClickLabel = "",
				) {
					onPrintHistoryClick(printHistory)
				}
			)
		}
	}
}