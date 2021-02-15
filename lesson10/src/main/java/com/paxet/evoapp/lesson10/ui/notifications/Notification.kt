package com.paxet.evoapp.lesson10.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.paxet.evoapp.lesson10.R
import com.paxet.evoapp.lesson10.data.network.tmdbapi.MovieItemAPI
import com.paxet.evoapp.lesson10.ui.activity.MainActivity
import java.time.Instant

interface Notification {
    fun initialize()
    fun showNotification(movie: MovieItemAPI)
}

class NewMovieNotification(private val context: Context) : Notification {
    var notificationManager: NotificationManagerCompat? = null
    init {
        notificationManager = NotificationManagerCompat.from(context)
        // Create the NotificationChannel
        val name = "New movie arrived"
        val descriptionText = "New movie received from TMDB"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("evoapp_new_movie", name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val channel = notificationManager?.createNotificationChannel(mChannel)
    }
    override fun initialize() {
        TODO("Not yet implemented")
    }

    override fun showNotification(movie: MovieItemAPI) {
        val pIntent = PendingIntent.getActivity(
            context, 1,
            Intent(context, MainActivity::class.java,)
                .setAction(Intent.ACTION_VIEW)
                .setData("https://www.paxet.evoapp.com/movie/${movie.id}".toUri()),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, "channel_id")
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setWhen(Instant.now().toEpochMilli())
            .setContentIntent(pIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .build()

        notificationManager?.notify("movie", 1, notification)
    }

}