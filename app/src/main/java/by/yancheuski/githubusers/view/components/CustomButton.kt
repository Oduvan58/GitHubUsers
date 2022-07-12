package by.yancheuski.githubusers.view.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class CustomButton(context: Context, attributeSet: AttributeSet) :
    AppCompatButton(context, attributeSet) {

    val buttonObservable: Observable<Unit> = PublishSubject.create()

    init {
        this.setOnClickListener {
            buttonObservable.click().onNext(Unit)
        }
    }

    private fun <T : Any> Observable<T>.click(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not Observable!")
    }
}
