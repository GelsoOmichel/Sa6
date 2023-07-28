package com.example.demo.controller;

import com.example.demo.model.ContaCorrentePF;
import org.springframework.data.repository.CrudRepository;

public interface BancoRepository extends CrudRepository<ContaCorrentePF, Long> {
}
