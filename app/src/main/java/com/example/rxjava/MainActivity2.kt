package com.example.rxjava

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        single()
    }

    @SuppressLint("CheckResult")
    private fun single() {

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
}