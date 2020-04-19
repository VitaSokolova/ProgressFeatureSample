package com.example.progressfeaturesample.ui.screens.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.personal.di.PersonalInfoScreenConfigurator
import kotlinx.android.synthetic.main.fragment_education.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject

/**
 * Вью TODO
 */
class PersonalInfoFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bm: PersonalInfoBindModel

    override fun getScreenName() = "PersonalInfoFragmentView"

    override fun createConfigurator() =
        PersonalInfoScreenConfigurator(
            arguments ?: bundleOf()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_personal_info, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        bind()
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
    }
}