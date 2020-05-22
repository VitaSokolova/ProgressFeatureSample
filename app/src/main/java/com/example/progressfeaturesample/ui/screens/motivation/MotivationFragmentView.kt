package com.example.progressfeaturesample.ui.screens.motivation

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.example.progressfeaturesample.R
import com.example.progressfeaturesample.domain.Motivation
import com.example.progressfeaturesample.ui.screens.motivation.di.MotivationScreenConfigurator
import com.google.android.material.chip.Chip
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
        bind()
    }

    private fun bind() {
        next_btn.setOnClickListener { bm.onNextPressedAction.accept() }

        bm.motivationVariantsState bindTo {
            motivation_pb.isVisible = it.isLoading()
            motivation_error_tv.isVisible = it.isErrorLoading()
            chips_container.isVisible = it.isNormal()
            createChips(it.data)
        }
    }

    private fun createChips(data: List<SelectableData<Motivation>>) {
        data.forEach {
            val chip = Chip(
                ContextThemeWrapper(context, R.style.Widget_MaterialComponents_Chip_Filter),
                null,
                0
            )

            chip.apply {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT

                TextViewCompat.setTextAppearance(this, R.style.RobotoRegular_14)

                setStateTextColor(
                    mapOf(
                        intArrayOf(android.R.attr.state_selected) to getThemeAttrColor(
                            context,
                            android.R.attr.windowBackground
                        ),
                        intArrayOf() to getThemeAttrColor(
                            context,
                            android.R.attr.colorAccent
                        )
                    )
                )

                setChipBackgroundColorResource(R.color.chip_bkg_color_selector)
                setChipCornerRadiusResource(R.dimen.dp16)
                setRippleColorResource(R.color.colorAccentVeryLight)
                isCheckable = true
                checkedIcon = null
                isCheckedIconVisible = false

                text = it.data.text
                textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
                isChecked = it.isSelected
                id = data.hashCode()
            }

            chips_group.addView(chip)
        }
    }

    /**
     * Получить значение атрибута цвета из темы, если нужна ссылка на ресурс, а не значение, см. getThemeAttr()
     */
    @ColorInt
    fun getThemeAttrColor(
        context: Context,
        @AttrRes colorAttr: Int,
        @ColorRes defaultColor: Int = android.R.color.white
    ): Int {
        val ta = context.obtainStyledAttributes(null, intArrayOf(colorAttr))

        try {
            return ta.getColor(0, ContextCompat.getColor(context, defaultColor))
        } finally {
            ta.recycle()
        }
    }

    /**
     * Устанавливает цвет текста для каждого состояния View
     * @param stateColorMap - Карта состояние - цвет из ресурса
     */
    fun TextView.setStateTextColor(stateColorMap: Map<IntArray, Int>) {
        val states = stateColorMap.keys.toTypedArray()
        val colors = stateColorMap.values.toIntArray()
        setTextColor(ColorStateList(states, colors))
    }
}

