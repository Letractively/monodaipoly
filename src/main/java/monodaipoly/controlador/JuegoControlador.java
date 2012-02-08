 package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.CasillaServicio;
import monodaipoly.servicio.JugadorServicio;
import monodaipoly.servicio.PartidaServicio;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"jugador"})
@Controller
public class JuegoControlador {
    
    private JugadorServicio jugadorServicio;
    private CasillaServicio casillaServicio;
    private UsuarioServicio usuarioServicio;
    private PartidaServicio partidaServicio;

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio=usuarioServicio;
    }

    @Autowired
    @Required
    public void setJugadorServicio(JugadorServicio jugadorServicio){
        this.jugadorServicio=jugadorServicio;
    }

    @Autowired
    @Required
    public void setCasillaServicio(CasillaServicio casillaServicio){
        this.casillaServicio=casillaServicio;
    }

    @Autowired
    @Required
    public void setPartidaServicio(PartidaServicio partidaServicio){
        this.partidaServicio=partidaServicio;
    }

    
    @RequestMapping(value = "/comprobarTurno", method = RequestMethod.GET)
    public String comprobarTurno(Model model,HttpSession sesion) {
        int turno=0;
        Jugador jugador = (Jugador)sesion.getAttribute("jugador");
        Partida partida=partidaServicio.buscar(jugador.getPartida());
        if(partida.getTurno()==partida.getJugador1()){
            turno=1;
        }
        if(partida.getTurno()==partida.getJugador2()){
            turno=2;
        }
        if(partida.getTurno()==partida.getJugador3()){
            turno=3;
        }
        if(partida.getTurno()==partida.getJugador4()){
            turno=4;
        }
        model.addAttribute("turno",turno);
        return "/tablero2";
    
    }
    @RequestMapping(value = "/cambiarTurno", method = RequestMethod.GET)
    public String cambiarTurno(Model model,HttpSession sesion) {
        int turno=0;
        Jugador jugador = (Jugador)sesion.getAttribute("jugador");
        Partida partida=partidaServicio.buscar(jugador.getPartida());
        if(partida.getTurno()==partida.getJugador1()){
            turno=2;
        }
        if(partida.getTurno()==partida.getJugador2()){
            turno=3;
        }
        if(partida.getTurno()==partida.getJugador3()){
            turno=4;
        }
        if(partida.getTurno()==partida.getJugador4()){
            turno=1;
        }
        model.addAttribute("turno",turno);
        return "/tablero2";
    
    }
}
