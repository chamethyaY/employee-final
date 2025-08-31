package com.example.employees.Service;
import com.example.employees.DTO.employeeDTO;
import com.example.employees.Entity.employee;
import com.example.employees.util.Varlist;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employees.Repo.employeeRepo;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class employeeService {
   //when you are creating a variable with another class datatype you have to import that.before using.
    @Autowired
    private employeeRepo employeeRepo;


    @Autowired
    //variable declaration
    //private datatype variable name.
    //variable name should not be equal to the method name in the   EmployeeApplication.java

    private ModelMapper modelMapper;


    public String saveEmployee(employeeDTO employeeDTO) {
        //exitsById is a CRUD operation that provided by the jpa Repositery.so we cannot change the name existById .and i know you will raise  a question we did not write any query for the exitsById.actually we do not have to write the query.jpa repository layer automatically  has  that query

        if (employeeRepo.existsById(employeeDTO.getEmpID())) {
            return Varlist.RSP_DUPLICATE;
        } else {
            //before saving converting the dto object to the entity.
            employeeRepo.save(modelMapper.map(employeeDTO, employee.class));
            return Varlist.RSP_SUCCESS;
        }

    }
    public String updateEmployee(employeeDTO employeeDTO){

        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO, employee.class));
            return Varlist.RSP_SUCCESS;

        }
        else{
           return Varlist.RSP_NO_DATA_FOUND;

        }      }


        public List<employeeDTO> getAllEmployee() {
            List<employee> employeeList = employeeRepo.findAll();

            Type listType = new TypeToken<List<employeeDTO>>() {}.getType();

            return modelMapper.map(employeeList, listType);
        }

        public employeeDTO searchEmployee(int empID) {
            if(employeeRepo.existsById(empID)){
                employee employee=employeeRepo.findById(empID).orElse(null);
                return modelMapper.map(employee,employeeDTO.class);

            }
            else{
                return null;

            }
        }
        public String deleteEmployee(int empID) {
            if (employeeRepo.existsById(empID)) {
                employeeRepo.deleteById(empID);
                return Varlist.RSP_SUCCESS;

            } else {
                return Varlist.RSP_NO_DATA_FOUND;
            }
        }



    }

