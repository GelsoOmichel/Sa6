package com.example.demo.controller;

import com.example.demo.model.ContaCorrentePF;

public interface ContaCorrente {

     void sacar(Double quantidade, Long contaOrigem);

     void depositar(Double quantidade, Long contaDestino);

    String transferir(Long contaOrigem, Long contaDestino, Double valor);

    Double consultaSaldo(ContaCorrentePF conta);

}