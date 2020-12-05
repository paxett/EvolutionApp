package com.paxet.evoapp.lesson4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Use NavController instead of Fragment Manager
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.parent_fragment, MoviesList())
//                .commit()
//        }
    }

    fun generateMovies() : List<Movie> {
        return listOf(
            Movie(
                //Avengers: End Game
                "Avengers: End Game",
                "@drawable/color_poster_avengers_end_game",
                "Action, Adventure, Drama",
                "125 Reviews",
                "3",
                "137 min",
                "13+",
                false
            ),
            Movie(
                //Tenet
                "Tenet",
                "@drawable/color_poster_tenet",
                "Action, Sci-Fi, Thriller",
                "98 Reviews",
                "5",
                "97 min",
                "16+",
                true
            ),
            Movie(
                //Black Widow
                "Black Widow",
                "@drawable/color_poster_black_widow",
                "Action, Adventure, Sci-Fi",
                "38 Reviews",
                "4",
                "102 min",
                "13+",
                false
            ),
            Movie(
                //Wonder Woman 1984
                "Wonder Woman 1984",
                "@drawable/color_poster_wonder_woman1984",
                "Action, Adventure, Fantasy",
                "74 Reviews",
                "6",
                "120 min",
                "13+",
                false
            )
        )
    }

        fun generateActors() : List<Actor> {
            return listOf(
                Actor(
                    "Robert Downey Jr.",
                    "@drawable/robert_downey_jr"
                ),
                Actor(
                    "Chris Evans",
                    "@drawable/chris_evans"
                ),
                Actor(
                    "Mark Ruffalo",
                    "@drawable/mark_ruffalo"
                ),
                Actor(
                    "Chris Hemsworth",
                    "@drawable/chris_hemsworth"
                ),
            )
    }

}