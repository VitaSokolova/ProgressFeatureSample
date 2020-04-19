package com.example.progressfeaturesample.ui.screens.education

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute
import androidx.fragment.app.Fragment

/**
 * Маршрут [EducationFragmentView].
 */
class EducationRoute : FragmentRoute() {

    override fun getFragmentClass() = EducationFragmentView::class.java
}