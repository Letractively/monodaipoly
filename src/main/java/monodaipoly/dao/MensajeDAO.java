/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Mensaje;

public interface MensajeDAO extends GenericDAO<Mensaje,Key>{
    
     public List<Mensaje> conseguirMensajesRecibidos(String nombre);
     public List<Mensaje> conseguirMensajesEnviados(String nombre);
}
