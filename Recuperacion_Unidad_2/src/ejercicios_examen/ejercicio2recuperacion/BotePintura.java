package ejercicios_examen.ejercicio2recuperacion;

public class BotePintura {
    private boolean ocupado;
    private String color;

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BotePintura(String color) {
        this.ocupado = false;
        this.color = color;
    }
}
