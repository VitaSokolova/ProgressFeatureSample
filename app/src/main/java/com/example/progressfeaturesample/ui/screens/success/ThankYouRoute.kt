
package com.example.progressfeaturesample.ui.screens.success

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityRoute

/**
 * Маршрут [ThankYouActivityView].
 */
class ThankYouRoute : ActivityRoute() {

    override fun prepareIntent(context: Context) = Intent(context, ThankYouActivityView::class.java).apply {
    }
}