package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;

import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorDAO extends GenericDAO <Jugador, Key>{
    Jugador buscarJugador(Key claveJugador);
    List<Jugador> buscarJugadoresQuierenJugar();
}
