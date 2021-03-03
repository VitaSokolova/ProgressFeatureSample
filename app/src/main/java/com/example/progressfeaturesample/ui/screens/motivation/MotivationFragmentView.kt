package com.example.progressfeaturesample.ui.screens.motivation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.ui.screens.motivation.di.MotivationScreenConfigurator
import com.example.progressfeaturesample.ui.utils.createChip
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_education.next_btn
import kotlinx.android.synthetic.main.fragment_motivation.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import ru.surfstudio.android.utilktx.data.wrapper.loadable.LoadStatus
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
            renderLoading(it.loadStatus)
            createChips(it.data)
        }

        bm.applicationSendingState bindTo ::renderLoading
    }

    private fun renderLoading(loadStatus: LoadStatus) {
        motivation_pb.isVisible = loadStatus == LoadStatus.LOADING
        next_btn.isInvisible = loadStatus == LoadStatus.LOADING
        motivation_error_tv.isVisible = loadStatus == LoadStatus.ERROR
        chips_container.isVisible = loadStatus == LoadStatus.NORMAL
    }

    private fun createChips(data: List<SelectableData<Motivation>>) {
        if (chips_group.children.count() == 0) {
            chips_group.removeAllViews()
            data.forEach {
                val chip = chips_group.createChip(
                    layoutInflater = layoutInflater,
                    tag = it.data.text,
                    text = it.data.text,
                    isChecked = it.isSelected
                )
                chip.setOnCheckedChangeListener { _, isChecked ->
                    bm.motivationCheckedAction.accept(it.data to isChecked)
                }
                chips_group.addView(chip)
            }
        } else {
            chips_group.children.forEach { view ->
                (view as? Chip)?.isChecked =
                    data.find { it.data.text == view.tag }?.isSelected ?: false
            }
        }
    }
}

