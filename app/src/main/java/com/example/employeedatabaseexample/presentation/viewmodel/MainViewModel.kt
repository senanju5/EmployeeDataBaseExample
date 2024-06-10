package com.example.employeedatabaseexample.presentation.Viewmodel
import androidx.lifecycle.ViewModel
import com.example.employeedatabaseexample.domain.EmployeeDomain
import com.example.employeedatabaseexample.data.model.EmployeeUIModel
import androidx.lifecycle.MutableLiveData
import android.content.Context
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val employeeDomain:EmployeeDomain = EmployeeDomain() ): ViewModel() {

 var employees = MutableLiveData<List<EmployeeUIModel>>()

   fun insertEmployee(context:Context, employee: EmployeeUIModel ) {
    viewModelScope.launch(Dispatchers.IO) {
        employeeDomain.insertEmployee(context, employee)
    }

   }

   fun deleteEmployee(context:Context, employee:EmployeeUIModel) {

    viewModelScope.launch(Dispatchers.IO) {
        employeeDomain.deleteEmployee(context, employee)
    }
   }

   fun updateEmployee (context: Context, empId:Int, empType:String) {
    viewModelScope.launch(Dispatchers.IO) {
        employeeDomain.updateEmployee(context, empId, empType)
    }
   }

   fun getAllEmployee (context: Context) {
    viewModelScope.launch {
        employeeDomain.getAllEmployee(context).collect{
            employees.value = it
        }
    }
   }

}