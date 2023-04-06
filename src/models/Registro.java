package models;

import java.util.List;

public class Registro {
    private String movimiento;
    private String fecha;
    private String hora;
    private String nombre;
    private String proovedor;
    private String almacenista;
    private String observaciones;
    private List<Material> productos;

    public Registro(String movimiento, String fecha, String hora, String nombre, String proovedor, String almacenista, String observaciones, List<Material> productos) {
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.proovedor = proovedor;
        this.almacenista = almacenista;
        this.observaciones = observaciones;
        this.productos = productos;
    }

    public Registro(String movimiento, String fecha, String hora, String nombre, String almacenista, String observaciones, List<Material> productos) {
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.almacenista = almacenista;
        this.observaciones = observaciones;
        this.productos = productos;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getProovedor() {
        return proovedor;
    }

    public void setProovedor(String proovedor) {
        this.proovedor = proovedor;
    }

    public String getAlmacenista() {
        return almacenista;
    }

    public void setAlmacenista(String almacenista) {
        this.almacenista = almacenista;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Material> getProductos() {
        return productos;
    }

    public void setProductos(List<Material> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Registro{" + "movimiento=" + movimiento + ", fecha=" + fecha + ", hora=" + hora + ", nombre=" + nombre + ", proovedor=" + proovedor + ", almacenista=" + almacenista + ", observaciones=" + observaciones + ", productos=" + productos + '}';
    }
    
            
     
    
    
    
}
