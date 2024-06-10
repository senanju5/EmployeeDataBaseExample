package com.example.employeedatabaseexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedatabaseexample.data.model.EmployeeUIModel
import android.app.Dialog
import androidx.fragment.app.viewModels


import com.example.employeedatabaseexample.databinding.FragmentEmployeeListBinding
import com.example.employeedatabaseexample.adapter.EmployeeListAdapter
import com.example.employeedatabaseexample.databinding.DialogLayoutBinding
import com.example.employeedatabaseexample.presentation.Viewmodel.MainViewModel

class EmployeeListFragment : Fragment() {

    private  lateinit var binding: FragmentEmployeeListBinding
    private lateinit var adapter: EmployeeListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        
        binding.floatButton.setOnClickListener {
          openDialog()
        }
        
        viewModel.getAllEmployee(requireContext())
        viewModel.employees.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        setUpRecyclerView()
       return binding.root
    }

    private fun setUpRecyclerView(){
       
        binding.employeeList.layoutManager = LinearLayoutManager(requireContext())
        adapter = EmployeeListAdapter{ employee: EmployeeUIModel ->
          viewModel.deleteEmployee(requireContext(), employee)
        }
        binding.employeeList.adapter = adapter
  }

  private fun openDialog(){

    val binding = DialogLayoutBinding.inflate(LayoutInflater.from(requireContext()), view as ViewGroup, false)
    
    val dialog  = Dialog(requireContext())
   // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    //dialog.setCancelable(true)
    dialog.setContentView(binding.root)
    dialog.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    );
    
    binding.addButton.setOnClickListener {
        val empFirstName = binding.empFirstname.text.toString()
        val empLastName = binding.empLastname.text.toString()
        val empDOB = binding.empDob.text.toString()
        val empType  = binding.empType.text.toString()
        viewModel.insertEmployee(requireContext(), EmployeeUIModel(null, empFirstName, empLastName, empDOB, empType))
    }
    dialog.show()

  }

}