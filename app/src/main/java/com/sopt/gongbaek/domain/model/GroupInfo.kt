package com.sopt.gongbaek.domain.model

import com.sopt.gongbaek.domain.type.GroupCategoryType
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.domain.type.GroupStatusType

data class GroupInfo(
    val groupId: Int,
    val coverImg: Int,
    val status: String,
    val category: String,
    val cycle: String,
    val title: String,
    val date: String? = null,
    val dayOfWeek: String,
    val startTime: Double,
    val endTime: Double,
    val place: String,
    val introduction: String? = null,
    val maxPeopleCount: Int? = null,
    val currentPeopleCount: Int? = null,
    val isHost: Boolean? = null,
    val isApply: Boolean? = null
) {
    fun getGroupStatusType() = GroupStatusType.entries.find { it.name == this.status }
    fun getGroupCategoryType() = GroupCategoryType.entries.find { it.name == this.category }
    fun getGroupCycleType() = GroupCycleType.entries.find { it.name == this.cycle }
}
