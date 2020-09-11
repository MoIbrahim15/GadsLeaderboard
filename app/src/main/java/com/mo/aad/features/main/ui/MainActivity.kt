package com.mo.aad.features.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mo.aad.R
import com.mo.aad.features.submission.ui.SubmissionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pager.adapter = PagerAdapter(fm = supportFragmentManager)
        tabLayout.setupWithViewPager(pager)
        btnSubmit.setOnClickListener { startActivity(Intent(this, SubmissionActivity::class.java)) }
    }
}