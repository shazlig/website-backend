package data.libs.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    fun<T> createReactiveService(
        serviceClass: Class<T>,
        okhttpClient: OkHttpClient,
        baseURl: String
    ): T {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okhttpClient)
            .build()
        return retrofit.create(serviceClass)
    }

}