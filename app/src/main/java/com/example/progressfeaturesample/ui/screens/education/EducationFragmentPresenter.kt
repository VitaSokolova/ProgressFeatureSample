package com.example.progressfeaturesample.ui.screens.education

import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStep
import com.example.progressfeaturesample.interactors.application.steps.EducationStepOutData
import com.example.progressfeaturesample.ui.utils.filter
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import javax.inject.Inject

/**
 * Презентер [EducationFragmentView].
 */
@PerScreen
class EducationFragmentPresenter @Inject constructor(
    private val bm: EducationBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction.observable.withLatestFrom(
            bm.educationState.observable
        ) bindTo { (_, education) ->
            subscribeIoHandleError(
                progressInteractor.completeStep(EducationStepOutData(education))
            ) {
                // обработка успешного завершения
            }
        }

        bm.placeChangedAction bindTo { placeTxt ->
            bm.educationState.change {
                it.copy(place = placeTxt)
            }
        }
        bm.facultyChangedAction bindTo { facultyTxt ->
            bm.educationState.change {
                it.copy(faculty = facultyTxt)
            }
        }
        bm.specialtyChangedAction bindTo { speciality ->
            bm.educationState.change {
                it.copy(speciality = speciality)
            }
        }
        bm.dateFromChangedAction bindTo { dateFrom ->
            bm.educationState.change {
                it.copy(startDate = dateFrom)
            }
        }
        bm.dateToChangedAction bindTo { dateTo ->
            bm.educationState.change {
                it.copy(endDate = dateTo)
            }
        }

        //т.к. данные обязаны прийти из предыдущего шага, это быстро и не трубуют доп. запросов
        subscribeIoHandleError(
            progressInteractor.getDataForStep(ApplicationStep.EDUCATION)
                .filter<ApplicationStepData.EducationStepData>(),
            {
                bm.educationTypeState.accept(it.inData.educationType)
                it.outData?.education?.let {
                    bm.draftCommand.accept(it)
                }
            },
            {}
        )
    }
}