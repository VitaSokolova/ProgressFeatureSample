package com.example.progressfeaturesample.ui.screens.container

import android.app.FragmentTransaction
import com.example.progressfeaturesample.interactors.*
import com.example.progressfeaturesample.ui.screens.education.EducationRoute
import com.example.progressfeaturesample.ui.screens.experience.ExperienceRoute
import com.example.progressfeaturesample.ui.screens.motivation.MotivationRoute
import com.example.progressfeaturesample.ui.screens.personal.PersonalInfoRoute
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.fragment.FragmentNavigator
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [MainActivity].
 */
@PerScreen
class MainActivityPresenter @Inject constructor(
    private val bm: MainBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    private val fragmentNavigator: FragmentNavigator,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        progressInteractor.initScenario()

        progressInteractor.stepChangeObservable.withLatestFrom(
            bm.currentStepCount.observable
        ) bindTo { (stepWithPosition, currentPosition) ->
            val fragmentRoute = when (stepWithPosition.step) {
                is PersonalInfoStep -> PersonalInfoRoute()
                is EducationStep -> EducationRoute()
                is ExperienceStep -> ExperienceRoute()
                is MotivationStep -> MotivationRoute()
            }

            if (stepWithPosition.position > currentPosition.position) {
                fragmentNavigator.add(fragmentRoute, true, FragmentTransaction.TRANSIT_NONE)
            } else {
                fragmentNavigator.popBackStack(fragmentRoute, false)
            }

            bm.currentStepState.accept(stepWithPosition.step)
            bm.currentStepCount.accept(
                StepPosition(
                    stepWithPosition.position,
                    stepWithPosition.allStepsCount
                )
            )
        }

        bm.onBackPressedClicked bindTo {
            progressInteractor.toPreviousStep()
        }
    }
}