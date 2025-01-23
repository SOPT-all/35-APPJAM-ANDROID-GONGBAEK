package com.sopt.gongbaek.data.remote.util

class ApiResponseException(val code: Int, message: String) : Exception(message)
