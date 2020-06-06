package com.example.progressfeaturesample.domain

/**
 * Информация об образовании
 */
class Education() {
}

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