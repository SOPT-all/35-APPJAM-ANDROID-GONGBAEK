package com.sopt.gongbaek.data.remote.util

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse

fun <T> ApiResponse<T>.handleApiResponse(): Result<T> {
    return when (this.success) {
        true -> Result.success(this.data)
        false -> Result.failure(Exception("Error code : ${this.code}, Error message : ${this.message}"))
    }
}