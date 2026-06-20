package ar.edu.unahur.obj2.banco;

import ar.edu.unahur.obj2.banco.observadores.IObservador;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private final Integer numero;
    private Double saldo;
    private List<IObservador> observadores = new ArrayList<>();

    public Cuenta(Integer numero, Double saldo){
        this.numero = numero;
        this.saldo = saldo;
    }

    public Integer getNumero(){
        return numero;
    }

    public Double getSaldo(){
        return saldo;
    }

    public void depositar(Double monto){
        this.saldo += monto;
        notificar("DEPOSITO", monto);
    }

    public void retirar(Double monto){
        this.saldo -= monto;
        notificar("RETIRO", monto);
    }

    public void agregarObservador(IObservador observador){
        observadores.add(observador);
    }

    public void quitarObservador(IObservador observador){
        observadores.remove(observador);
    }

    public void notificar(String operacion, Double monto){
        observadores.forEach(o -> o.notificar(this, operacion, monto));
    }



}
