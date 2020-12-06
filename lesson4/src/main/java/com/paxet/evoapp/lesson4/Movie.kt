package com.paxet.evoapp.lesson4

data class Movie(
    val name : String,
    val poster : Int,
    val genres: String,
    val reviews_counter : String,
    val rating : Float,
    val duration: String,
    val age : String,
    var like : Boolean
)