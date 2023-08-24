package com.example.demo.controller;

import com.example.demo.model.AccountType;
import com.example.demo.model.ContaCorrentePF;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoController implements ContaCorrente {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private Controller controller;

    private Long number = 0L;

    public ContaCorrentePF criarConta(Integer id, String name, String accountType) throws Exception {
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF();
        StringBuilder message = new StringBuilder();
        if(accountType == null){
            message.append("\nNecessário informar o tipo da conta!");
        }
        switch (accountType){
            case "POUPANCA" :
                contaCorrentePF.setAccountType(AccountType.CONTA_POUPANCA);
                break;
            case "CORRENTE" :
                contaCorrentePF.setAccountType(AccountType.CONTA_CORRENTE);
            default:
                message.append("\nTipo da conta não é suportado!");
        }
        Person person = controller.findPerson(id, name);
        if(person != null && contaCorrentePF.getError() == null){
            number++;
            contaCorrentePF.setNumeroConta(number);
            contaCorrentePF.setPerson(person);
            contaCorrentePF.setSaldo(0.0);
            bancoRepository.save(contaCorrentePF);
        }else if(contaCorrentePF.getError() == null){
            message.append("\nPessoa ");
            message.append(name).append(" informada não foi cadastrada");
        }
        if(!message.isEmpty()){
            contaCorrentePF.setError(message.toString());
        }

        return contaCorrentePF;
    }

    public ContaCorrentePF consultaConta(String name){

        List<ContaCorrentePF> contas = (List<ContaCorrentePF>) bancoRepository.findAll();

        for(ContaCorrentePF cc : contas){
            if(cc.getPerson() != null && cc.getPerson().getName().equals(name)){
                return cc;
            }
        }
        return null;
    }


    @Override
    public void depositar(Double quantidade, Long contaDestino) {
        ContaCorrentePF conta = bancoRepository.findById(contaDestino).get();
        Double total = conta.getSaldo() + quantidade ;
        conta.setSaldo(total);
        bancoRepository.save(conta);
    }

    @Override
    public Double consultaSaldo(ContaCorrentePF conta) {
        return conta.getSaldo();
    }


    public void sacar(Double quantidade, Long contaOrigem) {
        ContaCorrentePF conta = bancoRepository.findById(contaOrigem).get();
        Double total = conta.getSaldo() - quantidade ;
        conta.setSaldo(total);
        bancoRepository.save(conta);
    }

    @Override
    public String transferir(Long contaOrigem, Long contaDestino, Double valor) {
        String message = "";
        ContaCorrentePF destino = bancoRepository.findById(contaDestino).get();
        ContaCorrentePF origem = bancoRepository.findById(contaOrigem).get();

        if(origem.getSaldo() >= valor){
            destino.setSaldo(destino.getSaldo() + valor);
            origem.setSaldo(origem.getSaldo() - valor);
            bancoRepository.save(destino);
            bancoRepository.save(origem);
            message = "A conta do(a) " + destino.getPerson().getName() + " recebeu a transferência no valor de R$ " + valor;
        }else{
            message = message + " Saldo insuficiente para a operação";
        }
        return message;
    }
}