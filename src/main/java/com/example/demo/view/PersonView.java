package com.example.demo.view;

import com.example.demo.controller.Controller;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonView {

    @Autowired
    Controller controller;
    @GetMapping("/person")
    public Person findPerson(@PathParam("name") String name){
        return controller.findPerson(name);
    }

    @DeleteMapping("/person")
    public void deletePerson(@PathParam("name") String name){
        controller.removePerson(name);
    }

    @PostMapping("/person")
    public Person addPerson(@PathParam("name") String name, @PathParam("sexo") String sexo){
        return controller.addPerson(name, sexo);
    }

    @PutMapping("/person")
    public Person editPerson(@PathParam("name") String name, @PathParam("sexo") String sexo){
        return controller.editPerson(name, sexo);
    }

    @GetMapping("/home")
    public String helloWorld(){
        return "Hello world";
    }
}