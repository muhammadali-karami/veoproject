package co.veo.project.utility

import android.util.Log
import co.veo.project.BuildConfig

class Logger {

    companion object {
        private const val TAG = "AppTag"

        fun i(value: String?) {
            if (BuildConfig.BUILD_TYPE.equals("debug", ignoreCase = true)) {
                Log.i(TAG, value!!)
            }
        }

        fun d(value: String?) {
            if (BuildConfig.BUILD_TYPE.equals("debug", ignoreCase = true)) {
                Log.d(TAG, value!!)
            }
        }

        fun w(value: String?) {
            if (BuildConfig.BUILD_TYPE.equals("debug", ignoreCase = true)) {
                Log.w(TAG, value!!)
            }
        }

        fun e(value: String?, e: Throwable?) {
            if (BuildConfig.BUILD_TYPE.equals("debug", ignoreCase = true)) {
                Log.i(TAG, value, e)
            }
        }

        fun e(value: String?) {
            if (BuildConfig.BUILD_TYPE.equals("debug", ignoreCase = true)) {
                Log.e(TAG, value!!)
            }
        }
    }
}