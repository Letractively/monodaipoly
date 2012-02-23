package monodaipoly.dao;


import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;

public interface CasillaDAO extends GenericDAO <Casilla, Key>{
    Casilla buscarPorNumero(int posicion);
    Casilla buscarPorCalle(Key idCalle);
    
}
