package com.budgetnah.pro.data.voice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class VoiceManager(private val context: Context) {

    fun startVoiceInput(onResult: (String) -> Unit) {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }

        val launcher = (context as ComponentActivity)
            .registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->

                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val results = data?.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )
                    onResult(results?.firstOrNull() ?: "")
                }
            }

        launcher.launch(intent)
    }
}