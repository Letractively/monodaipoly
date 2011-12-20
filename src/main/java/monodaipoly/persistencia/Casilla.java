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

    //La colocacion de la casilla en el tablero
    @Id
    private int idCasilla;

    @Basic
    private String nombre;


    //provisionalmemte solo hay tipo calles
    @Basic
    private Key tipoCasilla;

    public Key getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(Key tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    

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





    public int getIdCasilla() {
        return idCasilla;
    }

   

    public String getNombre() {
        return nombre;
    }

    
    

    
    public void setIdCasilla(int idCasilla) {
        this.idCasilla = idCasilla;
    }

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

}


