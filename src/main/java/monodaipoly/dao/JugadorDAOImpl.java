package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class JugadorDAOImpl extends GenericDAOImpl <Jugador,Key> implements JugadorDAO{
    @Override
        public Jugador buscarJugador(Key claveJugador){
                return this.find(Jugador.class, claveJugador);
        }
}
