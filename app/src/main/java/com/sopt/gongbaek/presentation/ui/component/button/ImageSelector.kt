package com.sopt.gongbaek.presentation.ui.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.gongbaek.R
import com.sopt.gongbaek.presentation.type.ImageSelectorType
import com.sopt.gongbaek.presentation.util.extension.clickableWithoutRipple
import com.sopt.gongbaek.ui.theme.GongBaekTheme

@Composable
fun ImageSelector(
    imageSelectorType: ImageSelectorType,
    modifier: Modifier = Modifier,
    imageButtonResIdList: List<Int> = imageSelectorType.imageButtonResIdList,
    selectedAlpha: Float = 0.65f,
    selectedIndex: Int? = null,
    onIndexSelected: (Int) -> Unit = {},
) {
    val chunkedImages = imageButtonResIdList.chunked(imageSelectorType.chunkedCount)
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        chunkedImages.forEachIndexed { rowIndex, imageRow ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                imageRow.forEachIndexed { columnIndex, imageResId ->
                    val imageIndex = rowIndex * imageSelectorType.chunkedCount + columnIndex
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .clickableWithoutRipple { onIndexSelected(imageIndex) }
                            .clip(RoundedCornerShape(4.dp))
                    ) {
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        if (selectedIndex == imageIndex) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = GongBaekTheme.colors.black.copy(selectedAlpha),
                                        shape = RoundedCornerShape(4.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_check_fill_24),
                                    contentDescription = null,
                                    tint = GongBaekTheme.colors.mainOrange
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSImageSelectorButton(
    modifier: Modifier = Modifier
) {
    var selectedProfileIndex by remember { mutableStateOf<Int?>(null) }
    var selectedCoverIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        ImageSelector(
            imageSelectorType = ImageSelectorType.Profile,
            modifier = Modifier
                .aspectRatio(1f / 1f),
            selectedIndex = selectedProfileIndex,
            onIndexSelected = { selectedProfileIndex = it }
        )
        ImageSelector(
            imageSelectorType = ImageSelectorType.Cover,
            modifier = Modifier
                .aspectRatio(16f / 13f),
            selectedIndex = selectedCoverIndex,
            onIndexSelected = { selectedCoverIndex = it }
        )
    }
}
