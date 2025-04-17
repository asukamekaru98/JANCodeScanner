package com.websarva.wings.android.jan_scanner.layout

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.websarva.wings.android.jan_scanner.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JanScreen(
	innerPadding: PaddingValues
) {
	val context = LocalContext.current
	val scope = rememberCoroutineScope()

	var janCode by rememberSaveable { mutableStateOf("") }
	var productName by rememberSaveable { mutableStateOf("") }
	var printCount by rememberSaveable { mutableStateOf("") }

	// Barcode スキャン用ランチャー(例: 外部ライブラリ使用)
	val barcodeLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.StartActivityForResult()
	) { result ->
		// TODO: result からバーコード文字列を取り出す
		val scanned = /* extractBarcode(result) */ ""
		if (scanned.isNotEmpty()) {
			janCode = scanned
			// API から商品名取得
			scope.launch {
				try {
					productName = fetchProductNameApi(scanned)
				} catch (e: Exception) {
					Toast.makeText(context, "商品名取得に失敗しました", Toast.LENGTH_SHORT).show()
				}
			}
		}
	}

	Column(
		modifier = Modifier
			.padding(innerPadding)
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		// 商品イメージ
		Image(
			painter = painterResource(R.drawable.icon_weather),
			contentDescription = "商品イメージ",
			modifier = Modifier
				.fillMaxWidth()
				.height(200.dp)
		)

		// JANコード入力 + カメラ
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			OutlinedTextField(
				value = janCode,
				onValueChange = { janCode = it },
				label = { Text("JANコード") },
				modifier = Modifier
					.weight(1f)
					.height(56.dp)
			)
			Spacer(Modifier.width(8.dp))
			IconButton(
				onClick = {
					// TODO: スキャン画面を起動
					// barcodeLauncher.launch(intent)
				},
				modifier = Modifier.size(56.dp)
			) {
				Icon(imageVector = Icons.Default.Person, contentDescription = "カメラ")
			}
		}

		// 商品名称表示(読み取り専用)
		OutlinedTextField(
			value = productName,
			onValueChange = {},
			label = { Text("商品名称") },
			readOnly = true,
			modifier = Modifier
				.fillMaxWidth()
				.height(56.dp)
		)

		Spacer(Modifier.height(24.dp))

		// 印刷枚数入力 + 送信
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxWidth()
		) {
			OutlinedTextField(
				value = printCount,
				onValueChange = { printCount = it },
				label = { Text("印刷枚数") },
				modifier = Modifier
					.weight(1f)
					.height(56.dp),
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
			)
			Spacer(Modifier.width(8.dp))
			Button(
				onClick = {
					scope.launch {
						try {
							val success = sendToPrinterBluetooth(janCode, productName, printCount.toIntOrNull() ?: 1)
							val msg = if (success) "送信に成功しました" else "送信に失敗しました"
							Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
						} catch (e: Exception) {
							Toast.makeText(context, "送信中にエラーが発生しました", Toast.LENGTH_SHORT).show()
						}
					}
				},
				modifier = Modifier.height(56.dp)
			) {
				Text("送信")
			}
		}
	}
}

// --- 以下は ViewModel 等に実装する想定の関数シグネチャ ---

/**
 * API から商品名称を取得するサンプル関数
 */
suspend fun fetchProductNameApi(janCode: String): String {
	// TODO: Retrofit 等で実装
	return "商品名称_$janCode"
}

/**
 * Bluetooth プリンタに接続し、印刷データを送信するサンプル関数
 */
suspend fun sendToPrinterBluetooth(
	janCode: String,
	productName: String,
	count: Int
): Boolean {
	// TODO: BluetoothSocket などで送信処理を実装
	return true
}


@Preview(showBackground = true)
@Composable
fun PrevJanScreen(){
	JanScreen(innerPadding = PaddingValues())
}