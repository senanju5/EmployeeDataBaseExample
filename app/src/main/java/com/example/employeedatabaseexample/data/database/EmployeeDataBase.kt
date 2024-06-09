package com.example.employeedatabaseexample.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.employeedatabaseexample.data.database.model.EmployeeDBModel

@Database(entities = [EmployeeDBModel::class], version = 1)
abstract class EmployeeDataBase : RoomDatabase() {
    abstract fun getEmployeeDao(): EmployeeDAO

    companion object {
        @Volatile private var INSTANCE: EmployeeDataBase? = null

        fun getDataBase(context: Context): EmployeeDataBase? {

            return INSTANCE
                    ?: synchronized(this) {
                        INSTANCE = builDataBase(context)
                        INSTANCE
                    }
        }

        fun builDataBase(context: Context): EmployeeDataBase {
            return Room.databaseBuilder(
                            context.applicationContext,
                            EmployeeDataBase::class.java,
                            "employee_databse"
                    )
                    .build()
        }
    }
}
