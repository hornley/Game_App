package com.example.hornley

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val versionTextView = findViewById<TextView>(R.id.version)
        val playButton = findViewById<Button>(R.id.playbutton)
        versionTextView.text = "Version: ${BuildConfig.VERSION_NAME}"

        playButton.setOnClickListener {
            val intent = Intent(this, CharactersActivity::class.java)
            startActivity(intent)
        }
    }
}
