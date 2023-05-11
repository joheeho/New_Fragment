package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    viewBinding.TextSecond.text = intent.getStringExtra("data")
    }
}