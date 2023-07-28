package com.example.demo.view;

import com.example.demo.controller.BancoController;
import com.example.demo.model.ContaCorrentePF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class BancoView {

    @Autowired
    private BancoController bancoController;
    @GetMapping("/consultaSaldo")
    public Double consultaSaldo(){
        ContaCorrentePF conta = new ContaCorrentePF();
        conta.setSaldo(100D);
        return bancoController.consultaSaldo(conta);

    }

    @PostMapping("/criaconta")
    public ContaCorrentePF criarConta(@PathParam("name") String name) throws Exception {
        return bancoController.criarConta(name);
    }

    @GetMapping("/consultaconta")
    public ContaCorrentePF consultaConta(@PathParam("name") String name){
        return bancoController.consultaConta(name);
    }

}
