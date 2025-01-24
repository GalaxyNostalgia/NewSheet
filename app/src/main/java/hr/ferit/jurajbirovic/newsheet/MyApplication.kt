// File: app/src/main/java/hr/ferit/jurajbirovic/newsheet/MyApplication.kt
package hr.ferit.jurajbirovic.newsheet

import android.app.Application
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}