package com.example.progressfeaturesample.ui.screens.success

import android.os.Bundle
import android.os.PersistableBundle
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.success.di.ThankYouScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_thank_you.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxActivityView
import javax.inject.Inject

/**
 * Вью экрана успешного завершения флоу
 */
class ThankYouActivityView : BaseRxActivityView() {

    @Inject
    lateinit var bm: ThankYouBindModel

    override fun getScreenName() = "ThankYouActivityView"

    override fun createConfigurator() =
        ThankYouScreenConfigurator(
            intent
        )

    override fun getContentView(): Int = R.layout.activity_thank_you

    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?,
        viewRecreated: Boolean
    ) {
        initListeners()
    }

    private fun initListeners() {
        close_btn.clicks() bindTo bm.closeAction
    }
}