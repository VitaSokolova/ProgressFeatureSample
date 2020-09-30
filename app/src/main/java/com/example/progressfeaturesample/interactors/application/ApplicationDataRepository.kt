package com.example.progressfeaturesample.interactors.application

import com.example.progressfeaturesample.domain.Application
import com.example.progressfeaturesample.domain.Motivation
import io.reactivex.Completable
import io.reactivex.Single
import ru.surfstudio.android.dagger.scope.PerApplication
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Репозиторий для доступа к данным для оформления заявки
 */
@PerApplication
class ApplicationDataRepository @Inject constructor() {

    /**
     * Имитирует загрузку списка причин, почему пользователя заинтересовала вакансия
     */
    fun loadMotivationVariants(): Single<List<Motivation>> =
        Single.just(
            listOf(
                Motivation("Зарплата"),
                Motivation("Локация"),
                Motivation("Бесплатная еда"),
                Motivation("Крутые проекты")
            )
        ).delay(
            1L, TimeUnit.SECONDS
        )

    /**
     * Имитирует финальную отправку заявки
     */
    fun loadApplication(application: Application): Completable {
        return Completable.timer(3L, TimeUnit.SECONDS)
    }
}