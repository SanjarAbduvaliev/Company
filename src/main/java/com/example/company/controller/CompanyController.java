package com.example.company.controller;

import com.example.company.entity.Company;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.CompanyDto;
import com.example.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @PostMapping("/add")
    public HttpEntity<ApiResponce> add(@Valid @RequestBody CompanyDto companyDto){
        ApiResponce apiResponce = companyService.addCompany(companyDto);
        return ResponseEntity.ok(apiResponce);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }
    @GetMapping("/getId/{id}")
    public ResponseEntity<Company> getId(@PathVariable Integer id){
        return ResponseEntity.ok(companyService.getId(id));
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<ApiResponce> edit(@Valid @RequestBody CompanyDto companyDto, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(companyService.editCompany(companyDto,id));
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<ApiResponce> delete(@PathVariable Integer id){
        return  ResponseEntity.ok(companyService.deleteCompany(id));
    }
}
