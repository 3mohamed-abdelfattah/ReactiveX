package com.example.rxjava

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.rxjava.databinding.ActivityMain2Binding
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        single()
        maybe()
    }

    @SuppressLint("CheckResult")
    private fun single() {

        // Use Completable
        val completable = Completable.create { emitter ->
            binding.editText.doOnTextChanged { text, start, before, count ->
                if (text.toString() == "000") {
                    emitter.onComplete()
                }
            }
        }

        completable.subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG", "onError: $d")

            }

            override fun onComplete() {
                Log.d("TAG", "onComplete: Completable")
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "onError: $e")
            }

        })


        //Use Single
        val single = Single.just(5)
        single.subscribe(object : SingleObserver<Int> {
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "onError: $e")
            }

            override fun onSuccess(t: Int) {
                Log.d("TAG", "onSuccess: $t")
            }

        })

    }

    //Use Maybe
    @SuppressLint("CheckResult")
    private fun maybe() {

        // Use Completable
        val maybe = Maybe.create<String> { emitter ->
            binding.editText.doOnTextChanged { text, start, before, count ->
                when (text.toString()) {
                    "hello" -> emitter.onSuccess("World")
                    "meow" -> emitter.onSuccess("MEOW")
                    "no" -> emitter.onComplete()
                }
            }
        }


        maybe.subscribe(object : MaybeObserver<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "onError: ${e.message}")
            }

            override fun onComplete() {
                Log.d("TAG", "onComplete: ")
            }

            override fun onSuccess(t: String) {
                Log.d("TAG", "onSuccess: $t")
            }

        })


    }


}