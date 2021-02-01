package com.paxet.evoapp.lesson8.data.network.tmdbapi

import com.paxet.evoapp.lesson8.data.db.Movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListAPI(

	@SerialName("dates")
	val dates: DatesAPI? = null,

	@SerialName("page")
	val page: Int? = null,

	@SerialName("total_pages")
	val totalPages: Int? = null,

	@SerialName("results")
	val results: List<MovieItemAPI>? = null,

	@SerialName("total_results")
	val totalResults: Int? = null
)

@Serializable
data class MovieItemAPI (

	@SerialName("overview")
	val overview: String? = null,

	@SerialName("original_language")
	val originalLanguage: String? = null,

	@SerialName("original_title")
	val originalTitle: String? = null,

	@SerialName("video")
	val video: Boolean? = null,

	@SerialName("title")
	val title: String? = null,

	@SerialName("genre_ids")
	val genreIds: List<Int?>? = null,

	@SerialName("poster_path")
	val posterPath: String? = null,

	@SerialName("backdrop_path")
	val backdropPath: String? = null,

	@SerialName("release_date")
	val releaseDate: String? = null,

	@SerialName("popularity")
	val popularity: Double? = null,

	@SerialName("vote_average")
	val voteAverage: Double? = null,

	@SerialName("id")
	val id: Int? = null,

	@SerialName("adult")
	val adult: Boolean? = null,

	@SerialName("vote_count")
	val voteCount: Int? = null
)

@Serializable
data class DatesAPI(

	@SerialName("maximum")
	val maximum: String? = null,

	@SerialName("minimum")
	val minimum: String? = null
)

//Convert MovieItemAPI to DB Movies class
fun MovieItemAPI.toMovies() : Movies {
	return Movies(this.id,
		this.title, this.genreIds?.joinToString(","),
		this.voteCount,
		this.adult,
		this.voteAverage,
		this.overview)
}
