/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;

/**
 *
 * @author adler
 */
public interface JugadorServicio {
    public void crear(Jugador jugador);
    public Jugador buscar(Key claveJugador );
    public void borrar(Jugador jugador);
    public void borrarPorClave(Key claveJugador);
    public void actualizar(Jugador jugador);
}
