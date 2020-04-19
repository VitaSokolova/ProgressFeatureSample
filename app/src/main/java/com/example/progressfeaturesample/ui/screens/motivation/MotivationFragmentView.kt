package com.example.progressfeaturesample.ui.screens.motivation

import android.os.Bundle
import androidx.core.os.bundleOf
import android.view.View
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.motivation.di.MotivationScreenConfigurator
import kotlinx.android.synthetic.main.fragment_education.*
import kotlinx.android.synthetic.main.fragment_education.next_btn
import kotlinx.android.synthetic.main.fragment_motivation.*

/**
 * Вью TODO
 */
class MotivationFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bm: MotivationBindModel

    override fun getScreenName() = "MotivationFragmentView"

    override fun createConfigurator() =
        MotivationScreenConfigurator(
            arguments ?: bundleOf()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_motivation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        bind()
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
    }
}