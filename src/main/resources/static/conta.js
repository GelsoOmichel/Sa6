const formConta = document.getElementById("formConta");
const formSacar = document.getElementById("formSacar");
const formDepositar = document.getElementById("formDepositar");
const formTransferir = document.getElementById("formTransferir");

document.addEventListener("DOMContentLoaded", function (){
    let comboPessoa = document.getElementsByClassName("pessoa");
    function preencherPessoa(){
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                for (let i = 0; i < comboPessoa.length; i++) {
                    let option = document.createElement("option");
                    option.value = null;
                    option.textContent = "";
                    let select = comboPessoa[i];
                    select.appendChild(option);
                    data.forEach(pessoa => {
                        let option = document.createElement("option");
                        option.value = pessoa.id;
                        option.textContent = pessoa.name;
                        select.appendChild(option);
                    })
                }
            })
    }

    preencherPessoa();
});

formConta?.addEventListener("submit", function (event) {
    const pessoa_select = formConta.querySelector("#pessoa")
    let name = "";
    pessoa_select
        .querySelectorAll("option")
        .forEach((option) => {
            if(option.value == pessoa_select.value) name = option.textContent;
        });
    const id = formConta.querySelector("#pessoa").value;
    const conta = formConta.querySelector("#tipo_conta").value;


    const url = "name="+name+"&id="+id+"&type="+conta;
    fetch("/criarconta?"+url, {
        method: "POST"
    }).then(response => response.json())
    .then(data => {
        console.log(data)


    })
})

formSacar?.addEventListener("submit", function (event) {
    const numeroConta = formSacar.querySelector("#numero_conta_sacar").value;
    const valor = formSacar.querySelector("#valor_sacar").value;

    const url = "quantidade="+valor+"&contaOrigem="+numeroConta;
    fetch("/sacar?"+url, {
        method: "PUT"
    }).then(response => response.json())
    .then(data => {
        console.log(data)
    })
})

formDepositar?.addEventListener("submit", function (event) {
    const numeroConta = formDepositar.querySelector("#numero_conta_depositar").value;
    const valor = formDepositar.querySelector("#valor_depositar").value;

    const url = "quantidade="+valor+"&contaDestino="+numeroConta;
    fetch("/depositar?"+url, {
        method: "PUT"
    }).then(response => response.json())
    .then(data => {
        console.log(data)
    })
})

formTransferir?.addEventListener("submit", function (event) {
    const numeroContaOrigem = formTransferir.querySelector("#numero_conta_origem_transferir").value;
    const numeroContaDestino = formTransferir.querySelector("#numero_conta_destino_transferir").value;
    const valor = formTransferir.querySelector("#valor_transferir").value;

    const url = "valor="+valor+"&contaOrigem="+numeroContaOrigem+"&contaDestino="+numeroContaDestino;
    fetch("/transferir?"+url, {
        method: "PUT"
    }).then(response => response.json())
    .then(data => {
        console.log(data)
    })
})