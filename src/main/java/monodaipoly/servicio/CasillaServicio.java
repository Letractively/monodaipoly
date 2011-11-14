
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;

public interface CasillaServicio {
    
    public void crear(Casilla casilla);
    public void buscar(Key idCasilla );
    public void borrar(Casilla casilla);
    public Jugador buscarDueno(Key idCasilla);
    
}
