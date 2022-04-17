package com.deltarfd.deltagames.data.api

import com.deltarfd.deltagames.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("key", BuildConfig.RAWG_API_KEY)
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}