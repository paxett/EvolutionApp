package com.paxet.evoapp.lesson10.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
        val intent = intent
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e("TAG", "#onNewIntent#")
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment?.toIntOrNull()
                if (id != null) {
                    val args = Bundle()
                    args.putInt("movieId", id)
                    val navHostFragment =
                        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
                    val navController = navHostFragment.navController
                    val destinationIdOfThisFragment = navController.currentDestination?.id
                    when (destinationIdOfThisFragment) {
                        R.id.moviesList -> navController.navigate(
                            R.id.action_moviesList_to_movieDetails,
                            args
                        )
                        R.id.movieDetails -> navController.navigate(
                            R.id.action_movieDetails_self,
                            args
                        )
                    }

                }
            }
        }
    }
}