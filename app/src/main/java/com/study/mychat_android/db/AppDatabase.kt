package com.study.mychat_android.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.mychat_android.MainApplication

/*
 *
 * Author: jinguang
 * Create: 2019/11/13 20:28
 * Description:
 */

@Database(entities = [ChatMessage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chatMessageDao(): ChatMessageDao

    companion object{
        private var appDatabase: AppDatabase? = null
        private val sLock = Any()
        /**
         * 获取数据库实例
         */
        fun getDababaseInstance(): AppDatabase {
            synchronized(sLock){
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(
                        MainApplication.getAppContext(),
                        AppDatabase::class.java, "chat.db"
                    ).build()
                }

                return appDatabase as AppDatabase
            }
        }
    }

}