package com.example.company.repository;

import com.example.company.entity.Address;
import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Company findByCompany_Id(Integer company_id);

}
