/*
 * Copyright (c) 2021 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package com.websarva.wings.android.jan_scanner.scanner

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import net.mm2d.codereader.result.ScanResult

class MainActivityViewModel : ViewModel() {
    private val resultFlow: MutableStateFlow<List<ScanResult>> = MutableStateFlow(emptyList())
    fun getResultStream(): StateFlow<List<ScanResult>> = resultFlow

    fun add(result: ScanResult) {
        resultFlow.update {
            if (it.contains(result)) {
                it
            } else {
                it + result
            }
        }
    }
}
