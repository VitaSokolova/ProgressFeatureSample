package com.example.progressfeaturesample.interactors

import com.example.progressfeaturesample.interactors.common.ProgressInteractor
import ru.surfstudio.android.dagger.scope.PerApplication
import javax.inject.Inject

@PerApplication
class ApplicationProgressInteractor @Inject constructor() : ProgressInteractor<ApplicationSteps>() {
    override val scenario = ApplicationScenario()

    fun initScenario() = notifyStepChanges()
}