package com.example.company.repository;

import com.example.company.entity.Address;
import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    boolean existsByPhoneNumberNotAndNameNot(String phoneNumber, String name);
    Department findByDepartment_Id(Integer department_id);
    Address findByAddress_Id(Integer address_id);
}
