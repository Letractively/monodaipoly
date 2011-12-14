 package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.CasillaServicio;
import monodaipoly.servicio.JugadorServicio;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TableroControlador {

    private JugadorServicio jugadorServicio;
    private CasillaServicio casillaServicio;
    private UsuarioServicio usuarioServicio;

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

     @RequestMapping(value = "/tablero2", method = RequestMethod.GET)
    public String doShowTablero(Model model,HttpSession sesion) {
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        int c;
         ArrayList casillas=new ArrayList();
         for(c=0;c<=37;c++){
             Casilla casilla = new Casilla();
             casilla.setNumeroCasilla(c);
             casillas.add(casilla);
         }
         if(usuario.getJugador()==null){
            Jugador jugador=new Jugador(usuario);
            jugadorServicio.crear(jugador);
            usuario.setJugador(jugador.getClaveJugador());
         }else{
             jugadorServicio.buscar(usuario.getJugador());
         }
        this.usuarioServicio.actualizar(usuario);
        model.addAttribute("casillas",casillas);

        return "/tablero2";
    }
        @RequestMapping(value = "/tablero", method = RequestMethod.GET)
    public String doShowTablero1(Model model) {

        return "/tablero";
    }

}
