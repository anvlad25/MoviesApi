package com.example.moviesapi.api

import com.example.moviesapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.HttpUrl


object MoviesApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl =
            request.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}