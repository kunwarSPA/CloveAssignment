package com.kotlin.employeeaccountapp.cloveList.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.employeeaccountapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveActivity : AppCompatActivity(){

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clovelist)

    }


}