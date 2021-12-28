package com.kotlin.employeeaccountapp.cloveList.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.employeeaccountapp.R
import com.kotlin.employeeaccountapp.databinding.ActivityClovelistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloveActivity : AppCompatActivity(){

    private lateinit var binding: ActivityClovelistBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClovelistBinding.inflate(layoutInflater)
        setContentView(binding.root)
         navigateToEditPage()
    }

     fun navigateToEditPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame_container,
                CloveListFragment.newInstance(),
                CloveListFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }
}