package com.credmarg.controller;

import com.credmarg.model.Vendor;
import com.credmarg.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity<Object> createVendor(@RequestBody Vendor vendor) throws Exception {
        return vendorService.saveVendor(vendor);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PostMapping("/send-email")
    public void sendEmailToVendors(@RequestBody List<Long> vendorIds) {
        List<Vendor> vendors = vendorService.getAllVendors().stream()
                .filter(vendor -> vendorIds.contains(vendor.getId()))
                .collect(Collectors.toList());
        vendors.forEach(vendorService::sendEmailToVendor);
    }
}
