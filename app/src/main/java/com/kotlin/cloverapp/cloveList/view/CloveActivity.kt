package com.kotlin.cloverapp.cloveList.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.cloverapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveActivity : AppCompatActivity(){

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clovelist)

    }


}