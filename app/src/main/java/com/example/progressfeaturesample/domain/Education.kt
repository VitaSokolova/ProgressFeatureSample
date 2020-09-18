package com.example.progressfeaturesample.domain

import java.io.Serializable

/**
 * Информация об образовании
 */
data class Education(
    val place: String = "",
    val faculty: String? = null,
    val speciality: String? = null,
    val degree: String? = null,
    val startDate: String = "",
    val endDate: String = ""
) : Serializable

/**
 * Виды образования
 */
enum class EducationType {
    NO_EDUCATION,
    SCHOOL,
    VOCATIONAL,
    HIGHER,
    POST_GRADUATE;

    fun isHigherEducation() = this == HIGHER || this == POST_GRADUATE

    fun isNoEducation() = this == NO_EDUCATION
}