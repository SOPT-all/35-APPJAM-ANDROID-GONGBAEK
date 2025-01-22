package com.sopt.gongbaek.presentation.ui.component.picker

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.ui.theme.GongBaekTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Picker(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: PickerState = rememberPickerState(),
    initialSelectedIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textModifier: Modifier = Modifier
) {
    val visibleItemsMiddle = visibleItemsCount / 2
    val paddedItems = remember(items) { List(visibleItemsMiddle) { "" } + items + List(visibleItemsMiddle) { "" } }
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    var itemHeightPixels by remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels)
    val centerIndex by remember { derivedStateOf { listState.firstVisibleItemIndex + visibleItemsMiddle } }

    LaunchedEffect(initialSelectedIndex) {
        listState.scrollToItem(initialSelectedIndex + visibleItemsMiddle)
        state.selectedItem = items[initialSelectedIndex]
    }

    LaunchedEffect(listState) {
        snapshotFlow { centerIndex - visibleItemsMiddle }
            .mapNotNull { index -> items.getOrNull(index) }
            .distinctUntilChanged()
            .collect { state.selectedItem = it }
    }

    Box(modifier = modifier) {
        GuideLines(
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(itemHeightDp * visibleItemsCount)
                .fillMaxWidth()
        ) {
            items(paddedItems.size) { index ->
                PickerItem(
                    text = paddedItems[index],
                    isSelected = index == centerIndex,
                    onSizeChanged = { itemHeightPixels = it.height },
                    modifier = textModifier
                )
            }
        }

        Text(
            text = "년",
            color = GongBaekTheme.colors.gray10,
            style = GongBaekTheme.typography.title1.m20,
            modifier = modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}

@Composable
private fun PickerItem(
    text: String,
    isSelected: Boolean,
    onSizeChanged: (IntSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        maxLines = 1,
        color = if (isSelected) GongBaekTheme.colors.gray10 else GongBaekTheme.colors.gray04,
        style = if (isSelected) GongBaekTheme.typography.title1.m20 else GongBaekTheme.typography.title1.r20,
        modifier = Modifier
            .onSizeChanged { size -> onSizeChanged(size) }
            .then(modifier)
            .semantics {
                if (isSelected) {
                    selected = true
                    stateDescription = "Selected"
                }
            }
    )
}

@Composable
private fun rememberPickerState() = remember { PickerState() }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }

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
