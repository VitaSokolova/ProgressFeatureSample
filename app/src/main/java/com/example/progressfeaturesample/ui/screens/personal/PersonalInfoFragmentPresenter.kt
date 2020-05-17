package com.example.progressfeaturesample.ui.screens.personal

import com.example.progressfeaturesample.domain.PersonalInfo
import com.example.progressfeaturesample.interactors.ApplicationProgressInteractor
import com.example.progressfeaturesample.interactors.PersonalInfoStepOut
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
            subscribeIoHandleError(progressInteractor.completeStep(PersonalInfoStepOut(it))) {
                // если нужны какие-то действия на onCompleted()
            }
        }
    }
}