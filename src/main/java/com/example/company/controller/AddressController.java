package com.example.company.controller;

import com.example.company.entity.Address;
import com.example.company.payload.AddressDto;
import com.example.company.payload.ApiResponce;
import com.example.company.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @PostMapping
    public HttpEntity<ApiResponce> addAddres(@Valid @RequestBody AddressDto addressDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDto));
    }
    @GetMapping("/getAddressId/{id}")
    public ResponseEntity<Address> getId(@PathVariable Integer id){
        return  ResponseEntity.status(HttpStatus.OK).body(addressService.getId(id));
    }
    @GetMapping("/getAddressAll")
    public ResponseEntity<List<Address>> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }

}
