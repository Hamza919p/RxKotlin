package com.example.rxjava

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

//====================================== REACTIVE PRGRAMMING =========================================//
    // REACTIVE PROGRAMMING => Event driven asynchronous programming. Asynchronous means tasks that can be run on multiple threads
    //                         RxJava/RxKotlin works on the basis of observers
    //  Observerable listen to the data or backend or any database whether the data is updated and notify the observers


//=======================================================================================================//



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observer = getAnimalsObserver()
        val observable = getAnimalsObservable()

        observable.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }

    //This function is for observer that will observe the observable
    private fun getAnimalsObserver() : Observer<String> {
        return object: Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                //This function will tell whether observer and observable are connected
                Log.d("OnSubscribe: ", "On subscribe")
            }

            override fun onNext(t: String?) {
                //This function will be called when observeable ommits the data
                Log.d("OnNext: ", t!!)
            }

            override fun onError(e: Throwable?) {
                Log.d("OnNext: ", e?.message.toString())
            }

            override fun onComplete() {
                Log.d("Completed: ", "Completed")
            }
        }
    }

    //this function will observer the data if something is changed
    private fun getAnimalsObservable() : Observable<String> {
        return Observable.just("Ant", "Anti", "Uncle")
    }
}