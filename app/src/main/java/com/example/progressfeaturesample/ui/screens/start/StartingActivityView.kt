package com.example.progressfeaturesample.ui.screens.start

import android.os.Bundle
import android.os.PersistableBundle
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.start.di.StartingScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_starting.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import javax.inject.Inject

/**
 * Стартовый экран
 */
class StartingActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bm: StartingBindModel

    override fun getScreenName() = "StartingActivityView"

    override fun createConfigurator() = StartingScreenConfigurator(intent)

    override fun getContentView(): Int = R.layout.activity_starting

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?,
        viewRecreated: Boolean
    ) {
        initListeners()
    }

    private fun initListeners() {
        new_btn.clicks() bindTo bm.openNewApplicationAction
        from_draft_btn.clicks() bindTo bm.openDraftAction
    }
}