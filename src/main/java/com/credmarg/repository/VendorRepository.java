package com.credmarg.repository;

import com.credmarg.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    boolean existsByEmail(String email);
}
