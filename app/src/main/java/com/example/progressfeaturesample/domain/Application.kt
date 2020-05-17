package com.example.progressfeaturesample.domain

class Application(
    val personal: PersonalInfo,
    val education: Education,
    val experience: Experience,
    val motivation: Motivation
) {

    class Builder {
        lateinit var personal: PersonalInfo
        lateinit var education: Education
        lateinit var experience: Experience
        lateinit var motivation: Motivation

        fun personalInfo(value: PersonalInfo) = apply { personal = value }
        fun education(value: Education) = apply { education = value }
        fun experience(value: Experience) = apply { experience = value }
        fun motivation(value: Motivation) = apply { motivation = value }

        fun build(): Application {
            return Application(personal, education, experience, motivation)
        }
    }
}