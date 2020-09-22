package utils.rx

import io.reactivex.FlowableTransformer
import io.reactivex.schedulers.Schedulers

fun <T> flowableSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer {
        it.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
    }
}