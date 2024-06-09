package com.example.employeedatabaseexample.data.Offlinedatasource

import android.content.Context
import kotlinx.coroutines.flow.Flow

import com.example.employeedatabaseexample.data.database.EmployeeDataBase
import com.example.employeedatabaseexample.data.database.model.EmployeeDBModel

class OfflineDataSource () {
    
    suspend fun insertEmployee(context: Context, employee: EmployeeDBModel ) {
        EmployeeDataBase.getDataBase(context).getEmployeeDao().insert(employee)
    }

    suspend fun deleteEmployee(context: Context, employee: EmployeeDBModel ) {
        EmployeeDataBase.getDataBase(context).getEmployeeDao().delete(employee)
    }
    
    suspend fun updateEmployee (context: Context, empId: Int, empType: String) {
        EmployeeDataBase.getDataBase(context).getEmployeeDao().update(empId, empType)
    }

    suspend fun getAllEmployee(context: Context): Flow<List<EmployeeDBModel>>{
        return   EmployeeDataBase.getDataBase(context).getEmployeeDao().getEmployees()
    }
}