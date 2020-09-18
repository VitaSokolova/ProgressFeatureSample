
package com.example.progressfeaturesample.ui.screens.start

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityRoute

/**
 * Маршрут [StartingActivityView].
 */
class StartingRoute : ActivityRoute() {

    override fun prepareIntent(context: Context) = Intent(context, StartingActivityView::class.java).apply {
    }
}