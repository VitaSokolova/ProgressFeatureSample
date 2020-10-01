package com.example.progressfeaturesample.ui.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
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

fun ViewGroup.createChip(
    layoutInflater: LayoutInflater,
    tag: String,
    text: String,
    isChecked: Boolean = false
): Chip {
    val chip = layoutInflater.inflate(R.layout.chip_layout, this, false) as Chip
    return chip.apply {
        this.text = text
        this.isChecked = isChecked
        this.tag = tag
    }
}