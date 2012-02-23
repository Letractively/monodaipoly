package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.persistence.Query;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class JugadorDAOImpl extends GenericDAOImpl <Jugador,Key> implements JugadorDAO{
    @Override
        public Jugador buscarJugador(Key claveJugador){
                return this.find(Jugador.class, claveJugador);
        }

    @Override
    public List<Jugador> buscarJugadoresQuierenJugar() {
        Query query=em.createQuery("SELECT j FROM Jugador j WHERE j.estoyJugando=false");
        return (List<Jugador>)query.getResultList();
    }

    @Override
    public List<Jugador> todosJugadoresDePartida(Key partida){
       Query query=em.createQuery("SELECT j FROM Jugador j WHERE j.partida= :partida");
       query.setParameter("partida",partida);
       return (List<Jugador>) query.getResultList();
    }


}
