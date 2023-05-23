package com.example.lab8;

import com.example.lab8.Entities.Application;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceRepository extends CrudRepository<Application, Integer>{
}
