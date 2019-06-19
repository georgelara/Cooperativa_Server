package ar.com.cooperativa.java.servicios;

import ar.com.cooperativa.java.entities.Cuenta;
import ar.com.cooperativa.java.repositories.jpa.CuentaR;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServicioCuentaRemove implements Runnable{
    private int port=8202;
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("COOPU");
    EntityManager em=emf.createEntityManager();
    CuentaR cue=new CuentaR(em);
    @Override
    public void run() {
        try (ServerSocket ss=new ServerSocket(port)) {
            System.out.println("Servicio Cuenta Remove: OK");
            while(true){
                System.out.println("Esperando conexión de cueente...");
                try (
                        Socket so=ss.accept();
                        ObjectInputStream in=new ObjectInputStream(so.getInputStream());
                        ObjectOutputStream out=new ObjectOutputStream(so.getOutputStream());
                ){
                    System.out.println("Servicio Cuenta remove, se conecto "+so.getInetAddress());
                    try {
                        Cuenta cueente=(Cuenta)in.readObject();
                        cue.remove(cueente);
                        System.out.println(cueente);
                        out.writeUTF("true");
                    } catch (Exception eee) {
                        System.out.println(eee);
                        out.writeUTF("false");
                    }
                } catch (Exception ee) { ee.printStackTrace(); }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
