package com.example.progressfeaturesample.ui.screens.container

import android.content.Context
import android.content.Intent
import com.example.progressfeaturesample.interactors.application.ApplicationDraft
import ru.surfstudio.android.core.ui.navigation.Route
import ru.surfstudio.android.core.ui.navigation.activity.route.ActivityWithParamsRoute

/**
 * Маршрут [MainActivity].
 */
class MainRoute(val applicationDraft: ApplicationDraft? = null) : ActivityWithParamsRoute() {

    constructor(intent: Intent) : this(intent.getSerializableExtra(Route.EXTRA_FIRST) as? ApplicationDraft?)

    override fun prepareIntent(context: Context) = Intent(context, MainActivity::class.java).apply {
        putExtra(Route.EXTRA_FIRST, applicationDraft)
    }
}