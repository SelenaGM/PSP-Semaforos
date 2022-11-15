import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(2); //plazas libres en el parking / los que acepta (con 1 se esperara a que acabe, con los 2 los irá entrelazando)
        //Todos comparten el mismo semaforo
        //El semaforo tiene sentido cuando hay datos compartidos por diferentes hilos.

        HiloSemaforo h1 = new HiloSemaforo("INC", semaforo);
        HiloSemaforo h2 = new HiloSemaforo("DEC", semaforo);

        h1.start(); //los lanzo
        h2.start();

        try {
            h1.join(); //me espero
            h2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //los enseño

        System.out.println("El resultado es: "+HiloSemaforo.contador);


    }
}