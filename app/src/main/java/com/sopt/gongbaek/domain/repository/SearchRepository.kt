package com.sopt.gongbaek.domain.repository

import com.sopt.gongbaek.domain.model.Universities

interface SearchRepository {
    suspend fun getSearchUniversitiesResult(universityName: String): Result<Universities>
}
