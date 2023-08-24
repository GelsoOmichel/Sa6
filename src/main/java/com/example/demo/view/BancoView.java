package com.example.demo.view;

import com.example.demo.controller.BancoController;
import com.example.demo.model.AccountType;
import com.example.demo.model.ContaCorrentePF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class BancoView {

    @Autowired
    private BancoController bancoController;


    @PostMapping("/criarconta")
    public ContaCorrentePF criarConta(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("type") String type) throws Exception {
        return bancoController.criarConta(id, name, type);
    }

    @GetMapping("/type")
    public String listAccountType(){
        String text = AccountType.CONTA_CORRENTE + ", " + AccountType.CONTA_POUPANCA;
        return text;
    }

    @GetMapping("/consultaconta")
    public ContaCorrentePF consultaConta(@PathParam("name") String name){
        return bancoController.consultaConta(name);
    }

    @PutMapping("/transferir")
    public void transferir(@PathParam("contaOrigem") Long contaOrigem, @PathParam("contaDestino") Long contaDestino, @PathParam("valor") Double valor){
        bancoController.transferir(contaOrigem, contaDestino, valor);
    }

    @PutMapping("/depositar")
    public void depositar(@PathParam("quantidade") Double quantidade, @PathParam("contaDestino") Long contaDestino){
        bancoController.depositar(quantidade, contaDestino);
    }

    @PutMapping("/sacar")
    public void sacar(@PathParam("quantidade") Double quantidade, @PathParam("contaOrigem") Long contaOrigem){
        bancoController.sacar(quantidade, contaOrigem);
    }

}