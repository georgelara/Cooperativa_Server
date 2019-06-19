package ar.com.cooperativa.java.servicios;

import ar.com.cooperativa.java.entities.Cliente;
import ar.com.cooperativa.java.repositories.jpa.ClienteR;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServicioClienteRemove implements Runnable{
    private int port=8102;
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("COOPU");
    EntityManager em=emf.createEntityManager();
    ClienteR cli=new ClienteR(em);
    @Override
    public void run() {
        try (ServerSocket ss=new ServerSocket(port)) {
            System.out.println("Servicio Cliente Remove: OK");
            while(true){
                System.out.println("Esperando conexión de cliente...");
                try (
                        Socket so=ss.accept();
                        ObjectInputStream in=new ObjectInputStream(so.getInputStream());
                        ObjectOutputStream out=new ObjectOutputStream(so.getOutputStream());
                ){
                    System.out.println("Servicio Cliente remove, se conecto "+so.getInetAddress());
                    try {
                        Cliente cliente=(Cliente)in.readObject();
                        cli.remove(cliente);
                        System.out.println(cliente);
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
