package com.example.progressfeaturesample.ui.screens.motivation

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute
import androidx.fragment.app.Fragment

/**
 * Маршрут [MotivationFragmentView].
 */
class MotivationRoute : FragmentRoute() {

    override fun getFragmentClass() = MotivationFragmentView::class.java
}