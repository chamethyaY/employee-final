package com.example.employees.controller;
import com.example.employees.DTO.ResponseDTO;
import com.example.employees.DTO.employeeDTO;
import com.example.employees.Service.employeeService;
import com.example.employees.util.Varlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//here you can see this code is bit different to to the demo code we done earlier.because here we  use  a VarList ,so here DTO object contain 3 types of data that is code,message,content.
//controller layer is the REST API that catch the frontend end http requests.
@RestController
@RequestMapping("api/v1/employee")
public class employeeController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private employeeService  employeeService;
    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping(value="/saveEmployee")
    //reponseEntity  It’s a generic class that represents an HTTP response (body + headers + status code).
    public ResponseEntity saveemployee(@RequestBody employeeDTO employeeDTO){

        try{
            String respo=employeeService.saveEmployee(employeeDTO);
            if(respo.equals("00")){
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("sucess");
                responseDTO.setContent(employeeDTO);
                //bellow part can be done by using the responseEntity.
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }
            else if(respo.equals("06")){
                responseDTO.setCode(Varlist.RSP_DUPLICATE);
                responseDTO.setMessage("Employee registered");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
            else{
                responseDTO.setCode(Varlist.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
        }
        catch(Exception e){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping(value="/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody employeeDTO employeeDTO){

        try{
            String respo=employeeService.updateEmployee(employeeDTO);
            if(respo.equals("00")){
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }
            else if(respo.equals("01")){
                responseDTO.setCode(Varlist.RSP_DUPLICATE);
                responseDTO.setMessage("not a  registered employee");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
            else{
                responseDTO.setCode(Varlist.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            }
        }
        catch(Exception e){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    //getAll
    @GetMapping(value="/getAllEmployee")
    public ResponseEntity getAllEmployees(){

        try{
            List<employeeDTO> employeeDTOList=employeeService.getAllEmployee();
            responseDTO.setCode(Varlist.RSP_SUCCESS);
            responseDTO.setMessage("sucess");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);


        }
        catch(Exception e){
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID) {
        try {
            employeeDTO employeeDTO = employeeService.searchEmployee(empID);
            if (employeeDTO != null) {
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(Varlist.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID) {
        try {
            String res = employeeService.deleteEmployee(empID);
            if (res.equals("00")) {
                responseDTO.setCode(Varlist.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(Varlist.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setCode(Varlist.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(e);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
