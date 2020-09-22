package utils.rx

import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers

fun <T> singleSchedulers(): SingleTransformer<T, T> {
    return SingleTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
    }
}