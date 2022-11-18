package com.example.company.controller;

import com.example.company.entity.Worker;
import com.example.company.payload.ApiResponce;
import com.example.company.payload.WorkerDto;
import com.example.company.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @PostMapping
    public HttpEntity<ApiResponce> add(@Valid @RequestBody WorkerDto workerDto){
        return ResponseEntity.ok(workerService.addworker(workerDto));
    }
    @GetMapping
    public ResponseEntity<List<Worker>> getAll(){
        return ResponseEntity.ok(workerService.getAllWorker());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getId(@PathVariable Integer id){
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(workerService.getWorkerId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce> edit(@Valid @RequestBody WorkerDto workerDto,@PathVariable Integer id){
        return ResponseEntity.ok(workerService.edit(workerDto,id));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponce> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(workerService.delete(id));
    }
    
}
