package co.veo.project.utility

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Utility config
        Utility.setContext(this)
        Utility.setUiHandler(mainLooper)
    }

    companion object {
        private var instance: MyApplication? = null
    }
}