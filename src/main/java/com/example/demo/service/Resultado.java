/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.service;

public class Resultado {
    private Object valor;
    private boolean erro;
    private String mensagemErro;

    public Resultado(Object valor, boolean erro, String mensagemErro) {
        this.valor = valor;
        this.erro = erro;
        this.mensagemErro = mensagemErro;
    }

    public static Resultado sucesso (Object valor){
        return new Resultado(valor, false, "");
    }

    public static Resultado erro (String mensagemErro){
        return new Resultado(null, true, mensagemErro);
    }

    public Object getValor(){
        return valor;
    }

    public boolean getErro(){
        return erro;
    }

    public String getMensagemErro(){
        return mensagemErro;
    }
}
