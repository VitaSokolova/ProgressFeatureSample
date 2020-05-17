package com.example.progressfeaturesample.ui.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.about.di.AboutMeScreenConfigurator
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_about_me.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject

/**
 * Вью TODO
 */
class AboutMeFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bm: AboutMeBindModel

    override fun getScreenName() = "AboutMeFragmentView"

    override fun createConfigurator() = AboutMeScreenConfigurator(
        arguments ?: bundleOf()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_about_me, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findViews()
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        initListeners()
        bind()
    }

    private fun findViews() {}

    private fun initViews() {}

    private fun initListeners() {}

    private fun bind() {
        next_btn.clicks() bindTo bm.onNextPressedAction
    }
}