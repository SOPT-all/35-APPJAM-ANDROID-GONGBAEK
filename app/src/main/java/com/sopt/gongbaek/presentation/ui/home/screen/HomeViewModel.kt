package com.sopt.gongbaek.presentation.ui.home.screen

import androidx.lifecycle.viewModelScope
import com.sopt.gongbaek.domain.usecase.FetchHomeScreenUseCase
import com.sopt.gongbaek.domain.usecase.FetchLatestGroupUseCase
import com.sopt.gongbaek.domain.usecase.FetchUserLectureTimetableUseCase
import com.sopt.gongbaek.domain.usecase.FetchUserProfileUseCase
import com.sopt.gongbaek.domain.usecase.SetLectureTimetableUseCase
import com.sopt.gongbaek.presentation.util.base.BaseViewModel
import com.sopt.gongbaek.presentation.util.base.UiLoadState
import com.sopt.gongbaek.presentation.util.timetable.convertToSlotsByDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeScreenUseCase: FetchHomeScreenUseCase,
    private val fetchLatestGroupUseCase: FetchLatestGroupUseCase,
    private val fetchUserProfileUseCase: FetchUserProfileUseCase,
    private val fetchUserLectureTimetableUseCase: FetchUserLectureTimetableUseCase,
    private val setLectureTimetableUseCase: SetLectureTimetableUseCase
) : BaseViewModel<HomeContract.State, HomeContract.Event, HomeContract.SideEffect>() {

    override fun createInitialState(): HomeContract.State = HomeContract.State()

    override suspend fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnFetchHomeInfo -> fetchHomeInfo()
            is HomeContract.Event.OnFetchLatestOnceGroup -> fetchLatestOnceGroup()
            is HomeContract.Event.OnFetchLatestWeekGroup -> fetchLatestWeekGroup()
            is HomeContract.Event.OnFetchUserProfile -> fetchUserProfile()
            is HomeContract.Event.OnFetchUserLectureTimetable -> fetchUserLectureTimetable()
        }
    }

    fun sendSideEffect(sideEffect: HomeContract.SideEffect) =
        setSideEffect(sideEffect)

    private fun fetchHomeInfo() =
        viewModelScope.launch {
            setState { copy(homeLoadState = UiLoadState.Loading) }
            fetchHomeScreenUseCase()
                .fold(
                    onSuccess = { nearestGroup ->
                        setState {
                            copy(
                                homeLoadState = UiLoadState.Success,
                                nearestGroup = nearestGroup
                            )
                        }
                    },
                    onFailure = { setState { copy(homeLoadState = UiLoadState.Error) } }
                )
        }

    private fun fetchLatestOnceGroup() =
        viewModelScope.launch {
            setState { copy(homeLoadState = UiLoadState.Loading) }
            fetchLatestGroupUseCase(groupType = "ONCE")
                .fold(
                    onSuccess = { latestOnceGroups ->
                        setState {
                            copy(
                                homeLoadState = UiLoadState.Success,
                                onceRecommendGroupList = latestOnceGroups
                            )
                        }
                    },
                    onFailure = {
                        setState { copy(homeLoadState = UiLoadState.Error) }
                    }
                )
        }

    private fun fetchLatestWeekGroup() =
        viewModelScope.launch {
            setState { copy(homeLoadState = UiLoadState.Loading) }
            fetchLatestGroupUseCase(groupType = "WEEKLY")
                .fold(
                    onSuccess = { latestWeekGroups ->
                        setState {
                            copy(
                                homeLoadState = UiLoadState.Success,
                                weekRecommendGroupList = latestWeekGroups
                            )
                        }
                    },
                    onFailure = {
                        setState { copy(homeLoadState = UiLoadState.Error) }
                    }
                )
        }

    private fun fetchUserProfile() =
        viewModelScope.launch {
            fetchUserProfileUseCase()
                .fold(
                    onSuccess = { userProfile ->
                        setState { copy(userProfile = userProfile) }
                    },
                    onFailure = { setState { copy(homeLoadState = UiLoadState.Error) } }
                )
        }

    private fun fetchUserLectureTimetable() =
        viewModelScope.launch {
            fetchUserLectureTimetableUseCase()
                .fold(
                    onSuccess = { userLectureTimeTable ->
                        setState { copy(userLectureTimeTable = userLectureTimeTable) }
                        setState { copy(convertedUserLectureTimeTable = convertToSlotsByDay(userLectureTimeTable)) }
                        setUserLectureTimetable(convertToSlotsByDay(userLectureTimeTable))
                    },
                    onFailure = { setState { copy(homeLoadState = UiLoadState.Error) } }
                )
        }

    private fun setUserLectureTimetable(lectureTimetable: Map<String, List<Int>>) =
        viewModelScope.launch {
            setLectureTimetableUseCase(lectureTimetable)
        }
}
