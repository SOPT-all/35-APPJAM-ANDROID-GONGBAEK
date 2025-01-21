package com.sopt.gongbaek.presentation.ui.auth.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import com.sopt.gongbaek.ui.theme.defaultGongBaekColors
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Picker(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: PickerState = rememberPickerState(),
    initialSelectedIndex: Int = 0, // 초기 선택 인덱스
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier
) {
    val visibleItemsMiddle = visibleItemsCount / 2

    // 패딩된 아이템 리스트 생성
    val paddedItems = remember(items) {
        List(visibleItemsMiddle) { "" } + items + List(visibleItemsMiddle) { "" }
    }

    // LazyListState 초기화 및 동기화
    val listState = rememberLazyListState()

    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.intValue)

    val centerIndex = remember {
        derivedStateOf { listState.firstVisibleItemIndex + visibleItemsMiddle }
    }

    // 초기화: LazyListState 및 PickerState 동기화
    LaunchedEffect(initialSelectedIndex) {
        listState.scrollToItem(initialSelectedIndex + visibleItemsMiddle)
        state.selectedItem = items[initialSelectedIndex]
    }

    // 스냅 동작 시 PickerState 업데이트
    LaunchedEffect(listState) {
        snapshotFlow {
            val firstVisible = listState.firstVisibleItemIndex
            val center = firstVisible + visibleItemsMiddle
            center
        }
            .map { centerIndex ->
                val actualIndex = centerIndex - visibleItemsMiddle
                if (actualIndex in items.indices) {
                    items[actualIndex]
                } else {
                    null
                }
            }
            .filterNotNull()
            .distinctUntilChanged()
            .collect { item -> state.selectedItem = item }
    }

    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * visibleItemsCount)
                .align(Alignment.Center)
        ) {
            val borderHeight = itemHeightDp.toPx()
            val center = size.height / 2

            drawLine(
                color = defaultGongBaekColors.gray05,
                start = Offset(0f, center - borderHeight / 2),
                end = Offset(size.width, center - borderHeight / 2),
                strokeWidth = 0.5f
            )
            drawLine(
                color = defaultGongBaekColors.gray05,
                start = Offset(0f, center + borderHeight / 2),
                end = Offset(size.width, center + borderHeight / 2),
                strokeWidth = 0.5f
            )
        }

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(itemHeightDp * visibleItemsCount)
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            items(paddedItems.size) { index ->
                val isSelected = index == centerIndex.value
                Text(
                    text = paddedItems[index],
                    maxLines = 1,
                    color = if (isSelected) GongBaekTheme.colors.gray10 else GongBaekTheme.colors.gray04,
                    style = if (isSelected) GongBaekTheme.typography.title1.m20 else GongBaekTheme.typography.title1.r20,
                    modifier = Modifier
                        .onSizeChanged { size -> itemHeightPixels.intValue = size.height }
                        .then(textModifier)
                        .semantics {
                            if (isSelected) {
                                selected = true
                                stateDescription = "Selected"
                            }
                        }
                )
            }
        }
        Text(
            text = "년",
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.title1.m20,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPickerExample() {
    val valuesPickerState = rememberPickerState()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val years = remember { (2000..currentYear).map { it.toString() } }
    val defaultYearIndex = years.indexOf(currentYear.toString()).takeIf { it >= 0 } ?: 0

    Picker(
        state = valuesPickerState,
        items = years,
        initialSelectedIndex = defaultYearIndex,
        textModifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun rememberPickerState() = remember { PickerState() }

class PickerState {
    var selectedItem by mutableStateOf("")
}

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }
