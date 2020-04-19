package com.example.progressfeaturesample.ui.screens.container

import android.os.Bundle
import android.os.PersistableBundle
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.container.di.MainScreenConfigurator
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import ru.surfstudio.android.core.ui.FragmentContainer
import javax.inject.Inject

/**
 * Вью TODO
 */
class MainActivity : BaseRxActivityView(), FragmentContainer {

    @Inject
    lateinit var bm: MainBindModel

    override fun getScreenName() = "MainActivityView"

    override fun createConfigurator() = MainScreenConfigurator(intent)

    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?,
        viewRecreated: Boolean
    ) {
        bind()
    }

    override fun getContentContainerViewId(): Int = R.id.fragments_container

    private fun bind() {
        toolbar.setNavigationOnClickListener { bm.onBackPressedClicked.accept() }

        bm.currentStepCount bindTo {
            progress_step_view.setStepCount(it.allStepsCount)
            progress_step_view.setCurrentStepNum(it.position + 1)// из-за нумерации шагов с 0
        }
    }
}