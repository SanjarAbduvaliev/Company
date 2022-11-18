package com.example.company.controller;

import com.example.company.entity.Department;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.DepartmentDto;
import com.example.company.service.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DeparmentService deparmentService;
    @PostMapping
    public HttpEntity<ApiResponce> add(@Valid@RequestBody DepartmentDto departmentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(deparmentService.add(departmentDto));
    }
    @GetMapping
    public HttpEntity<List<Department>> getAll(){
        return ResponseEntity.ok(deparmentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getId(@PathVariable Integer id){
        return ResponseEntity.ok(deparmentService.getId(id));
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<ApiResponce> edit(@Valid @RequestBody DepartmentDto departmentDto,@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(deparmentService.edit(departmentDto,id));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponce> delete(@PathVariable Integer id){
        return ResponseEntity.ok(deparmentService.delete(id));
    }
}
