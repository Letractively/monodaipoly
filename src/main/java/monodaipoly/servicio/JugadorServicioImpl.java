/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import monodaipoly.dao.JugadorDAO;
import monodaipoly.persistencia.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;



/**
 *
 * @author adler
 */
@Service
public class JugadorServicioImpl implements JugadorServicio{
    private JugadorDAO jugadorDAO;

    @Autowired
    @Required
    public void setJugadorDAO(JugadorDAO jugadorDAO){
        this.jugadorDAO=jugadorDAO;
    }

    @Override
    public void crear(Jugador jugador) {
        jugadorDAO.insert(jugador);
    }

    @Override
    public void buscar(Key claveJugador) {
        jugadorDAO.find(Jugador.class, claveJugador);
    }

    @Override
    public void borrar(Jugador jugador) {
        jugadorDAO.remove(jugador);
    }

    @Override
    public void borrarPorClave(Key claveJugador) {
        jugadorDAO.remove(Jugador.class, claveJugador);
    }

    @Override
    public void actualizar(Jugador jugador) {
        jugadorDAO.update(jugador);
    }
}
