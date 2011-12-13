package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;

import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;

public interface JugadorDAO extends GenericDAO <Jugador, Key>{
    Jugador buscarJugador(Key claveJugador);
}
