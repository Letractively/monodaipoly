/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Mensaje;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adri
 */
@Repository
public class MensajeDAOImpl extends GenericDAOImpl<Mensaje,Key> implements MensajeDAO{
    
}
