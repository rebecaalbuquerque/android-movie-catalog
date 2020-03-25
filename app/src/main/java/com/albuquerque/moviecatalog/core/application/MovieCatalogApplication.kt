package com.albuquerque.moviecatalog.core.application

import android.app.Application
import com.albuquerque.moviecatalog.app.data.AppDatabase
import com.albuquerque.moviecatalog.app.repository.IRemoteRepository
import com.albuquerque.moviecatalog.app.repository.IRepository
import com.albuquerque.moviecatalog.app.repository.RemoteRepository
import com.albuquerque.moviecatalog.app.repository.Repository
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase
import com.albuquerque.moviecatalog.app.viewmodel.MoviesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MovieCatalogApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupRoom()
        setupKoin()
    }

    private fun setupRoom() {
        AppDatabase.getInstance(this)
    }

    private fun setupKoin() {

        startKoin {
            androidContext(this@MovieCatalogApplication)

            val databaseModule = module {
                single { AppDatabase.getInstance(get())
                single { get<AppDatabase>().movieDAO }}
            }

            val repositoryModule = module {
                factory<IRemoteRepository> { RemoteRepository() }
                factory<IRepository> { Repository(remote = get()) }
            }

            val useCaseModule = module {
                factory { GetPopularUseCase(repository = get()) }
            }

            val viewModelModule = module {
                viewModel { MoviesViewModel(getPopularUseCase = get()) }
            }

            modules(listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule))

        }

    }

}