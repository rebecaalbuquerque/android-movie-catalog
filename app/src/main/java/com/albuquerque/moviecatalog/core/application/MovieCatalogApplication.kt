package com.albuquerque.moviecatalog.core.application

import android.app.Application
import com.albuquerque.moviecatalog.app.data.AppDatabase
import com.albuquerque.moviecatalog.app.repository.*
import com.albuquerque.moviecatalog.app.usecase.GetMoviesPaginatedUseCase
import com.albuquerque.moviecatalog.app.usecase.GetMoviesUseCase
import com.albuquerque.moviecatalog.app.viewmodel.MoviesPaginationViewModel
import com.albuquerque.moviecatalog.app.viewmodel.MoviesViewModel
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MovieCatalogApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupStetho()
        setupRoom()
        setupKoin()
    }

    private fun setupStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this).apply {
                    enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this@MovieCatalogApplication))
                }.build()
        )
    }

    private fun setupRoom() {
        AppDatabase.getInstance(this)
    }

    private fun setupKoin() {

        startKoin {
            androidContext(this@MovieCatalogApplication)

            val databaseModule = module {
                single { AppDatabase.getInstance(get()) }
                single { get<AppDatabase>().movieDAO }
            }

            val repositoryModule = module {
                factory<IRemoteRepository> { RemoteRepository() }
                factory<ILocalRepository> { LocalRepository(movieDao = get()) }
                factory<IRepository> { Repository(remote = get(), local = get()) }
            }

            val useCaseModule = module {
                factory { GetMoviesPaginatedUseCase(repository = get()) }
                factory { GetMoviesUseCase(repository = get()) }
            }

            val viewModelModule = module {
                viewModel { MoviesViewModel(getMoviesPaginatedUseCase = get()) }
                viewModel { MoviesPaginationViewModel(getMoviesPaginatedUseCase = get()) }
            }

            modules(listOf(databaseModule, repositoryModule, useCaseModule, viewModelModule))

        }

    }

}