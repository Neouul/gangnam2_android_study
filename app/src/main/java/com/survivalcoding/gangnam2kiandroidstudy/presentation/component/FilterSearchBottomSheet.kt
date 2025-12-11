package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSearchBottomSheet(
    modifier: Modifier = Modifier,
    initialFilter: FilterSearchState = FilterSearchState(),
    onApplyFilter: (FilterSearchState) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    var filterState by remember(initialFilter) {
        mutableStateOf(initialFilter)     // 이전 선택값으로 초기화
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val timeList = listOf("All", "Newest", "Oldest", "Popularity")
    val categoryList = listOf(
        "All",
        "Cereal",
        "Vegetables",
        "Dinner",
        "Chinese",
        "Local Dish",
        "Fruit",
        "BreakFast",
        "Spanish",
        "Chinese",
        "Lunch",
    )

    ModalBottomSheet(
        containerColor = AppColors.white,
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp),
        ) {
            Text(
                text = "Filter Search",
                style = AppTextStyles.smallTextBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Time
            Column {
                Text(
                    text = "Time",
                    style = AppTextStyles.smallTextBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    timeList.forEach { time ->
                        FilterButton(
                            text = time,
                            onClick = { filterState = filterState.copy(time = time) },
                            isSelected = (filterState.time == time),
                            modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
                        )
                    }
                }
            }

            // Rate
            Column(
                modifier = Modifier.padding(vertical = 10.dp),
            ) {
                Text(
                    text = "Rate",
                    style = AppTextStyles.smallTextBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    for (num in 5 downTo 1) {
                        RatingButton(
                            text = "$num",
                            onClick = {
                                filterState = if (filterState.rating == num) {
                                    filterState.copy(rating = null)   // 동일 버튼 누르면 해제
                                } else {
                                    filterState.copy(rating = num)    // 새로 선택
                                }
                            },
                            isSelected = (filterState.rating == num),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        if (num != 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            // Category
            Column {
                Text(
                    text = "Category",
                    style = AppTextStyles.smallTextBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    categoryList.forEach { category ->
                        val isSelected = category in filterState.categories

                        val onClick = {
                            filterState =
                                if (isSelected) {
                                    filterState.copy(categories = filterState.categories - category)
                                } else {
                                    filterState.copy(categories = filterState.categories + category)
                                }
                        }

                        if (category == "Dinner") {
                            RatingButton(
                                text = category,
                                onClick = onClick,
                                isSelected = isSelected,
                                modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),
                            )
                        } else {
                            FilterButton(
                                text = category,
                                onClick = onClick,
                                isSelected = isSelected,
                                modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),
                            )
                        }
                    }
                }
            }



            SmallButton(
                text = "Filter",
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onApplyFilter(filterState) }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFilterSearchBottomSheet() {
    FilterSearchBottomSheet()
}

data class FilterSearchState(
    val time: String = "",
    val rating: Int? = null,
    val categories: Set<String> = emptySet(),
)