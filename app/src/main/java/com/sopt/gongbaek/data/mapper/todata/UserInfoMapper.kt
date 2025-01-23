package com.sopt.gongbaek.data.mapper.todata

import com.sopt.gongbaek.data.remote.dto.request.RegisterUserInfoRequestDto
import com.sopt.gongbaek.domain.model.UserInfo

fun UserInfo.toData() = RegisterUserInfoRequestDto(
    profileImage = this.profileImage,
    nickname = this.nickname,
    mbti = this.mbti,
    school = this.school,
    major = this.major,
    grade = this.grade,
    enterYear = this.enterYear,
    introduction = this.introduction,
    gender = this.gender,
    timeTable = this.timeTable.map { it.toData() }
)
