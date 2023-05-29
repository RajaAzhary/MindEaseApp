package com.apaya.mindease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    private lateinit var healthTrackerButton: Button
    private lateinit var forumButton: Button
    private lateinit var counsellingButton: Button
    private lateinit var quizButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

        healthTrackerButton = findViewById(R.id.health_tracker_button)
        forumButton = findViewById(R.id.forum_button)
        counsellingButton = findViewById(R.id.counselling_button)
        quizButton = findViewById(R.id.quiz_button)

        healthTrackerButton.setOnClickListener {
            val intent = Intent(this, HealthTrackerActivity::class.java)
            startActivity(intent)
        }

        forumButton.setOnClickListener {
            val intent = Intent(this, ForumActivity::class.java)
            startActivity(intent)
        }

        counsellingButton.setOnClickListener {
            val intent = Intent(this, CounsellingActivity::class.java)
            startActivity(intent)
        }

        quizButton.setOnClickListener {
            val intent = Intent(this, HealthQuizActivity::class.java)
            startActivity(intent)
        }
    }
}






