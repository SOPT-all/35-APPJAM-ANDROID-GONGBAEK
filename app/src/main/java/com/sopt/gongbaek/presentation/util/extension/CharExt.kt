package com.sopt.gongbaek.presentation.util.extension

fun Char.isKoreanChar(): Boolean {
    val regex = Regex("^[가-힣ㆍᆞᆢㄱ-ㅎㅏ-ㅣ]*$")
    return regex.matches(this.toString())
}

fun Char.isCompleteKorean(): Boolean = this in '\uAC00'..'\uD7A3'
