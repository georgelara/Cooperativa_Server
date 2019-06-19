package ar.com.cooperativa.java.test;
import ar.com.cooperativa.java.entities.*;
import ar.com.cooperativa.java.enumerados.*;
import ar.com.cooperativa.java.repositories.jpa.*;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class TestRepository {
    public static void main(String[] args) {
        Date fechaActual=new Date();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("COOPU");
        EntityManager em=emf.createEntityManager();

        ClienteR cli=new ClienteR(em);
        CuentaR cue=new CuentaR(em);
        MovimientoR mov=new MovimientoR(em);
        PrestamoR pre=new PrestamoR(em);
        CuotaR cuo=new CuotaR(em);
          
        
        Cliente cliente=new Cliente(987273, "20-31991379/4", "9435", 
                "Lara", "Jorge", Lugares.Administracion, "", "","");
        cli.save(cliente);
        System.out.println("*************************************************");
        System.out.println(cliente);
        System.out.println("*************************************************");
        cli.getAll().forEach(System.out::println);
        System.out.println(cli.getByLegajo(987272));
        System.out.println("*************************************************");
        cli.getLikeApellido("La").forEach(System.out::println);
        System.out.println(cli.getById(1));
        System.out.println("*************************************************");
        System.out.println("*************************************************");
        System.out.println("*************************************************");
  
        Cuenta cuenta=new Cuenta(cli.getById(1), 1550.50, true);
        cue.save(cuenta);
        System.out.println(cuenta);
        cue.getAll().forEach(System.out::println);
        System.out.println("*************************************************");
        cue.getLikeActiva(true).forEach(System.out::println);
        System.out.println("*************************************************");


        Movimiento movimiento1=new Movimiento(cue.getById(1), Tipos.Aporte, 1000, fechaActual);
        Movimiento movimiento2=new Movimiento(cue.getById(1), Tipos.Diferencial, 1000, fechaActual);
        Movimiento movimiento3=new Movimiento(cue.getById(1), Tipos.Reintegro, 1250, fechaActual);
        movimiento1.movimientos();
        movimiento2.movimientos();
        movimiento3.movimientos();
        mov.save(movimiento1);
        mov.save(movimiento2);
        mov.save(movimiento3);
        mov.getAll().forEach(System.out::println);
        System.out.println("*************************************************");

        
        Prestamo prestamo=new Prestamo(9559,cue.getById(1), 10000, 12, false, 3.5, fechaActual);
        pre.save(prestamo);
        pre.getAll().forEach(System.out::println);
        System.out.println("*************************************************");

        Cuota cuota1=new Cuota(pre.getById(9559), 1, 250.50, true, fechaActual);
        Cuota cuota2=new Cuota(pre.getById(9559), 2, 2500.50, true, fechaActual);
        Cuota cuota3=new Cuota(pre.getById(9559), 3, 2500.50, true, fechaActual);
        cuo.save(cuota1);
        cuo.save(cuota2);
        cuo.save(cuota3);
        cuo.getAll().forEach(System.out::println);
        System.out.println("*************************************************");

//REMOVER CUENTAS GENERADAS
    //cue.remove(cue.getById(1));
    //cli.remove(cli.getByLegajo(987273));

    }    
}
