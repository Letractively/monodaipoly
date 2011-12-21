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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author elena
 */

@Entity
public class Casilla implements Serializable {

    //La colocacion de la casilla en el tablero
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key idCasilla;
    @Basic
    private int numeroCasilla;

    @Basic
    private String nombre;


    //provisionalmemte solo hay tipo calles
    @Basic
    private Key tipoCasilla;

    public Key getIdCasilla() {
        return idCasilla;
    }

    public void setIdCasilla(Key idCasilla) {
        this.idCasilla = idCasilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    public void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public Key getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(Key tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }
    

}


