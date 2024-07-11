package com.credmarg.services;

import com.credmarg.model.Employee;
import com.credmarg.model.Vendor;
import com.credmarg.repository.EmployeeRepository;
import com.credmarg.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public VendorService(VendorRepository vendorRepository, EmployeeRepository employeeRepository, EmailService emailService) {
        this.vendorRepository = vendorRepository;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }


    public ResponseEntity<Object> saveVendor(Vendor vendor) throws Exception {
        if (vendorRepository.existsByEmail(vendor.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists for an vendors");
        }
        if (employeeRepository.existsByEmail(vendor.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists for an employee");
        }
        Vendor savedVendors = vendorRepository.save(vendor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVendors);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public void sendEmailToVendor(Vendor vendor) {


        emailService.sendEmailToVendor(vendor);    }
}