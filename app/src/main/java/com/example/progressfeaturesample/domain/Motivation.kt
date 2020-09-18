package com.example.progressfeaturesample.domain

import java.io.Serializable

/**
 * Модель причины, по которой пользователь обратил внимание на вакансию
 */
data class Motivation(val text: String) : Serializable