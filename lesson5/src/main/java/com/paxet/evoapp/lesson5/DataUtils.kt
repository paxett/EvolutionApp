package com.paxet.evoapp.lesson5

class DataUtils {
    fun generateMovies() : List<Movie> {
        return listOf(
            Movie(
                //Avengers: End Game
                "Avengers: End Game",
                R.drawable.color_poster_avengers_end_game,
                "Action, Adventure, Drama",
                "125 Reviews",
                3f,
                "137 min",
                "13+",
                false
            ),
            Movie(
                //Tenet
                "Tenet",
                R.drawable.color_poster_tenet,
                "Action, Sci-Fi, Thriller",
                "98 Reviews",
                5f,
                "97 min",
                "16+",
                true
            ),
            Movie(
                //Black Widow
                "Black Widow",
                R.drawable.color_poster_black_widow,
                "Action, Adventure, Sci-Fi",
                "38 Reviews",
                4f,
                "102 min",
                "13+",
                false
            ),
            Movie(
                //Wonder Woman 1984
                "Wonder Woman 1984",
                R.drawable.color_poster_wonder_woman_1984,
                "Action, Adventure, Fantasy",
                "74 Reviews",
                5f,
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
                R.drawable.robert_downey_jr
            ),
            Actor(
                "Chris Evans",
                R.drawable.chris_evans
            ),
            Actor(
                "Mark Ruffalo",
                R.drawable.mark_ruffalo
            ),
            Actor(
                "Chris Hemsworth",
                R.drawable.chris_hemsworth
            )
        )
    }
}