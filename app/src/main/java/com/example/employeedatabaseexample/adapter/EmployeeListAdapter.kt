package com.example.employeedatabaseexample.adapter

import androidx.recyclerview.widget.ListAdapter
import com.example.employeedatabaseexample.data.model.EmployeeUIModel
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedatabaseexample.databinding.EmployeeItemViewBinding
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater

class EmployeeListAdapter(private val onDeleteEmployee: (EmployeeUIModel) -> Unit):ListAdapter<EmployeeUIModel,  EmployeeListAdapter.EmployeeViewHolder>(DIFFUTILS){

    companion object {

         val DIFFUTILS = object : DiffUtil.ItemCallback<EmployeeUIModel>() {
 
            override fun areItemsTheSame (oldModel:EmployeeUIModel, newModel:EmployeeUIModel ):Boolean {
                return oldModel.empId == newModel.empId
            }
             override fun areContentsTheSame(
                 oldItem: EmployeeUIModel,
                 newItem: EmployeeUIModel
             ): Boolean {
                 return oldItem == newItem
             }

         }
    }

    class EmployeeViewHolder (private val binding :EmployeeItemViewBinding, private val onDeleteEmployee: (employee: EmployeeUIModel)->Unit): RecyclerView.ViewHolder(binding.root) {

        fun onBind(employee:EmployeeUIModel){
            binding.empId.text = employee.empId.toString()
            binding.empName.text = buildString {
                append(employee.empFirstName)
                append(" ")
                append(employee.empLastName)
            }
            binding.empType.text = employee.empType
            binding.deleteEmployee.setOnClickListener {
                onDeleteEmployee(employee)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmployeeItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding, onDeleteEmployee)
     }

    override fun onBindViewHolder(viewHolder: EmployeeViewHolder, position: Int) {
         viewHolder.onBind(getItem(position))
     }

}
