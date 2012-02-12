package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;


public interface JugadorServicio {
    public void crear(Jugador jugador);
    public Jugador buscar(Key claveJugador );
    public void borrar(Jugador jugador);
    public void borrarPorClave(Key claveJugador);
    public void actualizar(Jugador jugador);
    public List<Jugador> jugadoresQuierenJugar();
    public Key comprobarJugadorConPartida(Key jugador);
     public void comprobarJugadorConPartida();
}
