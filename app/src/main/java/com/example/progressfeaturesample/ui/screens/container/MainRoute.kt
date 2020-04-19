
package com.example.progressfeaturesample.ui.screens.container

import android.content.Context
import android.content.Intent
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityRoute

/**
 * Маршрут [MainActivity].
 */
class MainRoute : ActivityRoute() {

    override fun prepareIntent(context: Context) = Intent(context, MainActivity::class.java).apply {
    }
}