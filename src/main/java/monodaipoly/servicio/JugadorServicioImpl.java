package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.dao.JugadorDAO;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;




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
    public Jugador buscar(Key claveJugador) {
        return jugadorDAO.find(Jugador.class, claveJugador);
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

    @Override
    public List<Jugador> jugadoresQuierenJugar() {
        return this.jugadorDAO.buscarJugadoresQuierenJugar();
    }
}
