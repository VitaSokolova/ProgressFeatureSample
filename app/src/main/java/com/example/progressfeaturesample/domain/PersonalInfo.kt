package com.example.progressfeaturesample.domain

import java.util.*

/**
 *  Персональные данные
 */
class PersonalInfo(
    val name: String,
    val surname: String,
    val birthday: Date,
    val education: EducationType,
    val workingExperience: Boolean
)