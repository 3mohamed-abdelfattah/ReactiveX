package com.example.rxjava

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        foo()
        fooRx()

    }

    // Normal assign variables
    private fun foo() {
        var foo = 5
        Log.i("TAG_test", "This is an info $foo")
        foo = 40
        Log.i("TAG_test", "This is an info $foo")
        foo = 70
        Log.i("TAG_test", "This is an info $foo")
        foo = 80
        Log.i("TAG_test", "This is an info $foo")
        foo = 90
        Log.i("TAG_test", "This is an info $foo")
        foo = 100
        Log.d("TAG_test", "This is a debug $foo")
        Log.i("TAG_test", "This is an info $foo")
        Log.w("TAG_test", "This is a warning $foo")
        Log.e("TAG_test", "This is an error $foo")
    }


    
    // Use RxJava
    private fun fooRx() {
        var fooRX = Observable.just(5,40,70,80,90,100)
        val observer = object : Observer<Int>{
            override fun onSubscribe(d: Disposable) {
                Log.d("TAG_RX", "onSubscribe: ")
            }

            override fun onError(e: Throwable) {
                Log.e("TAG_ERROR", "onError: $e", )
            }

            override fun onComplete() {
                Log.d("TAG", "onComplete: ")
            }

            override fun onNext(t: Int) {
                Log.i("TAG", "onNext: $t")
            }
        }
        fooRX.subscribe(observer)
    }

}