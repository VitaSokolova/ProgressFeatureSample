package com.example.progressfeaturesample.ui.screens.experience

import android.os.Bundle
import androidx.core.os.bundleOf
import android.view.View
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import com.example.progressfeaturesample.R
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.progressfeaturesample.ui.screens.experience.di.ExperienceScreenConfigurator
import kotlinx.android.synthetic.main.fragment_experience.*

/**
 * Вью TODO
 */
class ExperienceFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bm: ExperienceBindModel

    override fun getScreenName() = "ExperienceFragmentView"

    override fun createConfigurator() =
        ExperienceScreenConfigurator(
            arguments ?: bundleOf()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_experience, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        bind()
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
    }
}