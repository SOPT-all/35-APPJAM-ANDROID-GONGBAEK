package com.sopt.gongbaek.data.remote.util

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse

fun <T> ApiResponse<T>.handleApiResponse(): Result<T> {
    return when (this.success) {
        true -> Result.success(this.data)
        false -> Result.failure(Exception("Error code : ${this.code}, Error message : ${this.message}"))
    }
}

fun <T> NullableApiResponse<T>.handleApiResponse(): Result<T?> =
    when (this.success) {
        true -> Result.success(this.data)
        false -> Result.failure(Exception("Error code : ${this.code}, Error message : ${this.message}"))
    }
