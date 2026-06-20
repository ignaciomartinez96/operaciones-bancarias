package ar.edu.unahur.obj2.banco;

import ar.edu.unahur.obj2.banco.comandos.DepositarCommand;
import ar.edu.unahur.obj2.banco.comandos.IComando;
import ar.edu.unahur.obj2.banco.comandos.Invoker;
import ar.edu.unahur.obj2.banco.comandos.RetirarCommand;
import ar.edu.unahur.obj2.banco.observadores.Auditoria;
import ar.edu.unahur.obj2.banco.observadores.NotificacionCliente;

public class Main {
    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta(1234, 10000.00);

        cuenta.agregarObservador(new Auditoria());
        cuenta.agregarObservador(new NotificacionCliente());

        // Ejecucion individual
       // System.out.println("Ejecutando comandos");
        IComando depo1 = new DepositarCommand(cuenta, 70000.00);
        IComando reti1 = new RetirarCommand(cuenta, 5000.00);
        IComando reti2 = new RetirarCommand(cuenta, 16000.00);
        IComando depo2 = new DepositarCommand(cuenta, 9000.00);

        //System.out.println("[SALDO] -> " + cuenta.getSaldo());

        Invoker yuna = new Invoker();

        yuna.ejecutar(depo1);
        //System.out.println("[SALDO] -> " + cuenta.getSaldo());

        yuna.ejecutar(reti1);


        //Ejecucion por lote

        IComando reti3 = new RetirarCommand(cuenta, 5000.00);
        IComando depo3 = new DepositarCommand(cuenta, 300000.00);
        IComando reti4 = new RetirarCommand(cuenta, 50000.00);

        yuna.cargarComando(reti3);
        yuna.cargarComando(depo3);
        yuna.cargarComando(reti4);

        yuna.ejecutarLote();
        //System.out.println("[SALDO] -> " + cuenta.getSaldo());

    }
}
