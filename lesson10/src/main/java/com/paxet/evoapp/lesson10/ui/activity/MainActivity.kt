package com.paxet.evoapp.lesson10.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.paxet.evoapp.lesson10.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Clear shared preferences on app start
        val pref: SharedPreferences = getSharedPreferences("searchLine", 0)
        val editor: SharedPreferences.Editor = pref.edit()
        editor.clear()
        editor.apply()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (intent.action) {
            // Invoked when a dynamic shortcut is clicked.
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment?.toIntOrNull()
                if (id != null) {
                    val args = Bundle()
                    args.putInt("movieId", id)
                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
                    val navController = navHostFragment.navController
                    navController.navigate(R.id.action_moviesList_to_movieDetails, args)
                }
            }
        }
    }
}