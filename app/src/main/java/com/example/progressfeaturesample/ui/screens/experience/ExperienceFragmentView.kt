package com.example.progressfeaturesample.ui.screens.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.ui.screens.experience.di.ExperienceScreenConfigurator
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
        bind()

        experience_add_one_more_tv.setOnClickListener {
            val toast = Toast.makeText(
                requireContext(),
                "Это уже слишком для семпла'!",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }
    }
}