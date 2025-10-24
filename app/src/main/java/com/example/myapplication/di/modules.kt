package com.example.myapplication.di

import com.example.myapplication.feature.dollar.data.database.AppRoomDataBase
import com.example.myapplication.feature.dollar.data.datasource.DollarLocalDataSource
import com.example.myapplication.feature.dollar.data.datasource.RealTimeRemoteDataSource
import com.example.myapplication.feature.dollar.data.repository.DollarRepository
import com.example.myapplication.feature.dollar.domain.repository.IDollarRepository
import com.example.myapplication.feature.dollar.domain.usecase.FetchDollarUseCase
import com.example.myapplication.feature.dollar.presentation.DollarViewModel
import com.example.myapplication.feature.github.data.api.GithubService
import com.example.myapplication.feature.github.data.datasource.GithubRemoteDataSource
import com.example.myapplication.feature.github.data.repository.GitHubRepository
import com.example.myapplication.feature.github.domain.repository.IgitHubRepository
import com.example.myapplication.feature.github.domain.usercases.FindByNicknameUseCase
import com.example.myapplication.feature.github.presentation.github.GithubViewModel
import com.example.myapplication.feature.maintenance.data.MaintenanceDataStore
import com.example.myapplication.feature.maintenance.data.MaintenanceRepository
import com.example.myapplication.feature.maintenance.presentation.MaintenanceViewModel
import com.example.myapplication.feature.movie_pages.data.api.MoviesService
import com.example.myapplication.feature.movie_pages.data.database.MoviesRoomDatabase
import com.example.myapplication.feature.movie_pages.data.datasorce.MoviesLocalDataSource
import com.example.myapplication.feature.movie_pages.data.datasorce.MoviesRemoteDataSource
import com.example.myapplication.feature.movie_pages.data.repository.MoviesRepository
import com.example.myapplication.feature.movie_pages.data.repository.WatchLaterRepository
import com.example.myapplication.feature.movie_pages.domain.repository.IMoviesRepository
import com.example.myapplication.feature.movie_pages.domain.repository.IWatchLaterRepository
import com.example.myapplication.feature.movie_pages.domain.usecases.AddToWatchLaterUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.GetMoviesUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.GetWatchLaterMoviesUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.IsInWatchLaterUseCase
import com.example.myapplication.feature.movie_pages.domain.usecases.RemoveFromWatchLaterUseCase
import com.example.myapplication.feature.movie_pages.presentation.MoviesViewModel
import com.example.myapplication.feature.profile.presentation.profile.ProfileViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    // OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Retrofit para GitHub
    single(named("githubRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit para Movies (TMDB)
    single(named("moviesRetrofit")) {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicios
    single<GithubService> {
        get<Retrofit>(named("githubRetrofit")).create(GithubService::class.java)
    }

    single<MoviesService> {
        get<Retrofit>(named("moviesRetrofit")).create(MoviesService::class.java)
    }

    // DataSources
    single { GithubRemoteDataSource(get()) }
    single { MoviesRoomDatabase.getDatabase(get()) }
    single { get<MoviesRoomDatabase>().movieDao() }

    single { get<MoviesRoomDatabase>().watchLaterDao() }
    single { MoviesRemoteDataSource(get()) }
    single { MoviesLocalDataSource(get()) }

    // Repositorios
    single<IgitHubRepository> { GitHubRepository(get()) }
    single<IMoviesRepository> { MoviesRepository(get(), get()) }

    single<IWatchLaterRepository> { WatchLaterRepository(get()) }

    // UseCases
    factory { FindByNicknameUseCase(get()) }
    factory { GetMoviesUseCase(get()) }

    factory { GetWatchLaterMoviesUseCase(get()) }
    factory { AddToWatchLaterUseCase(get()) }
    factory { RemoveFromWatchLaterUseCase(get()) }
    factory { IsInWatchLaterUseCase(get()) }

    // ViewModels
    viewModel { GithubViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

    viewModel { MoviesViewModel(get(), get(), get(), get(), get()) }

    //DOLLAR
    //DataSources
    single { AppRoomDataBase.getDatabase(get()) } //Data base room
    single { get<AppRoomDataBase>().dollarDao() } //Data base room
    single { RealTimeRemoteDataSource() }
    single { DollarLocalDataSource(get()) } //Data base room
    //Repository
    single<IDollarRepository> { DollarRepository(get(), get()) }
    //UseCases
    factory { FetchDollarUseCase(get()) }
    //ViewModels
    viewModel { DollarViewModel(get()) }

    // Maintenance
    single { MaintenanceDataStore(get()) }
    single {
        FirebaseRemoteConfig.getInstance()
    }
    single { MaintenanceRepository(get(), get()) }
    viewModel { MaintenanceViewModel(get()) }
}