package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Partida;
import org.springframework.stereotype.Repository;


public interface PartidaDAO extends GenericDAO<Partida,Key>{
    public List<Partida>partidasCompletas();
    List<Partida> damePartidas();
    List<Partida> partidasIncompletas();
}
