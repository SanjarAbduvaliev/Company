package com.example.company.service;

import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.DepartmentDto;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeparmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponce add(DepartmentDto departmentDto){
        Department department=new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponce("This Company not found!",false);
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponce("Successfull saved", true);

    }
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
    public Department getId(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }
    public ApiResponce edit(DepartmentDto departmentDto, Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponce("Not found",false);
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(departmentRepository.findByCompany_Id(departmentDto.getCompanyId()));
        departmentRepository.save(department);
        return new ApiResponce("Department edited",true);
    }
    public ApiResponce delete(Integer id){
        departmentRepository.deleteById(id);
        return new ApiResponce("Deleted",true);
    }
}
