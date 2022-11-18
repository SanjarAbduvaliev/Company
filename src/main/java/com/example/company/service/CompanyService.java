package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.entity.Company;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.CompanyDto;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;
    public ApiResponce addCompany(CompanyDto companyDto){
        boolean existsByCorpNameNot = companyRepository.existsByCorpNameNot(companyDto.getCorpName());
        if (existsByCorpNameNot){
            return new ApiResponce("Bunday companiya nomi mavjud",false);
        }
        Company company=new Company();
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponce("Bunday address mavjud emas",false);
        company.setAddress(optionalAddress.get());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return  new ApiResponce("Companiya Ro'yhatga olindi",true);
    }
    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    public Company getId(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }
    public ApiResponce editCompany(CompanyDto companyDto,Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new ApiResponce("Bunday companiya mavjud emas",false);
        }
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        Address byAddress_id = companyRepository.findByAddress_Id(companyDto.getAddressId());
        company.setAddress(byAddress_id);
        return new ApiResponce("Muvaffaqqiyatli o'zgartirildi",true);
    }
    public ApiResponce deleteCompany(Integer id){
        companyRepository.deleteById(id);
        return  new ApiResponce("Ochirildi", true);
    }
}
