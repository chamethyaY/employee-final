package com.example.employees.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//tells Spring/JPA: "This class is a database table".
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data



//here method name is the default table nam in  the mysql.
public class employee {
      @Id
      //by the generated value it is  auto increament the ID.
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empID;
    private String empName;
    private String empAddress;
    private String empNumber;

}
