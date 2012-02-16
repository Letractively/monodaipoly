
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;

public interface CasillaServicio {
    
    public void crear(Casilla casilla);
    public void buscar(Key idCasilla );
    public void borrar(Casilla casilla);
    public void preload_casillas();
    public List<Casilla> getAll();
    Casilla buscarPorNumero(int posicion);
}
