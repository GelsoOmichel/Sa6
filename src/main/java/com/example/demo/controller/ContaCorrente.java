package com.example.demo.controller;

import com.example.demo.model.Conta;
import com.example.demo.model.ContaCorrentePF;

public interface ContaCorrente {

    Double sacar(Double quantidade, Conta conta);

    void depositar(Double quantidade, Conta conta);

    void transferir(Double quantidade, Conta conta);

    String transferir(Long contaOrigem, Long contaDestino, Double valor);

    Double consultaSaldo(ContaCorrentePF conta);

    void sacar(Double quantidade);

    void depositar(Double quantidade);

    void transferir(Double quantidade);

    void consultarsaldo(double quantidade);
}