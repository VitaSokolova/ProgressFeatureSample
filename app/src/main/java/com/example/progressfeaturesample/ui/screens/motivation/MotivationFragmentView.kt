package com.example.progressfeaturesample.ui.screens.motivation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.ui.screens.motivation.di.MotivationScreenConfigurator
import com.example.progressfeaturesample.ui.utils.createChip
import kotlinx.android.synthetic.main.fragment_education.next_btn
import kotlinx.android.synthetic.main.fragment_motivation.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.utilktx.data.wrapper.selectable.SelectableData
import javax.inject.Inject

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
        listen()
        bind()
    }

    private fun listen() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
    }

    private fun bind() {
        bm.motivationVariantsState bindTo {
            motivation_pb.isVisible = it.isLoading()
            motivation_error_tv.isVisible = it.isErrorLoading()
            chips_container.isVisible = it.isNormal()
            createChips(it.data)
        }
    }

    private fun createChips(data: List<SelectableData<Motivation>>) {
        data.forEach {
            val chip = requireContext().createChip(
                tag = it.data.text,
                text = it.data.text
            )
            chips_group.addView(chip)
        }
    }
}

