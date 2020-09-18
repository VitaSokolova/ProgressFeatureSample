package com.example.progressfeaturesample.ui.screens.container

import android.app.FragmentTransaction
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationSteps
import com.example.progressfeaturesample.ui.screens.about.AboutMeRoute
import com.example.progressfeaturesample.ui.screens.education.EducationRoute
import com.example.progressfeaturesample.ui.screens.experience.ExperienceRoute
import com.example.progressfeaturesample.ui.screens.motivation.MotivationRoute
import com.example.progressfeaturesample.ui.screens.personal.PersonalInfoRoute
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.core.ui.navigation.activity.navigator.ActivityNavigator
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
    private val activityNavigator: ActivityNavigator,
    private val fragmentNavigator: FragmentNavigator,
    private val route: MainRoute,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        route.applicationDraft?.let {
            progressInteractor.applyDraft(it)
        }
        progressInteractor.initProgressFeature()

        progressInteractor.stepChangeObservable.withLatestFrom(
            bm.currentStepCount.observable
        ) bindTo { (stepWithPosition, currentPosition) ->
            val fragmentRoute = when (stepWithPosition.step) {
                 ApplicationSteps.PERSONAL_INFO -> PersonalInfoRoute()
                 ApplicationSteps.EDUCATION -> EducationRoute()
                 ApplicationSteps.EXPERIENCE -> ExperienceRoute()
                 ApplicationSteps.MOTIVATION -> MotivationRoute()
                 ApplicationSteps.ABOUT_ME -> AboutMeRoute()
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

        bm.onBackPressedClicked.observable.withLatestFrom(
            bm.currentStepCount.observable
        ) bindTo { (_, step) ->
            if (step.position == 0) {
                progressInteractor.closeProgressFeature()
                finish()
            } else {
                progressInteractor.toPreviousStep()
            }
        }
    }
}