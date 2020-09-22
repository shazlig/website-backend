package di

import data.libs.remote.HeaderInterceptor
import data.libs.remote.OkHttpClientFactory
import data.libs.remote.ParameterInterceptor
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import utils.constant.AppConstants

const val BASE_URL: String = "baseUrl"

val apiModule = module {


    single {
        return@single OkHttpClientFactory.create(
            interceptors =
            arrayOf(
                getHeaderInterceptor(),
                getParameterInterceptor(),
            ),
            showDebugLog = true,
            authenticator = null
        )
    }

    single(named(BASE_URL)) {
        AppConstants.BASE_URL
    }
}


private fun getParameterInterceptor(): Interceptor {
    val params = HashMap<String, String>()
    return ParameterInterceptor(params)
}

private fun getHeaderInterceptor(): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"
    return HeaderInterceptor(headers)
}