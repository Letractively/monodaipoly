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
    private int numeroCasilla;

    @Basic
    private String nombre;
    

    @Basic
    @ManyToOne
    private Jugador dueno;
    @Basic
    private int precioCompra;

    public int getidCasilla(){
        return this.idCasilla;
    }
    public void setidCasilla(int idCasilla){
        this.idCasilla=idCasilla;
    }
    public String getnombre(){
        return this.nombre;
    }
    public void setnombre(String nombre){
        this.nombre=nombre;
    }
    public String getimagen(){
        return this.imagen;
    }
    public void setimagen(String imagen){
        this.imagen=imagen;
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

    public Jugador getDueno() {
        return dueno;
    }
    

    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    public int getPrecioCompra() {
        return precioCompra;
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

    public void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

}


