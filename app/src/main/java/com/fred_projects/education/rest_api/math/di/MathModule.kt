package com.fred_projects.education.rest_api.math.di

import com.fred_projects.database.MainDB
import com.fred_projects.education.rest_api.math.model.repository.IMSRepository
import com.fred_projects.education.rest_api.math.model.repository.MSRepository
import com.fred_projects.education.rest_api.math.model.service.IMathService
import com.fred_projects.education.rest_api.math.use_case.GetSolutionFMS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MathModule {
    @Provides
    @Singleton
    fun provideMathService(): IMathService {
        return Retrofit.Builder().baseUrl(IMathService.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build().create(IMathService::class.java)
    }
    @Provides
    @Singleton
    fun provideMSRepository(api: IMathService, db: MainDB): IMSRepository {
        return MSRepository(api, db.mathDao)
    }
    @Provides
    @Singleton
    fun provideGetMathInfo(msRepository: IMSRepository): GetSolutionFMS {
        return GetSolutionFMS(msRepository)
    }
}