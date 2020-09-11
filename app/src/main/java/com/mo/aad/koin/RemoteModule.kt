package com.mo.aad.koin

import com.mo.aad.features.main.remote.LearningAPIService
import com.mo.aad.features.main.remote.SkillIQAPIService
import com.mo.aad.features.submission.remote.SubmissionApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DynamicRetrofit {

    val BASE_URL = "https://gadsapi.herokuapp.com"
    val FORM_URL = "https://docs.google.com/forms/d/e/"

    fun buildBaseRetrofit(urlType: Int): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(6, TimeUnit.SECONDS)
        httpClient.writeTimeout(6, TimeUnit.SECONDS)
        httpClient.connectTimeout(6, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(if (urlType == 1) BASE_URL else FORM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

    }
}

val remoteModule = module {
    factory { provideLearningAPI(DynamicRetrofit().buildBaseRetrofit(1)) }
    factory { provideSkillIQAPI(DynamicRetrofit().buildBaseRetrofit(1)) }
    factory { provideSubmissionAPI(DynamicRetrofit().buildBaseRetrofit(2)) }
}


fun provideSkillIQAPI(retrofit: Retrofit): SkillIQAPIService =
    retrofit.create(SkillIQAPIService::class.java)

fun provideLearningAPI(retrofit: Retrofit): LearningAPIService =
    retrofit.create(LearningAPIService::class.java)

fun provideSubmissionAPI(retrofit: Retrofit): SubmissionApiService =
    retrofit.create(SubmissionApiService::class.java)
