/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.persistencia;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.google.appengine.api.datastore.Key;
import java.util.*;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.ManyToOne;

/**
 *
 * @author elena
 */

@Entity
public class Casilla implements Serializable {

    @Id
    private int idCasilla;

    @Basic
    private String imagen;
    
    @Basic
    private String numeroCasilla;

    @Basic
    private String nombre;
    
    @Basic
    @ManyToOne
    private Jugador dueño;
    @Basic
    private int precioCompra;

    @Basic
    private int precioCasa;

    @Basic
    private int precioHotel;

    @Basic
    private String color;

    @Basic
    private HashMap<Integer,Integer> cobros;

    public HashMap<Integer, Integer> getCobros() {
        return cobros;
    }

    public String getColor() {
        return color;
    }

    public int getIdCasilla() {
        return idCasilla;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public Jugador getDueño() {
        return dueño;
    }
    

    public String getNumeroCasilla() {
        return numeroCasilla;
    }

    public int getPrecioCasa() {
        return precioCasa;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getPrecioHotel() {
        return precioHotel;
    }

    public void setCobros(HashMap<Integer, Integer> cobros) {
        this.cobros = cobros;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroCasilla(String numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public void setPrecioCasa(int precioCasa) {
        this.precioCasa = precioCasa;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioHotel(int precioHotel) {
        this.precioHotel = precioHotel;
    }

    
}


