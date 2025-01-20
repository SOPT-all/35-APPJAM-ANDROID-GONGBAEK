package com.sopt.gongbaek.domain.model

import com.sopt.gongbaek.domain.type.GroupCycleType

data class GroupRegisterInfo(
    val groupType: String = "",
    val weekDate: String = "",
    val weekDay: String = "",
    val startTime: Double = 0.0,
    val endTime: Double = 0.0,
    val dueDate: String = "",
    val category: String = "",
    val coverImg: Int = 0,
    val location: String = "",
    val maxPeopleCount: Int = 0,
    val groupTitle: String = "",
    val introduction: String = ""
) {
    fun getGroupType() = GroupCycleType.entries.find { it.name == this.groupType }

    fun setGroupType(description: String): String =
        GroupCycleType.entries.find { it.description == description }?.name ?: ""
}
