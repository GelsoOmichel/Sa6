package com.example.demo.view;

import com.example.demo.controller.Controller;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PersonView {

    @Autowired
    Controller controller;
    @GetMapping("/person")
    public Person findPerson(@PathParam("id") Integer id, @PathParam("name") String name){
        return controller.findPerson(id, name);
    }


    @DeleteMapping("/person")
    public String deletePerson(@PathParam("id") Integer id, @PathParam("name") String name){
        controller.removePerson(id, name);

        return "Pessoa com o nome de " + name + " foi deletada";
    }

    @PostMapping("/person")
    public Person addPerson(@PathParam("name") String name, @PathParam("sexo") String sexo){
        return controller.addPerson(name, sexo);
    }

    @PutMapping("/person")
    public Person updatePerson(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("sexo") String sexo){
        return controller.editPerson(id, name, sexo);
    }

    @GetMapping("/all")
    public List<Person> listAll(){
        return controller.listAll();
    }

    @GetMapping("/home")
    public String helloWorld(){
        return "Hello world";
    }
}