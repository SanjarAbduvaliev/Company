package com.example.company.repository;

import com.example.company.entity.Address;
import com.example.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameNot(String corpName);
    Address findByAddress_Id(Integer address_id);
}
