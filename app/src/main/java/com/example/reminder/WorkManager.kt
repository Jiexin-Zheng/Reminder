package com.example.reminder

//import com.mredrock.cyxbs.common.utils.extensions.defaultSharedPreferences
//import com.mredrock.cyxbs.common.utils.extensions.editor
//import com.mredrock.cyxbs.mine.util.NotificationUtil
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*
var id_tmp: Long = 0
val CHANNEL_ID = "REMINDER_1"
//val context_tmp = CreateReminderActivity

class NotificationWorker(c: Context, workerParams: WorkerParameters)
    : Worker(c, workerParams) {

//    private val context_tmp = c
//    private val id_tmp = id
    override fun doWork(): Result {
        //读取是否通知过
//        val isPush = applicationContext.defaultSharedPreferences.getBoolean(FLAG, false)
//        SQLiteHelperOfReminder.init(CreateReminderActivity())
        println("id_tmp in workmanager")
        println(id_tmp)
        val ret = SQLiteHelperOfReminder.getRemindDataByID(id_tmp)
        val notPush = ret.reminder_seen
        val date: Date = Date()
        date.time = ret.reminder_time
        val calendar:Calendar = Calendar.getInstance()
        calendar.time = date

        println("notPush: ")
        println(notPush)

        if (compareCurrentHour(calendar[Calendar.HOUR_OF_DAY])) {
            if (notPush < 2) {
                //如果在指定时间段，并且没有推送过通知

                    //写入已通知

//                    println("notification!!")
                  //  ret.reminder_seen = ret.reminder_seen + 1
                    SQLiteHelperOfReminder.updateRemindData(id_tmp,ret)
 //               }
                //继续后面的推送通知代码
            } else {
                //在指定时间段，已推送过了，则不再推送
                return Result.retry()
            }
        } else {
            //不在时间段，重置标志位false

            return Result.retry()
        }

        id_tmp = 0
        val resultIntent = Intent(applicationContext, CreateReminderActivity::class.java)
        val intent = PendingIntent.getActivity(applicationContext, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        //推送通知


        return Result.success()
    }

    private fun compareCurrentHour(targetHour: Int): Boolean {
        val current = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return current == targetHour
    }
}

/*
fun showNotification(context: Context, pendingIntent: PendingIntent)
{
    var manager=context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//    var mUri= Settings.System.DEFAULT_NOTIFICATION_URI
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)//如果系统是8.0以上的就需要用到NotificationChannel
    {
        var mChannel= NotificationChannel("1", "hh", NotificationManager.IMPORTANCE_LOW)
        mChannel.setDescription("description of this notification");
//        mChannel.setSound(mUri, Notification.AUDIO_ATTRIBUTES_DEFAULT);
//        mChannel.enableVibration(true)
 //       mChannel.enableLights(true)
//        mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        manager.createNotificationChannel(mChannel);
    }

    // CHANNEL_ID：通道ID，可在类 MainActivity 外自定义。如：val CHANNEL_ID = 'msg_1'
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setDefaults(Notification.DEFAULT_ALL)
        .setSmallIcon(R.drawable.icon2)
        .setContentTitle("Reminder")
        .setContentText("6666666")
        // 通知优先级，可以设置为int型，范围-2至2
 //       .setPriority(NotificationCompat.PRIORITY_MAX )
            .setContentIntent(pendingIntent)
    // 显示通知
    with(NotificationManagerCompat.from(context)) {
        notify(1, builder.build())
    }
}

*/