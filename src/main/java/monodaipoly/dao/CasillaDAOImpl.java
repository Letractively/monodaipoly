package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import org.springframework.stereotype.Repository;

@Repository
public class CasillaDAOImpl extends GenericDAOImpl <Casilla,Key> implements CasillaDAO{
        
    @Override
    public Jugador buscarDueño(Key idCasilla){
        return em.find(Casilla.class,idCasilla).getDueño();
    }
}
