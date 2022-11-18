package com.example.company.service;

import com.example.company.entity.Address;
import com.example.company.entity.Department;
import com.example.company.entity.Worker;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.WorkerDto;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponce addworker(WorkerDto workerDto) {
        boolean existsByPhoneNumberNotAndNameNot = workerRepository.existsByPhoneNumberNotAndNameNot(workerDto.getPhoneNumber(), workerDto.getName());
        if (existsByPhoneNumberNotAndNameNot) {
            Worker worker = new Worker();
            worker.setName(workerDto.getName());
            worker.setPhoneNumber(workerDto.getPhoneNumber());
            Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
            Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDeparmentId());
            if (!optionalAddress.isPresent())
                return new ApiResponce("This address not found",false);
            if (!optionalDepartment.isPresent())
                return new ApiResponce("This Department not found",false);
            worker.setAddress(optionalAddress.get());
            worker.setDepartment(optionalDepartment.get());
            return new ApiResponce("Successfull added",true);
        }
        return new ApiResponce("Such a worker already exists",false);
    }
    public List<Worker> getAllWorker(){
        return workerRepository.findAll();
    }
    public Worker getWorkerId(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }
    public ApiResponce edit(WorkerDto workerDto,Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponce("Such a worker not found",false);
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

        worker.setDepartment(workerRepository.findByDepartment_Id(workerDto.getDeparmentId()));
        worker.setAddress(workerRepository.findByAddress_Id(workerDto.getAddressId()));
        workerRepository.save(worker);
        return new ApiResponce("Successfull edited",true);
    }
    public ApiResponce delete(Integer id){
        workerRepository.deleteById(id);
        return new ApiResponce("Worker deleted",true);
    }
}
