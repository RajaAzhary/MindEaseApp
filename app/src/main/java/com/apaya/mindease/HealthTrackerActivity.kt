package com.apaya.mindease

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class HealthTrackerActivity : AppCompatActivity() {

    private lateinit var datePickerButton: Button
    private lateinit var moodHappy: ImageView
    private lateinit var moodNeutral: ImageView
    private lateinit var moodSad: ImageView
    private lateinit var moodAngry: ImageView
    private lateinit var sleepHoursSpinner: Spinner
    private lateinit var sleepRateGoodnight: ImageView
    private lateinit var sleepRateNeutral: ImageView
    private lateinit var sleepRateNotSleepy: ImageView
    private lateinit var sleepRateNoSleep: ImageView
    private lateinit var exerciseYesCheckbox: CheckBox
    private lateinit var exerciseNoCheckbox: CheckBox
    private lateinit var exerciseText: TextView
    private lateinit var exerciseOptionsSpinner: Spinner
    private lateinit var commentEditText: EditText
    private lateinit var backButton: Button
    private lateinit var saveSubmitButton: Button

    private lateinit var selectedDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_tracker)

        datePickerButton = findViewById(R.id.date_picker_button)
        moodHappy = findViewById(R.id.mood_happy)
        moodNeutral = findViewById(R.id.mood_neutral)
        moodSad = findViewById(R.id.mood_sad)
        moodAngry = findViewById(R.id.mood_angry)
        sleepHoursSpinner = findViewById(R.id.sleep_hours_spinner)
        sleepRateGoodnight = findViewById(R.id.sleep_rate_goodnight)
        sleepRateNeutral = findViewById(R.id.sleep_rate_neutral)
        sleepRateNotSleepy = findViewById(R.id.sleep_rate_not_sleepy)
        sleepRateNoSleep = findViewById(R.id.sleep_rate_no_sleep)
        exerciseYesCheckbox = findViewById(R.id.exercise_yes_checkbox)
        exerciseNoCheckbox = findViewById(R.id.exercise_no_checkbox)
        exerciseText = findViewById(R.id.what_exercise_text)
        exerciseOptionsSpinner = findViewById(R.id.exercise_options_spinner)
        commentEditText = findViewById(R.id.comment_edit_text)
        backButton = findViewById(R.id.back_button)
        saveSubmitButton = findViewById(R.id.save_submit_button)

        selectedDate = Calendar.getInstance()

        datePickerButton.setOnClickListener {
            showDatePickerDialog()
        }

        moodHappy.setOnClickListener {
            selectMood(Mood.HAPPY)
        }

        moodNeutral.setOnClickListener {
            selectMood(Mood.NEUTRAL)
        }

        moodSad.setOnClickListener {
            selectMood(Mood.SAD)
        }

        moodAngry.setOnClickListener {
            selectMood(Mood.ANGRY)
        }

        sleepRateGoodnight.setOnClickListener {
            selectSleepRate(SleepRate.GOODNIGHT)
        }

        sleepRateNeutral.setOnClickListener {
            selectSleepRate(SleepRate.NEUTRAL)
        }

        sleepRateNotSleepy.setOnClickListener {
            selectSleepRate(SleepRate.NOT_SLEEPY)
        }

        sleepRateNoSleep.setOnClickListener {
            selectSleepRate(SleepRate.NO_SLEEP)
        }

        exerciseYesCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                exerciseNoCheckbox.isChecked = false
                showExerciseOptions(true)
            }
        }

        exerciseNoCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                exerciseYesCheckbox.isChecked = false
                showExerciseOptions(false)
            }
        }

        backButton.setOnClickListener {
            finish()
        }

        saveSubmitButton.setOnClickListener {
            val comment = commentEditText.text.toString()
            saveHealthTracker(comment)
        }

        val sleepHoursOptions = arrayOf("< 4 hours", "4 - 5 hours", "6 - 7 hours", "8 hours", "> 8 hours")
        val sleepHoursAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sleepHoursOptions)
        sleepHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sleepHoursSpinner.adapter = sleepHoursAdapter

        val exerciseOptions = arrayOf("Gym", "Cardio", "Yoga", "Stretch")
        val exerciseOptionsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, exerciseOptions)
        exerciseOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        exerciseOptionsSpinner.adapter = exerciseOptionsAdapter
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                updateSelectedDate()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.maxDate = System.currentTimeMillis()
        datePicker.show()
    }

    private fun updateSelectedDate() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val selectedDateText = dateFormat.format(selectedDate.time)
        datePickerButton.text = selectedDateText
    }

    private fun selectMood(mood: Mood) {
        moodHappy.isSelected = false
        moodNeutral.isSelected = false
        moodSad.isSelected = false
        moodAngry.isSelected = false

        when (mood) {
            Mood.HAPPY -> moodHappy.isSelected = true
            Mood.NEUTRAL -> moodNeutral.isSelected = true
            Mood.SAD -> moodSad.isSelected = true
            Mood.ANGRY -> moodAngry.isSelected = true
        }
    }

    private fun selectSleepRate(sleepRate: SleepRate) {
        sleepRateGoodnight.isSelected = false
        sleepRateNeutral.isSelected = false
        sleepRateNotSleepy.isSelected = false
        sleepRateNoSleep.isSelected = false

        when (sleepRate) {
            SleepRate.GOODNIGHT -> sleepRateGoodnight.isSelected = true
            SleepRate.NEUTRAL -> sleepRateNeutral.isSelected = true
            SleepRate.NOT_SLEEPY -> sleepRateNotSleepy.isSelected = true
            SleepRate.NO_SLEEP -> sleepRateNoSleep.isSelected = true
        }
    }

    private fun showExerciseOptions(show: Boolean) {
        if (show) {
            exerciseText.visibility = View.VISIBLE
            exerciseOptionsSpinner.visibility = View.VISIBLE
        } else {
            exerciseText.visibility = View.GONE
            exerciseOptionsSpinner.visibility = View.GONE
        }
    }

    private fun saveHealthTracker(comment: String) {

    }

    enum class Mood {
        HAPPY,
        NEUTRAL,
        SAD,
        ANGRY
    }

    enum class SleepRate {
        GOODNIGHT,
        NEUTRAL,
        NOT_SLEEPY,
        NO_SLEEP
    }
}
