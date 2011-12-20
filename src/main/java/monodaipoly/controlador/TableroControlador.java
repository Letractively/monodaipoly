 package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
             casilla.setIdCasilla(c);
             casillas.add(casilla);
         }
         if(usuario.getJugador()==null){
            Jugador jugador=new Jugador(usuario);
            jugadorServicio.crear(jugador);
            usuario.setJugador(jugador.getClaveJugador());
            this.usuarioServicio.actualizar(usuario);
         }

        Jugador jugador1=jugadorServicio.buscar(usuario.getJugador());
        model.addAttribute("jugador1",jugador1.getPosicion());
        model.addAttribute("casillas",casillas);

        return "/tablero2";
    }
        @RequestMapping(value = "/tablero", method = RequestMethod.GET)
    public String doShowTablero1(Model model) {

        return "/tablero";
    }
        
        @RequestMapping(value = "/perfil", method = RequestMethod.POST)
    public String volverPerfil(Model model,HttpSession sesion) {
            Usuario usuario = (Usuario)sesion.getAttribute("usuario");
            Jugador jugador=this.jugadorServicio.buscar(usuario.getJugador());
            this.jugadorServicio.borrar(jugador);
            usuario.setJugador(null);
            usuarioServicio.actualizar(usuario);
            model.addAttribute("usuario", usuarioServicio.getCurrentUser());
        return "/perfil2";
    }



        @RequestMapping(method=RequestMethod.GET, value="/moverJugador")
        public @ResponseBody String dado(HttpSession sesion,
            @RequestParam("dado") int dado){
            Usuario usuario = (Usuario)sesion.getAttribute("usuario");
            Jugador jugador=jugadorServicio.buscar(usuario.getJugador());
            if(jugador.getPosicion()+dado>35){
                int posAnt=jugador.getPosicion();
                int dif=36-posAnt;
                jugador.setPosicion(0+dado-dif);                    
            }else{
                jugador.setPosicion(jugador.getPosicion()+dado);
            }

            jugadorServicio.actualizar(jugador);
            return this.dadoJson(jugador.getPosicion()).toString();
    }

        private JSONObject dadoJson(int posicion){
        JSONObject json=new JSONObject();
        try{
            json.put("jugador1",posicion);

        }catch (JSONException ex){

        }
        return json;
    }

}
