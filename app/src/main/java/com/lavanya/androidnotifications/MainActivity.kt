package com.lavanya.androidnotifications

import android.graphics.BitmapFactory
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(
                    "first",
                    "Default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            nm.createNotificationChannel(
                NotificationChannel(
                    "second",
                    "Misc",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }


        btnSimple.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this, "first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello There")
                .setContentText("General Master!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(System.currentTimeMillis().toInt(), simpleNotification)
        }

        btnIntent.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi: PendingIntent =
                PendingIntent.getActivity(this, 12345, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello There")
                .setContentText("General Master!")
                .setContentIntent(pi)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(2, clickableNotification)
        }

        btnLongText.setOnClickListener {

            val bigTextStyle = NotificationCompat.Builder(this, "first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello There")
                .setContentText("General Master!")
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.big_string))
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(3, bigTextStyle)
        }

        btnAction.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi: PendingIntent =
                PendingIntent.getActivity(this, 12345, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val actionedNotification = NotificationCompat.Builder(this, "first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello There")
                .setContentText("General Master!")
                .addAction(R.mipmap.ic_launcher, "Click", pi)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(4, actionedNotification)
        }

        btnExpanding.setOnClickListener {

            val icon = BitmapFactory.decodeResource(
                this.resources,
                R.drawable.ic_launcher_foreground
            )

            val actionedNotification = NotificationCompat.Builder(this, "first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello There")
                .setContentText("General Kenobi!")
                .setLargeIcon(icon)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(icon)
                        .bigLargeIcon(null)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(5, actionedNotification)
        }
    }
}