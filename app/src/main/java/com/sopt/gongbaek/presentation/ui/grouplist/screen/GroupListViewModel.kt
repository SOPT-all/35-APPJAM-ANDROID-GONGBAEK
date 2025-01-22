package com.sopt.gongbaek.presentation.ui.grouplist.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor() :
    BaseViewModel<GroupListContract.State, GroupListContract.Event, GroupListContract.SideEffect>() {
    override fun createInitialState(): GroupListContract.State = GroupListContract.State()

    override suspend fun handleEvent(event: GroupListContract.Event) {
        when (event) {
            is GroupListContract.Event.GetGroups -> {
                getGroups(event.category)
            }

            is GroupListContract.Event.OnDayOfWeekSelected -> {
                setState { copy(selectedDayOfWeekIndex = event.index) }
            }

            is GroupListContract.Event.OnCategorySelected -> {
                setState { copy(selectedCategoryIndex = event.index) }
                getGroups(category = mapIndexToCategory(event.index))
            }

            is GroupListContract.Event.OnToggleSelected -> {
                setState { copy(toggleCheckedState = event.state) }
            }
        }
    }

    fun sendSideEffect(sideEffect: GroupListContract.SideEffect) = setSideEffect(sideEffect)

    private fun mapIndexToCategory(index: Int): String {
        return when (index) {
            0 -> GroupCategoryType.ALL.name
            1 -> GroupCategoryType.STUDY.name
            2 -> GroupCategoryType.DINING.name
            3 -> GroupCategoryType.EXERCISE.name
            4 -> GroupCategoryType.PLAYING.name
            5 -> GroupCategoryType.NETWORKING.name
            6 -> GroupCategoryType.OTHERS.name
            else -> GroupCategoryType.ALL.name
        }
    }

    private fun getGroups(category: String) {
        viewModelScope.launch {
            setState { copy(loadState = UiLoadState.Loading) }
            val queryParams = if (category == GroupCategoryType.ALL.name) {
                null
            } else {
                mapOf("category" to category)
            }
            // 서버로부터 카테고리 입력별 모임들을 불러오는 로직
        }
    }
}