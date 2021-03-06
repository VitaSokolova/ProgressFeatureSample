package com.example.progressfeaturesample.ui.screens.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.experience.di.ExperienceScreenConfigurator
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.fragment_experience.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject


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
        listen()
        bind()
    }

    private fun listen() {
        experience_add_one_more_tv.setOnClickListener {
            val toast = Toast.makeText(
                requireContext(),
                resources.getString(R.string.experience_to_mush_for_sample_toast_txt),
                Toast.LENGTH_SHORT
            )
            toast.show()
        }

        place_et.textChanges().map { it.toString() } bindTo bm.placeChangedAction
        position_et.textChanges().map { it.toString() } bindTo bm.positionChangedAction
        start_et.textChanges().map { it.toString() } bindTo bm.dateFromChangedAction
        end_et.textChanges().map { it.toString() } bindTo bm.dateToChangedAction

    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
        bm.draftCommand bindTo {
            place_et.setText(it.place)
            position_et.setText(it.position)
            start_et.setText(it.dateFrom)
            end_et.setText(it.dateTo)
        }
    }
}