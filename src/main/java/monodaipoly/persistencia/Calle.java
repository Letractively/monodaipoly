/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 *
 * @author instalador
 */
public class Calle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key idCalle;

    @Basic
    private String propietario;

    @Basic
    private int precio;

    @Basic
    private int multa;

    @Basic
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Key getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(Key idCalle) {
        this.idCalle = idCalle;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }



}
