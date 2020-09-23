package com.jjswigut.matters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjswigut.matters.ui.MatterListFragment

//ready to go
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MatterListFragment()
        fragmentTransaction.add(R.id.matter_list, fragment)
        fragmentTransaction.commit()
    }
}