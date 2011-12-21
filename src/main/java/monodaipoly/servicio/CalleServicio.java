/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Calle;

/**
 *
 * @author instalador
 */
public interface CalleServicio {
    public void crear(Calle calle);
    public Calle buscar(Key idCalle );
    public void borrar(Calle calle);
    public void actualizar(Calle calle);
}
