package com.sopt.gongbaek.presentation.util

import com.sopt.gongbaek.domain.model.GroupInfo
import com.sopt.gongbaek.domain.type.GroupCycleType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
            val date = LocalDate.parse(groupInfo.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val formattedDate = date.format(DateTimeFormatter.ofPattern("M/d", Locale.KOREAN))
            "$formattedDate $startTimeString-$endTimeString"
        }

        GroupCycleType.WEEKLY -> {
            val dayOfWeek = getKoreanDayOfWeek(groupInfo.dayOfWeek)
            "매주 $dayOfWeek $startTimeString-$endTimeString"
        }

        else -> "시간을 불러올 수 없습니다."
    }
}

fun getKoreanDayOfWeek(weekDay: String): String {
    return when (weekDay.uppercase()) {
        "MON" -> "월요일"
        "TUE" -> "화요일"
        "WED" -> "수요일"
        "THU" -> "목요일"
        "FRI" -> "금요일"
        "SAT" -> "토요일"
        "SUN" -> "일요일"
        else -> ""
    }
}
