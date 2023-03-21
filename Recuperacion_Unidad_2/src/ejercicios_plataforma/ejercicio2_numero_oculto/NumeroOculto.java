package ejercicios_plataforma.ejercicio2_numero_oculto;

public class NumeroOculto extends Thread {
    public static int numeroAdivinar;
    public static boolean terminado = false;
    private int numero;


    public NumeroOculto() {
        super();
        this.numero = 0;
    }



    public int getNumero() {
        return numero;
    }



    public void setNumero(int numero) {
        this.numero = numero;
    }



    @Override
    public void run() {
        int opc = propuestaNumero(this.getNumero());

        while(opc==0) {
            this.setNumero((int) (Math.random()*100));
            opc = propuestaNumero(this.getNumero());
        }

    }

    synchronized public static int propuestaNumero(int num) {
        int opc = 0;

        if (num == numeroAdivinar && !terminado) {
            opc = 1;
            terminado = true;
            System.out.println(Thread.currentThread().getName() + " HA ACERTADO");
            Thread.currentThread().interrupt();

        } else if (terminado) {
            opc = -1;
            System.out.println(Thread.currentThread().getName() + " ya ha acertado otro hilo");
            currentThread().interrupt();
        }

        return opc;
    }

    public static void main(String[] args) {
        numeroAdivinar = (int) (Math.random() * 10) + 1;
        for (int i = 10; i >= 1; i--) {
            NumeroOculto numOculto = new NumeroOculto();
            numOculto.setName("hilo" + i);
            numOculto.start();
        }
    }

}
