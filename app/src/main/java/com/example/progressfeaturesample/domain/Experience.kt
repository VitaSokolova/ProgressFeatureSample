package com.example.progressfeaturesample.domain

sealed class Experience

/**
 * Информация об опыте работы
 */
class WorkingExperience : Experience()

/**
 * Информация об личном опыте
 */
class AboutMe(val text: String) : Experience()