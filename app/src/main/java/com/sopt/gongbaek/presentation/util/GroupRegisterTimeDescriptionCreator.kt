package com.sopt.gongbaek.presentation.util

import com.sopt.gongbaek.domain.model.GroupRegisterInfo
import com.sopt.gongbaek.domain.type.GroupCycleType
import com.sopt.gongbaek.presentation.util.extension.getKoreanDayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun createGroupRegisterTimeDescription(groupRegisterInfo: GroupRegisterInfo): String {
    val groupCycle = groupRegisterInfo.getGroupType()

    val startHour = groupRegisterInfo.startTime.toInt()
    val startMinute = ((groupRegisterInfo.startTime - startHour) * 60).toInt()
    val endHour = groupRegisterInfo.endTime.toInt()
    val endMinute = ((groupRegisterInfo.endTime - endHour) * 60).toInt()

    val startTimeString = "${startHour}시" + if (startMinute != 0) " ${startMinute}분" else ""
    val endTimeString = "${endHour}시" + if (endMinute != 0) " ${endMinute}분" else ""

    return when (groupCycle) {
        GroupCycleType.ONCE -> {
            val date = LocalDate.parse(groupRegisterInfo.weekDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val formattedDate = date.format(DateTimeFormatter.ofPattern("M/d E", Locale.KOREAN))
            "$formattedDate $startTimeString - $endTimeString"
        }

        GroupCycleType.WEEKLY -> {
            val dayOfWeek = getKoreanDayOfWeek(groupRegisterInfo.weekDay)
            "매주 $dayOfWeek $startTimeString - $endTimeString"
        }

        else -> "시간을 불러올 수 없습니다."
    }
}
