package com.example.lab8;

import com.example.lab8.Entities.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.lab8.InterfaceRepository;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class InterfaceController {
    @Autowired
    private InterfaceRepository InterfaceRepository;

    @GetMapping
    public Iterable<Application> getApplications() {
        return InterfaceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Integer id) {
        return InterfaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applications not found"));
    }

    @PostMapping
    public Application addApplication(@RequestBody Application application) {
        return InterfaceRepository.save(application);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Integer id) {
        InterfaceRepository.deleteById(id);
    }
}
