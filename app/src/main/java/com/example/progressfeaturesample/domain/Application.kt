package com.example.progressfeaturesample.domain

import ru.surfstudio.android.core.mvp.binding.rx.extensions.Optional

/**
 * Модель заявления
 */
class Application(
    val personal: PersonalInfo,
    val education: Education,
    val experience: Experience,
    val motivation: List<Motivation>
) {

    class Builder {
        private var personal: Optional<PersonalInfo> = Optional.empty()
        private var education: Optional<Education> = Optional.empty()
        private var experience: Optional<Experience> = Optional.empty()
        private var motivation: Optional<List<Motivation>> = Optional.empty()

        fun personalInfo(value: PersonalInfo) = apply { personal = Optional.of(value) }
        fun education(value: Education) = apply { education = Optional.of(value) }
        fun experience(value: Experience) = apply { experience = Optional.of(value) }
        fun motivation(value: List<Motivation>) = apply { motivation = Optional.of(value) }

        fun getPersonalInfo(): PersonalInfo = personal.get()
        fun getEducation(): Education = education.get()
        fun getExperience(): Experience = experience.get()
        fun getMotivation(): List<Motivation> = motivation.get()

        fun build(): Application {
            return try {
                Application(
                    personal.get(),
                    education.get(),
                    experience.get(),
                    motivation.get()
                )
            } catch (e: NoSuchElementException) {
                throw ApplicationIsNotFilled(
                    """Some fields aren't filled in application
                        personal = {${personal.getOrNull()}}
                        education = {${education.getOrNull()}}
                        experience = {${experience.getOrNull()}}
                        motivation = {${motivation.getOrNull()}}
                    """.trimMargin()
                )
            }
        }
    }
}

class ApplicationIsNotFilled(msg: String) : Throwable(msg)