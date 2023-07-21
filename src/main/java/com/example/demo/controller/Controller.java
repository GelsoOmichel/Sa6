package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Controller {


    private List<Person> persons = new ArrayList<>();
    private int id = 0;

    public Person findPerson(String name) {
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public Person addPerson(String name, String sexo) {
        Person person = new Person();
        person.setName(name);
        person.setSexo(sexo);
        id++;
        person.setId(id);
        persons.add(person);
        return person;
    }

    public void removePerson(String name) {
        Person person = new Person();
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                person = p;
            }
        }
        persons.remove(person);
    }

    public Person editPerson(String name, String sexo) {
        Person p = new Person();
        for (Person person : persons) {
            String nome = person.getName();
            if (nome.equals(name)) {
                person.setSexo(sexo);
                p = person;
            }
        }
        return p;
    }
}


