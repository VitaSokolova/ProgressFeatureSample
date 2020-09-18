package com.example.progressfeaturesample.ui.screens.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.ui.screens.education.di.EducationScreenConfigurator
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.fragment_education.*
import ru.surfstudio.android.core.mvp.binding.rx.ui.BaseRxFragmentView
import javax.inject.Inject

/**
 * Фрагмент с информацией об образовании
 */
class EducationFragmentView : BaseRxFragmentView() {

    @Inject
    lateinit var bm: EducationBindModel

    override fun getScreenName() = "EducationFragmentView"

    override fun createConfigurator() =
        EducationScreenConfigurator(
            arguments ?: bundleOf()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_education, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?, viewRecreated: Boolean) {
        listen()
        bind()
    }

    private fun listen() {
        faculty_et.textChanges() bindTo { bm.facultyChangedAction.accept(it.toString()) }
        specialty_et.textChanges() bindTo { bm.specialtyChangedAction.accept(it.toString()) }
        place_et.textChanges() bindTo { bm.placeChangedAction.accept(it.toString()) }
        degree_et.textChanges() bindTo { bm.degreeChangedAction.accept(it.toString()) }
        start_et.textChanges() bindTo { bm.dateFromChangedAction.accept(it.toString()) }
        end_et.textChanges() bindTo { bm.dateToChangedAction.accept(it.toString()) }
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }

        bm.educationTypeState.bindTo {
            faculty_til.isVisible = it.isHigherEducation()
            specialty_til.isVisible = it.isHigherEducation() || it == EducationType.VOCATIONAL
            degree_til.isVisible = it == EducationType.POST_GRADUATE
            others_tv.isVisible = it == EducationType.POST_GRADUATE
        }

        bm.draftCommand bindTo {
            faculty_et.setText(it.faculty)
            specialty_et.setText(it.speciality)
            place_et.setText(it.place)
            degree_et.setText(it.degree)
            start_et.setText(it.startDate)
            end_et.setText(it.endDate)
        }
    }
}