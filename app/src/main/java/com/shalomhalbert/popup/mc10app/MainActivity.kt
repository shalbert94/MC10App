package com.shalomhalbert.popup.mc10app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shalomhalbert.popup.mc10app.fragments.LoginFragment

/**
 * [AppCompatActivity] that hosts all [Fragment]s
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Start with inflating the login screen
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,
                LoginFragment.newInstance(), LoginFragment.TAG).commit()

    }
}
