package com.example.employeedatabaseexample.domain

import com.example.employeedatabaseexample.data.repositiory.EmployeeRepository
import android.content.Context
import com.example.employeedatabaseexample.data.model.EmployeeUIModel
import kotlinx.coroutines.flow.Flow

class EmployeeDomain(private val employeeRepository: EmployeeRepository = EmployeeRepository() ) {

suspend fun insertEmployee (context: Context, employee: EmployeeUIModel) {
    employeeRepository.insertEmployee(context, employee)
}

suspend fun deleteEmployee (context: Context, employee: EmployeeUIModel) {
    employeeRepository.deleteEmployee(context, employee)
}

suspend fun updateEmployee (context: Context,  empId: Int, empType: String) {
    employeeRepository.updateEmployee(context, empId, empType)
}

suspend fun getAllEmployee (context: Context): Flow<List<EmployeeUIModel>> {
    return employeeRepository.getEmployee(context)
}
}