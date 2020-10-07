package com.jjswigut.matters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jjswigut.matters.ui.matterlist.MatterListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        loadFragment(MatterListFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            null
        )
    }


    private fun loadFragment(fragment: Fragment): Boolean {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
        return true

    }
}