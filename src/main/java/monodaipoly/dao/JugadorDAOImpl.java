package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;

import monodaipoly.persistencia.Jugador;
import org.springframework.stereotype.Repository;
@Repository
public class JugadorDAOImpl extends GenericDAOImpl <Jugador,Key> implements JugadorDAO{

}
