/**
 * Autor: Juan Francisco
 * Proyecto: TDA Agenda de Equipo
 * Descripción: Clase abstracta que representa el TDA base,
 * con encapsulamiento, validaciones en constructor y lógica recursiva.
 */
public abstract class Actividad {
    // Atributos encapsulados
    private String titulo;
    private int nivelComplejidad; // Escala del 1 al 10
    private boolean estaCompletada;

    // Constructor con validaciones
    public Actividad(String titulo, int nivelComplejidad) {
        if (titulo == null || titulo.trim().isEmpty()) {
            this.titulo = "Actividad sin nombre";
        } else {
            this.titulo = titulo;
        }
        
        if (nivelComplejidad <= 0) {
            this.nivelComplejidad = 1;
        } else {
            this.nivelComplejidad = nivelComplejidad;
        }
        this.estaCompletada = false; // Toda tarea inicia pendiente
    }

    //MÉTODOS DE ACCESO Getters y Setters
    public String getTitulo() { return titulo; }
    public int getNivelComplejidad() { return nivelComplejidad; }
    public boolean isEstaCompletada() { return estaCompletada; }

    public void setNivelComplejidad(int nivel) {
        this.nivelComplejidad = Math.max(1, nivel);
    }

    //MÉTODOS DE LÓGICA DEL DOMINIO 

    //1:Marcar la tarea como terminada
    public String completarActividad() {
        if (estaCompletada) {
            return "La actividad '" + titulo + "' ya estaba terminada previamente.";
        }
        estaCompletada = true;
        return "¡Excelente! La actividad '" + titulo + "' ha sido COMPLETADA.";
    }

    //2: Reiniciar una tarea (si hubo un error y hay que rehacerla)
    public String reiniciarActividad() {
        estaCompletada = false;
        return "La actividad '" + titulo + "' ha vuelto a estado PENDIENTE.";
    }

    //3: Obtener un resumen rápido del estado
    public String evaluarEstado() {
        if (estaCompletada) {
            return "[TERMINADA] " + titulo;
        }
        return "[PENDIENTE] " + titulo + " (Nivel de Complejidad: " + nivelComplejidad + ")";
    }

    //4: MÉTODO RECURSIVO Estimación Ágil de Esfuerzo
    // Utiliza la Sucesión de Fibonacci, estándar en el desarrollo de software real.
    // Condición de avance: f(n-1) + f(n-2)
    public int calcularPuntosEsfuerzo(int complejidad) {
        // Casos Base
        if (complejidad <= 0) {
            return 0;
        } else if (complejidad == 1) {
            return 1;
        }
        // Condición de avance que se llama recursiva doble
        return calcularPuntosEsfuerzo(complejidad - 1) + calcularPuntosEsfuerzo(complejidad - 2);
    }
}
