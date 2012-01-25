package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import monodaipoly.dao.JugadorDAO;
import monodaipoly.dao.PartidaDAO;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class PartidaServicioImpl implements PartidaServicio{
    private JugadorDAO jugadorDAO;
    private PartidaDAO partidaDAO;

    @Autowired
    @Required
    public void setJugadorDAO(JugadorDAO jugadorDAO){
        this.jugadorDAO=jugadorDAO;
    }

    @Autowired
    @Required
    public void setPartidaDAO(PartidaDAO partidaDAO){
        this.partidaDAO=partidaDAO;
    }

    @Override
    public void crear(final Partida partida) {
        this.partidaDAO.insert(partida);
    }

    @Override
    public void terminar(Partida partida) {
        this.partidaDAO.remove(partida);
    }

    @Override
    public Partida buscar(Key idPartida) {
        return this.partidaDAO.find(Partida.class, idPartida);
    }
    /*
     * Para empezar una partida
     * buscamos todos los jugadores que tienen el atributo,estoyJugando a false
     * luego metemos en la lista jugadoresVanAJugar 4 jugadores
     * relacionamos los jugadores con esa partida
     * y a la partida con esos 4 jugadores
     * les cambiamos el estoyJugando a true
     * 
     */
    @Override
    public Partida empezarPartida() {
        Partida partida=new Partida();
        this.crear(partida);
        List<Jugador> jugadores=this.jugadorDAO.buscarJugadoresQuierenJugar();
        List<Jugador> jugadoresVanAJugar=new ArrayList(4);
        System.out.println("Estamos en empezar partdia");
        if(jugadores.size()>3){
            for(Jugador jugador:jugadores){
                //
                System.out.println("jugador");
                System.out.println(jugador.getClaveJugador());
                //
                if(jugadoresVanAJugar.size()<5){
                jugadoresVanAJugar.add(jugador);
                jugador.setEstoyJugando(true);
                jugador.setPartida(partida.getIdpartida());
                if(jugadoresVanAJugar.size()==0){
                    partida.setJugador1(jugador.getClaveJugador());
                    //
                    System.out.println("jugador1 asociado a partida");
                }
                if(jugadoresVanAJugar.size()==1){
                    //
                    System.out.println("jugador2 asociado a partida");
                    partida.setJugador2(jugador.getClaveJugador());
                }
                if(jugadoresVanAJugar.size()==2){
                    //
                    System.out.println("jugador3 asociado a partida");
                    partida.setJugador3(jugador.getClaveJugador());
                }
                if(jugadoresVanAJugar.size()==3){
                    //
                    System.out.println("jugador4 asociado a partida");
                    partida.setJugador4(jugador.getClaveJugador());
                }
                }
                
            }
        }else {
            System.out.println("No hay jugadores suficientes para jugar");
            return null;
        }
        return partida;

    }
}
