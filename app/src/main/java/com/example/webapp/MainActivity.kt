package com.example.webapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.webapp.fragment.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MainFragment())
    }

    fun replaceFragment(fragment: Fragment?) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragment?.let { ft.add(R.id.fragment_container, it) }
        ft.addToBackStack(fragment?.tag)
        ft.commit()
    }
}