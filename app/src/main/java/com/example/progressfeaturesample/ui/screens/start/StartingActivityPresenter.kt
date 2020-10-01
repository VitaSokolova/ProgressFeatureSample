package com.example.progressfeaturesample.ui.screens.start

import com.example.progressfeaturesample.domain.*
import com.example.progressfeaturesample.interactors.application.steps.*
import com.example.progressfeaturesample.ui.screens.container.MainRoute
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import java.util.*
import javax.inject.Inject

/**
 * Презентер [StartingActivityView].
 */
@PerScreen
class StartingActivityPresenter @Inject constructor(
    private val bm: StartingBindModel,
    private val activityNavigator: ActivityNavigator,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        super.onFirstLoad()
        bm.openNewApplicationAction bindTo {
            activityNavigator.start(MainRoute())
        }
        bm.openDraftAction bindTo {
            activityNavigator.start(MainRoute(getDraft()))
        }
    }

    private fun getDraft(): ApplicationDraft {
        return ApplicationDraft().apply {
            outDataMap[ApplicationStep.PERSONAL_INFO] = PersonalInfoStepOutData(
                PersonalInfo(
                    name = "Вита",
                    surname = "Соколова",
                    birthday = Date(),
                    educationType = EducationType.HIGHER,
                    hasWorkingExperience = true
                )
            )
            outDataMap[ApplicationStep.EDUCATION] = EducationStepOutData(
                Education(
                    place = "ВГУ",
                    faculty = "ФКН",
                    speciality = "ПИИТ",
                    degree = null,
                    startDate = "2014",
                    endDate = "2020"
                )
            )
            outDataMap[ApplicationStep.EXPERIENCE] = ExperienceStepOutData(
                WorkingExperience(
                    place = "Surf",
                    position = "Android Team Lead",
                    dateFrom = "2017",
                    dateTo = "2020"
                )
            )
            outDataMap[ApplicationStep.MOTIVATION] = MotivationStepOutData(
                listOf(Motivation("Крутые проекты"))
            )
        }
    }
}