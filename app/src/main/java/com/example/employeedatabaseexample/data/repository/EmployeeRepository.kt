package com.example.employeedatabaseexample.data.repositiory

import android.content.Context
import com.example.employeedatabaseexample.data.Offlinedatasource.OfflineDataSource
import com.example.employeedatabaseexample.data.model.EmployeeUIModel
import com.example.employeedatabaseexample.data.database.model.EmployeeDBModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmployeeRepository (private val offlineDataSource: OfflineDataSource = OfflineDataSource()) {
   
    suspend fun insertEmployee (context: Context, employee: EmployeeUIModel) {
      val employee : EmployeeDBModel=  EmployeeDBModel(null, employee.empFirstName, employee.empLastName, employee.empDOB, employee.empType)
      offlineDataSource.insertEmployee(context, employee)
    }

    suspend fun deleteEmployee (context: Context, employee: EmployeeUIModel) {
        val employee : EmployeeDBModel=  EmployeeDBModel(employee.empId, employee.empFirstName, employee.empLastName, employee.empDOB, employee.empType)
        offlineDataSource.deleteEmployee(context, employee)
    }

    suspend fun updateEmployee (context: Context, empId: Int, empType: String) {
        offlineDataSource.updateEmployee(context, empId, empType)
    }

    suspend fun getEmployee (context: Context): Flow<List<EmployeeUIModel>> =  offlineDataSource.getAllEmployee(context).map{employees ->
        employees.map {EmployeeUIModel(it.empId, it.empFirstName, it.empLastName, it.empDob, it.empType)}
    }
    
}