package com.credmarg.services;

import com.credmarg.model.Employee;
import com.credmarg.repository.EmployeeRepository;
import com.credmarg.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final VendorRepository vendorRepository;


    public EmployeeService(EmployeeRepository employeeRepository, VendorRepository vendorRepository) {
        this.employeeRepository = employeeRepository;
        this.vendorRepository = vendorRepository;
    }

    public ResponseEntity<Object> saveEmployee(Employee employee) throws Exception {

        if (employeeRepository.existsByEmail(employee.getEmail().trim())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists for an employee");
        }
        if (vendorRepository.existsByEmail(employee.getEmail().trim())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists for an vendors");
        }
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
