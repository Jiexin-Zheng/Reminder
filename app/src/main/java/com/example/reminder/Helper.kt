package com.example.reminder

import android.os.Build
import android.os.Handler
import android.os.HandlerThread

class HelperOfReminder {

    private val HandlerThreadByCyrus = HandlerThread("remindHelper")
    private val HandlerByCyrus: Handler
    private var RunByCyrus: Boolean = false
    private var FinishByCyrus: Boolean = false
    private var ListenerByCyrus: ((remindData: SQLiteHelperOfReminder.DataOfReminder) -> Unit)? = null

    init {
        HandlerThreadByCyrus.start()
        HandlerByCyrus = Handler(HandlerThreadByCyrus.looper)
    }

    private val RunnableByCyrus = object : Runnable {
        override fun run() {
            if (RunByCyrus && !FinishByCyrus) {
                loopToFindRemind()
                HandlerByCyrus.postDelayed({
                    this.run()
                }, 500)
            }
        }
    }

    fun setOnThreadRemindListener(listener: ((remindData: SQLiteHelperOfReminder.DataOfReminder) -> Unit)?) {
        ListenerByCyrus = listener
    }

    private fun loopToFindRemind() {
        val list = SQLiteHelperOfReminder.getAllList()
        for (remindData in list) {
            if (remindData.reminder_seen == 0 && remindData.reminder_time <= System.currentTimeMillis()) {
                ListenerByCyrus?.run {
                    this(remindData)
                    remindData.reminder_seen = 1
                    SQLiteHelperOfReminder.updateRemindData(remindData.id, remindData)
                }
                break
            }
        }
    }

    fun onPause() {
        RunByCyrus = false
        HandlerByCyrus.removeCallbacks(RunnableByCyrus)
    }

    fun onResume() {
        RunByCyrus = true
        HandlerByCyrus.removeCallbacks(RunnableByCyrus)
        HandlerByCyrus.post(RunnableByCyrus)
    }

    fun quit() {
        FinishByCyrus = true
        HandlerByCyrus.removeCallbacks(RunnableByCyrus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            HandlerThreadByCyrus.quitSafely()
        } else {
            HandlerThreadByCyrus.quit()
        }
    }

}