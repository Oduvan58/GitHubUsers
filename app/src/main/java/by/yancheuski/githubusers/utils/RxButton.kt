package by.yancheuski.githubusers.utils

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

fun View.observableClickListener(): Observable<View> {
    val publishSubject: PublishSubject<View> = PublishSubject.create()
    this.setOnClickListener {
        publishSubject.onNext(it)
    }
    return publishSubject
}