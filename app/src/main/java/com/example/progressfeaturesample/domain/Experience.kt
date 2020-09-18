package com.example.progressfeaturesample.domain

import java.io.Serializable

sealed class Experience : Serializable

/**
 * Информация об опыте работы
 */
data class WorkingExperience(
    val place: String = "",
    val position: String = "",
    val dateFrom: String = "",
    val dateTo: String = ""
) : Experience()

/**
 * Информация об личном опыте
 */
data class AboutMe(val text: String) : Experience()