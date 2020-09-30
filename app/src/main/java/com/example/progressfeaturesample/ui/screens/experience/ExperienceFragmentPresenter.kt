package com.example.progressfeaturesample.ui.screens.experience

import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStep
import com.example.progressfeaturesample.interactors.application.steps.ExperienceStepOutData
import com.example.progressfeaturesample.ui.utils.filter
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [ExperienceFragmentView].
 */
@PerScreen
class ExperienceFragmentPresenter @Inject constructor(
    private val bm: ExperienceBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction.observable.withLatestFrom(
            bm.experienceState.observable
        ) bindTo { (_, experince) ->
            subscribeIoHandleError(
                progressInteractor.completeStep(
                    ExperienceStepOutData(experince)
                ),
                {}
            )
        }

        bm.placeChangedAction bindTo { placeTxt ->
            bm.experienceState.change {
                it.copy(place = placeTxt)
            }
        }

        bm.positionChangedAction bindTo { positionTxt ->
            bm.experienceState.change {
                it.copy(position = positionTxt)
            }
        }

        bm.dateFromChangedAction bindTo { dateFromTxt ->
            bm.experienceState.change {
                it.copy(dateFrom = dateFromTxt)
            }
        }

        bm.dateToChangedAction bindTo { dateToTxt ->
            bm.experienceState.change {
                it.copy(dateTo = dateToTxt)
            }
        }

        subscribeIoHandleError(
            progressInteractor.getDataForStep(ApplicationStep.EXPERIENCE)
                .filter<ApplicationStepData.ExperienceStepData>(),
            {
                it.outData?.experience?.let { experience ->
                    bm.draftCommand.accept(experience)
                }
            },
            {}
        )
    }
}