import java.util.concurrent.Semaphore;

public class HiloSemaforo extends Thread{
    //El semaforo te lo tiene que dar el padre
    private Semaphore semaphore;

    //marca los datos que se van a ir compartiendo entre los hilos
    public static int contador= 0;

    //para crear el constructor llamamos al nombre y luego nos dará la opción de introducir el atributo del semaforo
    public HiloSemaforo(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        //lo que queremos hacer
        if (this.getName().equalsIgnoreCase("inc")){ //incrementa
            System.out.println("Soy el incrementador");
            System.out.println("INC: Estoy solicitando el semaforo");

            try {
                semaphore.acquire(); //si no puede se quedará parado hasta que pueda
                //el codigo que va después del acquire irá dentro del trycatch
                System.out.println("INC: Ya tengo el semaforo en verde");

                for (int i = 0; i < 100; i++) {
                    contador++;
                    System.out.println("INC: "+contador);
                    sleep(500); //esto es solo para que nos de tiempo de ir viendolo crearse
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //IMPORTANTE LIBERAR EL SEMAFORO
            finally{
                semaphore.release();
            }

        }else{ //decrementa
            System.out.println("Soy el decrementador");
            System.out.println("DEC: Estoy solicitando el semaforo");

            try {
                semaphore.acquire();
                System.out.println("DEC:  Ya tengo el semaforo en verde");
                for (int i = 0; i < 100; i++) {
                    contador--;
                    System.out.println("DEC: "+contador);
                    sleep(200);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                semaphore.release();
            }
        }
    }
}
