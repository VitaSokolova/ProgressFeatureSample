package com.example.progressfeaturesample.ui.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.example.progressfeaturesample.R
import com.google.android.material.chip.Chip

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

fun Context.createChip(tag: String, text: String, isChecked: Boolean = false): Chip {
    return Chip(
        ContextThemeWrapper(this, R.style.Widget_MaterialComponents_Chip_Filter),
        null,
        0
    ).apply {
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT

//                val horizontalPadding = resources.getDimensionPixelSize(R.dimen.dp4).toFloat()
//                setPadding(horizontalPadding, 0, horizontalPadding, 0)

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

        textAlignment = LinearLayout.TEXT_ALIGNMENT_CENTER
        this.text = text
        this.isChecked = isChecked
        this.tag = tag
    }
}