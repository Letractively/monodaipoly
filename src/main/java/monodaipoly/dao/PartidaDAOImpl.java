package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import monodaipoly.persistencia.Partida;
import org.springframework.stereotype.Repository;

@Repository
public class PartidaDAOImpl extends GenericDAOImpl<Partida,Key>  implements PartidaDAO{
    @Override
    public List<Partida> partidasCompletas(){
       System.out.println("aki dao de partida");
       Query query=em.createQuery("SELECT p FROM Partida p WHERE p.jugador4 is not null");
       return (List<Partida>) query.getResultList();

    }
    @Override
    public List<Partida> damePartidas(){
        Query query=em.createQuery("SELECT p FROM Partida p");
        return (List<Partida>) query.getResultList();
    }
}