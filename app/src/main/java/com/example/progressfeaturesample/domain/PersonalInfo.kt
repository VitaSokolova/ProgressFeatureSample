package com.example.progressfeaturesample.domain

import java.io.Serializable
import java.util.*

/**
 *  Персональные данные
 */
class PersonalInfo(
    val name: String,
    val surname: String,
    val birthday: Date,
    val educationType: EducationType,
    val hasWorkingExperience: Boolean
) : Serializable