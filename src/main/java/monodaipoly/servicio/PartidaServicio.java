package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Partida;


public interface PartidaServicio {
    public void crear(final Partida partida);
    public void actualizar(final Partida partida);
    public void terminar(Partida partida);
    public Partida buscar(Key idPartida);
    public Partida empezarPartida();
    public Partida empezarPartida2();
    public Partida comprobarPartidaLibre();
    public int comprobarHueco(Key clavePartida);
    public List<Partida> partidaCompleta();
}
