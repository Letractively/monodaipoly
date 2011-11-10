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
    private String nombre;

    @Basic
    private int precioCompra;

    @Basic
    private int precioCasa;

    @Basic
    private int precioHotel;

    @Basic
    private String color;

    //@Basic
    //private Map<> cobros;


}
