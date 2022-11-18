package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.payload.AddressDto;
import com.example.company.payload.ApiResponce;
import com.example.company.repository.AddressRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class AddressService {
   @Autowired
    AddressRepository addressRepository;

    public ApiResponce addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponce("Yangi address qo'shildi",true);
    }
    public List<Address> getAll(){
        return addressRepository.findAll();
    }
    public Address getId(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }
    public ApiResponce editAddress(AddressDto addressDto,Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new ApiResponce("Address mavjud emas",false);
        }
        Address address = optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponce("Adress o'zgartirildi",true);
    }
    public ApiResponce delete(Integer id){
        addressRepository.deleteById(id);
        return new ApiResponce("Addres o'chirildi",true);
    }
}
