package ejercicios_examen.ejercicio2recuperacion;

public class Persona extends Thread{
    public static final BotePintura[] botesPintura = {new BotePintura("cian"), new BotePintura("magenta"), new BotePintura("amarillo")};
    private final String[] colores = {"rojo", "azul", "verde"};
    private int color;

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void run() {
        while(true) {
            int[] botes = asignaBotesPintura(color);
            System.out.println(currentThread().getName() + " va a hacer el color " + colores[color]);
            try {
                synchronized (botesPintura) {
                    while (botesPintura[botes[0]].isOcupado() || botesPintura[botes[1]].isOcupado()) {
                        System.out.println(currentThread().getName() + " está esperando para coger los botes");
                        botesPintura.wait();
                    }
                    botesPintura[botes[0]].setOcupado(true);
                    botesPintura[botes[1]].setOcupado(true);
                    System.out.println(currentThread().getName() + " ha cogido los botes: " + botesPintura[botes[0]].getColor() + " y " + botesPintura[botes[1]].getColor());
                }
                System.out.println(currentThread().getName() + " está haciendo el color");
                Thread.sleep((long) (Math.random() * 5 + 1) * 1000);
                synchronized (botesPintura) {
                    botesPintura[botes[0]].setOcupado(false);
                    botesPintura[botes[1]].setOcupado(false);
                    botesPintura.notifyAll();
                }
                System.out.println(currentThread().getName() + " ha soltado los botes");

                Thread.sleep((long) (Math.random() * 5 + 1) * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int[] asignaBotesPintura(int color){
        int[] botes = {0, 0};
        switch (color){
            case 0 ->{
                botes[0] = 1;
                botes[1] = 2;
            }
            case 1 ->{
                botes[1] = 1;
            }
            case 2 ->{
                botes[1] = 2;
            }
        }
        return botes;
    }
}
