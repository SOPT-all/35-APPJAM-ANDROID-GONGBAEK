package com.sopt.gongbaek.presentation.util

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.type.DayOfWeekType.Companion.toDayOfWeekRemoveSuffix
import com.sopt.gongbaek.domain.type.GroupCycleType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun formatGroupTimeDescription(groupInfo: GroupInfo): String {
    val groupCycle = groupInfo.getGroupCycleType()

    val startHour = groupInfo.startTime.toInt()
    val startMinute = ((groupInfo.startTime - startHour) * 60).toInt()
    val endHour = groupInfo.endTime.toInt()
    val endMinute = ((groupInfo.endTime - endHour) * 60).toInt()

    val startTimeString = "${startHour}시" + if (startMinute != 0) " ${startMinute}분" else ""
    val endTimeString = "${endHour}시" + if (endMinute != 0) " ${endMinute}분" else ""

    return when (groupCycle) {
        GroupCycleType.ONCE -> {
            if (groupInfo.date.isNullOrBlank()) {
                return "날짜 정보가 없습니다."
            }
            return try {
                val date = LocalDate.parse(groupInfo.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val formattedDate = date.format(DateTimeFormatter.ofPattern("M/d E요일", Locale.KOREAN))
                "$formattedDate $startTimeString-$endTimeString"
            } catch (e: DateTimeParseException) {
                "날짜 형식이 잘못되었습니다."
            }
        }

        GroupCycleType.WEEKLY -> {
            val dayOfWeek = toDayOfWeekRemoveSuffix(groupInfo.dayOfWeek, "")
            if (dayOfWeek.isEmpty()) {
                return "요일 정보가 잘못되었습니다."
            }
            "매주 $dayOfWeek $startTimeString-$endTimeString"
        }

        else -> "시간을 불러올 수 없습니다."
    }
}
