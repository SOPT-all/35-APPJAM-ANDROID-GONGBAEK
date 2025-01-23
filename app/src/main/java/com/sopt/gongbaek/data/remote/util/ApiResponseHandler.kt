package com.sopt.gongbaek.data.remote.util

import com.sopt.gongbaek.data.remote.dto.base.ApiResponse
import com.sopt.gongbaek.data.remote.dto.base.NullableApiResponse

fun <T> ApiResponse<T>.handleApiResponse(): Result<T> =
    when (this.code) {
        in 2000..2999 -> {
            Result.success(this.data)
        }

        in 3000..3999 -> {
            Result.failure(Exception("Redirection error : ${this.message}"))
        }

        in 4000..4999 -> {
            Result.failure(Exception("Client error : ${this.message}"))
        }

        in 5000..5999 -> {
            Result.failure(Exception("Server error : ${this.message}"))
        }

        else -> {
            Result.failure(Exception("Unknown error : ${this.message}"))
        }
    }

fun <T> NullableApiResponse<T>.handleNullableApiResponse(): Result<T?> =
    when (this.code) {
        in 2000..2999 -> {
            Result.success(this.data)
        }

        in 3000..3999 -> {
            Result.failure(ApiResponseException(this.code, "Redirection error : ${this.message}"))
        }

        in 4000..4999 -> {
            Result.failure(ApiResponseException(this.code, "Client error : ${this.message}"))
        }

        in 5000..5999 -> {
            Result.failure(ApiResponseException(this.code, "Server error : ${this.message}"))
        }

        else -> {
            Result.failure(ApiResponseException(this.code, "Unknown error : ${this.message}"))
        }
    }
