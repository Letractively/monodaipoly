package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import javax.persistence.Query;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import org.springframework.stereotype.Repository;

@Repository
public class CasillaDAOImpl extends GenericDAOImpl <Casilla,Key> implements CasillaDAO{
        @Override
    public Casilla buscarPorNumero(int posicion) {
        Query query=em.createQuery("SELECT c FROM Casilla c WHERE c.numeroCasilla= :posicion");
       query.setParameter("posicion",posicion);
       return (Casilla) query.getSingleResult();
    }
    
}
