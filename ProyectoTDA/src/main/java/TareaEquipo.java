/**
 * Autor: Juan Francisco
 * Descripción: Clase que aplica HERENCIA extendiendo del TDA Actividad,
 * pensada para organizar el trabajo colaborativo.
 */
public class TareaEquipo extends Actividad {
    
    private String responsable; // Atributo exclusivo de la tarea de equipo

    public TareaEquipo(String titulo, int nivelComplejidad, String responsable) {
        super(titulo, nivelComplejidad); // Llama al constructor de la clase padre
        if (responsable == null || responsable.trim().isEmpty()) {
            this.responsable = "Equipo Completo";
        } else {
            this.responsable = responsable;
        }
    }

    public String getResponsable() { 
        return responsable; 
    }

    // Método de lógica adicional exclusivo de TareaEquipo
    public String reasignarResponsable(String nuevoResponsable) {
        this.responsable = nuevoResponsable;
        return "La tarea '" + getTitulo() + "' ha sido reasignada. Nuevo responsable: " + this.responsable;
    }
}
