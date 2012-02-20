package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.List;
import monodaipoly.dao.CasillaDAO;
import monodaipoly.dao.JugadorDAO;
import monodaipoly.dao.PartidaDAO;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaServicioImpl implements PartidaServicio {

    private JugadorDAO jugadorDAO;
    private PartidaDAO partidaDAO;
    private CasillaDAO casillaDAO;

    @Autowired
    @Required
    public void setJugadorDAO(JugadorDAO jugadorDAO) {
        this.jugadorDAO = jugadorDAO;
    }

    @Autowired
    @Required
    public void setPartidaDAO(PartidaDAO partidaDAO) {
        this.partidaDAO = partidaDAO;
    }

    @Autowired
    @Required
    public void setCasillaDAO(CasillaDAO casillaDAO) {
        this.casillaDAO = casillaDAO;
    }

    @Override
    public void crear(final Partida partida) {
        this.partidaDAO.insert(partida);
    }

    @Override
    public void actualizar(final Partida partida) {
        this.partidaDAO.update(partida);
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
        Partida partida = new Partida();
        this.crear(partida);
        List<Jugador> jugadores = this.jugadorDAO.buscarJugadoresQuierenJugar();
        List<Jugador> jugadoresVanAJugar = new ArrayList(4);
        System.out.println("Estamos en empezar partdia");
        if (jugadores.size() > 3) {
            for (Jugador jugador : jugadores) {
                //
                System.out.println("jugador");
                System.out.println(jugador.getClaveJugador());
                //
                if (jugadoresVanAJugar.size() < 5) {
                    jugadoresVanAJugar.add(jugador);
                    jugador.setEstoyJugando(true);
                    jugador.setPartida(partida.getIdpartida());
                    if (jugadoresVanAJugar.size() == 0) {
                        partida.setJugador1(jugador.getClaveJugador());
                        //
                        System.out.println("jugador1 asociado a partida");
                    }
                    if (jugadoresVanAJugar.size() == 1) {
                        //
                        System.out.println("jugador2 asociado a partida");
                        partida.setJugador2(jugador.getClaveJugador());
                    }
                    if (jugadoresVanAJugar.size() == 2) {
                        //
                        System.out.println("jugador3 asociado a partida");
                        partida.setJugador3(jugador.getClaveJugador());
                    }
                    if (jugadoresVanAJugar.size() == 3) {
                        //
                        System.out.println("jugador4 asociado a partida");
                        partida.setJugador4(jugador.getClaveJugador());
                    }
                }

            }
        } else {
            System.out.println("No hay jugadores suficientes para jugar");
            return null;
        }
        return partida;

    }

    @Override
    public Partida empezarPartida2() {
        List<Key> casillas1 = new ArrayList<Key>();
        Partida partida = new Partida();
        List<Casilla> casillas = casillaDAO.getAll(Casilla.class);
        for (Casilla casilla : casillas) {
            casillas1.add(casilla.getIdCasilla());
        }
        partida.setCasillas(casillas1);
        this.crear(partida);
        return partida;
    }

    @Override
    public Partida comprobarPartidaLibre() {
        List<Partida> partidas = partidaDAO.getAll(Partida.class);
        for (Partida partida : partidas) {
            if (this.comprobarHueco(partida.getIdpartida()) != 0) {
                return partida;
            }
        }
        return null;

    }

    @Override
    public int comprobarHueco(Key clavePartida) {
        if (this.buscar(clavePartida).getJugador1() == null) {
            return 1;
        } else if (this.buscar(clavePartida).getJugador2() == null) {
            return 2;
        } else if (this.buscar(clavePartida).getJugador3() == null) {
            return 3;
        } else if (this.buscar(clavePartida).getJugador4() == null) {
            return 4;
        } else {
            return 0;
        }
    }

    @Override
    public List<Partida> partidaCompleta() {
        List<Partida> todasPartidas = new ArrayList<Partida>();
        
        try {
            for (Partida partida : partidaDAO.getAll(Partida.class)) {
                //if (partida.getJugador1() != null && partida.getJugador2() != null && partida.getJugador3() != null && partida.getJugador4() != null) {
                if(partida.isCompleta()){
                    todasPartidas.add(partida);
                    System.out.println("recoge partidas");
                }
            }
            System.out.println(todasPartidas.get(0).getIdString());
        }catch (Exception e) {
            System.out.println("Aqui error en en partidaCompleta servicio");
            e.printStackTrace();
        }
        return todasPartidas;
    }

    @Override
    public List<Partida> todasPartidas() {
        //System.out.println("partidaDAO.getAll(Partida.class).size()= " + partidaDAO.getAll(Partida.class).size());
        return partidaDAO.getAll(Partida.class);
    }
}
