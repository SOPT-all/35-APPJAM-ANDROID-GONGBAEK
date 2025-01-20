package com.sopt.gongbaek.domain.model

import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.domain.type.GroupStatusType

data class GroupInfo(
    val groupId: Int = 0,
    val coverImg: Int = 0,
    val status: String = "",
    val category: String = "",
    val cycle: String = "",
    val title: String = "",
    val date: String = "",
    val dayOfWeek: String = "",
    val startTime: Double = 0.0,
    val endTime: Double = 0.0,
    val place: String = "",
    val introduction: String = "",
    val maxPeopleCount: Int = 0,
    val currentPeopleCount: Int = 0,
    val isHost: Boolean = false,
    val isApply: Boolean? = false
) {
    fun getGroupStatusType() = GroupStatusType.entries.find { it.name == this.status }
    fun getGroupCategoryType() = GroupCategoryType.entries.find { it.name == this.category }
    fun getGroupCycleType() = GroupCycleType.entries.find { it.name == this.cycle }
}
