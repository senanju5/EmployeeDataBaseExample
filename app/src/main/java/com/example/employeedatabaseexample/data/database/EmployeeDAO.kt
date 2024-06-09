package com.example.employeedatabaseexample.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.employeedatabaseexample.data.database.model.EmployeeDBModel

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: EmployeeDBModel)

    @Delete
    suspend fun delete(employee: EmployeeDBModel)

    @Query("UPDATE emp_table set empType = :empType where empId = :empId ")
    suspend fun update( empId: Int, empType: String)

    @Query("SELECT * FROM emp_table order by empId ASC ")
    fun getEmployees (): Flow<List<EmployeeDBModel>>
}