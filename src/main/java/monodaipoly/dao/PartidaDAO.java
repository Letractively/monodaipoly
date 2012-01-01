package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Partida;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaDAO extends GenericDAO<Partida,Key>{

}
