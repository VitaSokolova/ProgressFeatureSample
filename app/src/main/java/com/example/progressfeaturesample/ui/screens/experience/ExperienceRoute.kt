package com.example.progressfeaturesample.ui.screens.experience

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.fragment.route.FragmentRoute
import androidx.fragment.app.Fragment

/**
 * Маршрут [ExperienceFragmentView].
 */
class ExperienceRoute : FragmentRoute() {

    override fun getFragmentClass() = ExperienceFragmentView::class.java
}