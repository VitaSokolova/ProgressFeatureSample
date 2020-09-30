package com.example.progressfeaturesample.ui.screens.personal

import com.example.progressfeaturesample.domain.PersonalInfo
import com.example.progressfeaturesample.interactors.application.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStepData
import com.example.progressfeaturesample.interactors.application.steps.ApplicationStep
import com.example.progressfeaturesample.interactors.application.steps.PersonalInfoStepOutData
import com.example.progressfeaturesample.ui.utils.filter
import io.reactivex.Observable
import io.reactivex.functions.Function4
import io.reactivex.rxkotlin.withLatestFrom
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxPresenter
import ru.surfstudio.android.core.mvp.presenter.BasePresenterDependency
import ru.surfstudio.android.dagger.scope.PerScreen
import java.util.*
import javax.inject.Inject

/**
 * Презентер [PersonalInfoFragmentView].
 */
@PerScreen
class PersonalInfoFragmentPresenter @Inject constructor(
    private val bm: PersonalInfoBindModel,
    private val progressInteractor: ApplicationProgressInteractor,
    basePresenterDependency: BasePresenterDependency
) : BaseRxPresenter(basePresenterDependency) {

    override fun onFirstLoad() {
        bm.onNextPressedAction.observable.withLatestFrom(
            Observable.combineLatest(
                bm.nameAction.observable,
                bm.surnameAction.observable,
                bm.educationSelectedAction.observable,
                bm.experienceCheckedAction.observable,
                Function4 { name, surname, education, experience ->
                    PersonalInfo(
                        name,
                        surname,
                        Date(), // тут у меня уже кончилось терпение верстать поля ради примера
                        education,
                        experience
                    )
                }
            )
        ) { _, info: PersonalInfo -> info } bindTo {
            subscribeIoHandleError(
                progressInteractor.completeStep(
                    PersonalInfoStepOutData(it)
                )
            ) {
                // если нужны какие-то действия на onCompleted()
            }
        }

        getDataFromDraft()
    }

    private fun getDataFromDraft() {
        subscribeIoHandleError(
            progressInteractor.getDataForStep(ApplicationStep.PERSONAL_INFO)
                .filter<ApplicationStepData.PersonalInfoStepData>(),
            {
                it.outData?.let { stepOutData ->
                    bm.draftData.accept(stepOutData)
                }
            },
            {}
        )
    }
}