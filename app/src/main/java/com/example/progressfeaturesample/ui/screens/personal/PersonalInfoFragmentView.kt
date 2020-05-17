package com.example.progressfeaturesample.ui.screens.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.domain.EducationType
import com.example.progressfeaturesample.ui.screens.personal.di.PersonalInfoScreenConfigurator
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.fragment_personal_info.*
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
        initSpinner()
        bind()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.education_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun bind() {
        experience_cb.setOnCheckedChangeListener { _, isChecked ->
            bm.experienceCheckedAction.accept(isChecked)
        }
        name_tf.textChanges() bindTo { bm.nameAction.accept(it.toString()) }
        surname_tf.textChanges() bindTo { bm.surnameAction.accept(it.toString()) }
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val education = EducationType.values()[position]
                bm.educationSelectedAction.accept(education)
            }
        }
    }
}