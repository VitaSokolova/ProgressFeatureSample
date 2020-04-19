package com.example.progressfeaturesample.app.dagger.screen

import android.os.Bundle
import com.example.progressfeaturesample.app.dagger.activity.ActivityComponent
import ru.surfstudio.android.core.mvp.configurator.BaseFragmentViewConfigurator
import ru.surfstudio.android.core.mvp.fragment.CoreFragmentView
import ru.surfstudio.android.core.ui.activity.CoreActivityInterface

abstract class FragmentScreenConfigurator(
        args: Bundle
) : BaseFragmentViewConfigurator<ActivityComponent, FragmentScreenModule>(args) {

    override fun getFragmentScreenModule(): FragmentScreenModule {
        return FragmentScreenModule(persistentScope)
    }

    override fun getParentComponent(): ActivityComponent {
        return (getTargetFragmentView<CoreFragmentView>().activity as CoreActivityInterface)
                .persistentScope
                .configurator
                .activityComponent as ActivityComponent
    }
}