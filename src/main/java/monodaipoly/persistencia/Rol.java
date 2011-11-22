/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.persistencia;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author elena
 */

@Entity
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Key id;

    @Column(nullable = false, length = 50)
    private String nombre;


    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        return ((Rol)o).getId().equals(id) || ((Rol)o).getNombre().equals(nombre);
    }

}
