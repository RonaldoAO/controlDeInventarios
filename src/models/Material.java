package models;

public class Material implements Comparable<Material>{
   private int cantidad;
   private String nombre;

    public Material(int cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

   
   
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "{" + cantidad + " " +  nombre + "} ";
    }

    @Override
    public int compareTo(Material o) {
        String a = new String(this.getNombre());
        String b = new String(o.getNombre()); 
        return a.compareTo(b);
    }

    
    
    
}
