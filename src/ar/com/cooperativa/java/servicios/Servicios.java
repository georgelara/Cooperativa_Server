package ar.com.cooperativa.java.servicios;

public class Servicios {
    public static void main(String[] args) {
        new Thread(new ServicioClienteSave()).start();
        new Thread(new ServicioClienteGetAll()).start();
        new Thread(new ServicioClienteRemove()).start();
        new Thread(new ServicioClienteUpdate()).start();

        new Thread(new ServicioCuentaSave()).start();
        new Thread(new ServicioCuentaGetAll()).start();
        new Thread(new ServicioCuentaRemove()).start();
        new Thread(new ServicioCuentaUpdate()).start();

    }
}
