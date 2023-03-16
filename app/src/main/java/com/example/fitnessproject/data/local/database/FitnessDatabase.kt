package com.example.fitnessproject.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnessproject.data.local.dao.TopicDao
import com.example.fitnessproject.data.local.dao.UserDao
import com.example.fitnessproject.data.local.dao.VersionDao
import com.example.fitnessproject.data.local.database.FitnessDatabase.Companion.DATABASE_VERSION
import com.example.fitnessproject.data.local.entity.*
import kotlinx.coroutines.CoroutineScope


@Database(
    entities = [User::class,
        DatabaseVersion::class,
        Topic::class,
        TopicDetail::class,
        TopicDetailSelected::class,
        TopicSelected::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun versionDao(): VersionDao
    abstract fun topicDao(): TopicDao

    companion object {
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: FitnessDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FitnessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FitnessDatabase::class.java,
                    "fitness_database"
                ).addCallback(WordDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
//    private val callback = object: RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            db.insert()
//        }
//    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
//            context.resources.assets.open()
            INSTANCE?.let { database ->
                //pre-populate topic
                for (i in 0..5000) {
                    db.execSQL(
                        "INSERT INTO user (user_name, password, gender, height, weight) VALUES ('name test', '123', 1, 10.3, 10.3)," +
                                "('name test', '123', 1, 10.3, 10.3)," +
                                "('name test', '123', 1, 10.3, 10.3);"
                    )
                }

                //final insert version database
                db.execSQL("INSERT INTO database_version (version) VALUES (${DATABASE_VERSION})")

                //test to pre-populate database
//                scope.launch {
//                    val userDao = database.userDao()
//                    val user2 = User(
//                        name = "name",
//                        password = "pass",
//                        gender = Gender.MALE.gender,
//                        height = 10.1,
//                        weight = 120.1
//                    )
//                    val list = mutableListOf<User>()
//                    for (i in 0..50) {
//                        list.add(user2)
//                    }
//                    userDao.insertUserList(list)
//                }
            }
        }
    }
}