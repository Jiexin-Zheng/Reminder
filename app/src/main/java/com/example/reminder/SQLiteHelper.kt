package com.example.reminder

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

object SQLiteHelperOfReminder {

    lateinit var SQLiteByCyrus: SQLiteOfReminder
    var UserId: String? = null

    data class DataOfReminder(var reminder_message: String, var reminder_time: Long, var reminder_seen: Int = 0,
                              var creation_time: Long = 0, var location_x: Long = 0, var location_y: Long = 0, var icon_id: Long = 1) {

        var id: Long = 0

        constructor(id: Long, reminder_message: String, reminder_time: Long, reminder_seen: Int,
                    creation_time: Long, location_x: Long, location_y: Long, icon_id: Long
        ) : this(
                reminder_message,
                reminder_time,
                reminder_seen,
                creation_time,
                location_x,
                location_y,
                icon_id
        ) {
            this.id = id
        }

    }

//why use ".let" and "!!"  ？
    @JvmStatic
    @Synchronized
    fun init(context: Context) {
//        val userId = username_g?.let {
 //           SPDatabase.getString(it)
 //       }

 //       val userId = username_g!!
    val settings = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val userId = settings.getString( "USER_TEMP", "not found")
    println("USER_TEMP")
    println(userId)
        if (UserId == null || !UserId.equals(userId)) {
            SQLiteByCyrus = SQLiteOfReminder(context, userId!!)
        }
    }

    @Synchronized
    fun addRemindData(remindData: SQLiteHelperOfReminder.DataOfReminder) {
        val db = SQLiteByCyrus.writableDatabase
        val insert =
                "insert into ${SQLiteByCyrus.getTableName()} (message,timeR,timeC,icon,locationX,locationY,seen) values ('${remindData.reminder_message}',${remindData.reminder_time},${remindData.creation_time},${remindData.icon_id},${remindData.location_x},${remindData.location_y},${remindData.reminder_seen})"
        db.execSQL(insert)
        db.close()
    }

    @Synchronized
    fun deleteRemindData(id: Long) {
        val db = SQLiteByCyrus.writableDatabase
        val delete = "delete from ${SQLiteByCyrus.getTableName()} where id = $id"
        db.execSQL(delete)
        db.close()
    }

    @Synchronized
    fun updateRemindData(id: Long, remindData: SQLiteHelperOfReminder.DataOfReminder) {
        val db = SQLiteByCyrus.writableDatabase
        val sql =
                "update ${SQLiteByCyrus.getTableName()} set message = '${remindData.reminder_message}',timeR = ${remindData.reminder_time},locationX = ${remindData.location_x},locationY = ${remindData.location_y},icon = ${remindData.icon_id},timeC = ${remindData.creation_time},seen = ${remindData.reminder_seen} where id = $id"
        db.execSQL(sql)
        db.close()
    }

    @Synchronized
    fun updateIcon(id: Long, iconId: Int) {
        val db = SQLiteByCyrus.writableDatabase
        val sql =
            "update ${SQLiteByCyrus.getTableName()} set icon = ${iconId} where id = $id"
        db.execSQL(sql)
        db.close()
    }

    @Synchronized
    fun getAllList(): List<SQLiteHelperOfReminder.DataOfReminder> {
        val list = mutableListOf<SQLiteHelperOfReminder.DataOfReminder>()
        val db = SQLiteByCyrus.readableDatabase
        val sql = "select * from " + SQLiteByCyrus.getTableName() + " order by id desc"
        val cursor: Cursor = db.rawQuery(sql, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val message = cursor.getString(cursor.getColumnIndex("message"))
            val timeR = cursor.getLong(cursor.getColumnIndex("timeR"))
            val seen = cursor.getInt(cursor.getColumnIndex("seen"))
            val icon = cursor.getLong(cursor.getColumnIndex("icon"))
            val timeC = cursor.getLong(cursor.getColumnIndex("timeC"))
            val locationX = cursor.getLong(cursor.getColumnIndex("locationX"))
            val locationY = cursor.getLong(cursor.getColumnIndex("locationY"))
            list.add(SQLiteHelperOfReminder.DataOfReminder(id, message, timeR, seen, timeC, locationX, locationY, icon))
        }
        cursor.close()
        db.close()
        return list
    }

    class SQLiteOfReminder(
            context: Context?,
            userId: String
    ) : SQLiteOpenHelper(context, "SQ", null, 2) {

        private var TABLE_NAME = "Remind_$userId"
        private var CREATE_STATEMENT =
                "create table if not exists $TABLE_NAME (id INTEGER primary key autoincrement, message TEXT,timeR INTEGER,seen NUMERIC,timeC INTEGER,locationX INTEGER,locationY INTEGER,icon INTEGER)"
        private val DELETE_STATEMENT = "drop table if exists $TABLE_NAME"

        fun getTableName(): String {
            return TABLE_NAME
        }

        override fun onOpen(db: SQLiteDatabase?) {
            super.onOpen(db)
            db?.execSQL(CREATE_STATEMENT)
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_STATEMENT)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(DELETE_STATEMENT)
            db?.execSQL(CREATE_STATEMENT)
        }

    }


}

