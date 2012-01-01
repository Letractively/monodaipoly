/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import monodaipoly.persistencia.Mensaje;
import org.springframework.stereotype.Repository;


@Repository
public class MensajeDAOImpl extends GenericDAOImpl<Mensaje,Key> implements MensajeDAO{
    
    @Override
    public List<Mensaje> conseguirMensajesRecibidos(String nombre){
       Query query=em.createQuery("SELECT m FROM Mensaje m WHERE m.destinatario = :nombre");
       query.setParameter("nombre",nombre);
       return (List<Mensaje>) query.getResultList();

    }
}
