package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Partida;
import org.springframework.stereotype.Repository;

@Repository
public class PartidaDAOImpl extends GenericDAOImpl<Partida,Key>  implements PartidaDAO{

}