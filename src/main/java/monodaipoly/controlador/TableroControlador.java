package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Calle;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.CalleServicio;
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
public class TableroControlador {

    private JugadorServicio jugadorServicio;
    private CasillaServicio casillaServicio;
    private UsuarioServicio usuarioServicio;
    private PartidaServicio partidaServicio;
    private CalleServicio calleServicio;
    private JuegoControlador juegoControlador;

    @Autowired
    @Required
    public void setJuegoControlador(JuegoControlador juegoControlador) {
        this.juegoControlador = juegoControlador;
    }

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Autowired
    @Required
    public void setCalleServicio(CalleServicio calleServicio) {
        this.calleServicio = calleServicio;
    }

    @Autowired
    @Required
    public void setJugadorServicio(JugadorServicio jugadorServicio) {
        this.jugadorServicio = jugadorServicio;
    }

    @Autowired
    @Required
    public void setCasillaServicio(CasillaServicio casillaServicio) {
        this.casillaServicio = casillaServicio;
    }

    @Autowired
    @Required
    public void setPartidaServicio(PartidaServicio partidaServicio) {
        this.partidaServicio = partidaServicio;
    }

    /* @RequestMapping(value = "/tablero2", method = RequestMethod.GET)
    public String doShowTablero(Model model,HttpSession sesion) {
    Usuario usuario = (Usuario)sesion.getAttribute("usuario");
    
    if(usuario.getJugador()==null){
    Jugador jugador=new Jugador(usuario);
    jugadorServicio.crear(jugador);
    usuario.setJugador(jugador.getClaveJugador());
    this.usuarioServicio.actualizar(usuario);
    Partida partida= partidaServicio.empezarPartida();
    if(partida!=null){
    model.addAttribute("partida",partida);
    List <Casilla> casillas=casillaServicio.getAll();
    model.addAttribute("casillas",casillas);
    
    
    System.out.println("Aqui partida en tablero controlador");
    System.out.println(partida.getIdpartida());
    
    }
    
    Jugador jugador1=jugadorServicio.buscar(usuario.getJugador());
    model.addAttribute("jugador1",jugador1.getPosicion());
    
    
    return "/tablero2";
    }
    return "/perfilPrueba";
    
    
    }*/
    @RequestMapping(value = "/perfil", method = RequestMethod.POST)
    public String volverPerfil(Model model, HttpSession sesion) {
        /*Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        Jugador jugador=this.jugadorServicio.buscar(usuario.getJugador());
        this.jugadorServicio.borrar(jugador);
        usuario.setJugador(null);
        usuarioServicio.actualizar(usuario);
        model.addAttribute("usuario", usuarioServicio.getCurrentUser());*/
        String enPartida = "Estas en una partida";
        model.addAttribute("enPartida", enPartida);
        return "/perfil";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/moverJugador")
    public @ResponseBody
    String dado(HttpSession sesion, @RequestParam("jugQueTira") String jugQueTira) {
        //Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        //Jugador jugador=jugadorServicio.buscar(usuario.getJugador());

        Jugador jugador = jugadorServicio.buscar(usuarioServicio.buscar(jugQueTira).getJugador());
        Partida partida = partidaServicio.buscar(jugador.getPartida());

        juegoControlador.mostrarCallesDeLosJugadores(partida);

        int numJugador = this.numDelJugador(jugador, partida);
        if (comprobarQueEsTurno(numJugador, partida) && partida.getHaTirado() == false) {

            int dado = (int) (6.0 * Math.random()) + 1;
            //int dado=((int)Math.random()*6);
            if (jugador.getPosicion() + dado > 35) {
                int posAnt = jugador.getPosicion();
                int dif = 36 - posAnt;
                jugador.setPosicion(0 + dado - dif);
                jugador.setDinero(jugador.getDinero() + 200);
            } else {
                jugador.setPosicion(jugador.getPosicion() + dado);
                //jugador.setPosicion(3);

            }
            partida.setHaTirado(true);
            System.out.println("AQUIIIIIIIIIIIIIIIIII 5");
            juegoControlador.mostrarCallesDeLosJugadores(partida);
            partidaServicio.actualizar(partida);
            return this.dadoJson(jugador.getPosicion(), dado, numJugador).toString();
        } else {
            return "no";
        }


    }

    private int numDelJugador(Jugador jugador, Partida partida) {
        int n = 0;
        if (partida.getJugador1() != null && partida.getJugador1().compareTo(jugador.getClaveJugador()) == 0) {
            n = 1;
        }
        if (partida.getJugador2() != null && partida.getJugador2().compareTo(jugador.getClaveJugador()) == 0) {
            n = 2;
        }
        if (partida.getJugador3() != null && partida.getJugador3().compareTo(jugador.getClaveJugador()) == 0) {
            n = 3;
        }
        if (partida.getJugador4() != null && partida.getJugador4().compareTo(jugador.getClaveJugador()) == 0) {
            n = 4;
        }
        return n;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tirarDado")
    public @ResponseBody
    String tirarDado(HttpSession sesion, @RequestParam("jugQueTira") String jugQueTira) {

        Jugador jugador = jugadorServicio.buscar(usuarioServicio.buscar(jugQueTira).getJugador());
        Partida partida = partidaServicio.buscar(jugador.getPartida());

        int numJugador = this.numDelJugador(jugador, partida);
        int posicion = 0;

        int dado = (int) (6.0 * Math.random()) + 1;
        if (dado == 6) {
            posicion = jugador.getPosicion();
        } else {
            jugador.setPosicion(9);
            jugador.setEstoyEnCarcel(true);
            jugadorServicio.actualizar(jugador);
        }
        juegoControlador.cambiarTurnoManualmente(jugQueTira);
        return dadoJson(posicion, dado, numJugador).toString();


    }

    private JSONObject dadoJson(int posicion, int dado, int numJugador) {
        JSONObject json = new JSONObject();
        try {

            json.put("nuevaPosicion", posicion);
            json.put("dado", dado);
            json.put("numJugador", numJugador);

        } catch (JSONException ex) {
        }
        return json;
    }

    //metodo refactorizado .... 
    @RequestMapping(value = "/prepararPartida2", method = RequestMethod.GET)
    public String prepararPartida2(Model model, HttpSession sesion) {


        List<String> arrayTux = new ArrayList(15);
        int iTux;
        int aleatorio;
        String cadena;
        for (iTux = 0; iTux < 15; iTux++) {
            cadena = "Estilos/tux/tux" + iTux + ".png";
            arrayTux.add(iTux, cadena);
        }

        int posicion;
        String enCola = "Esperando a otros jugadores...";
        String imagenCola = "<img id='imagenGif' src='/Estilos/ProgressBar.gif' />";
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        if (usuarioServicio.getCurrentUser().getJugador() == null) {

            Jugador jugador = new Jugador();
            jugadorServicio.crear(jugador);
            jugador.setNick(usuario.getNick());
            jugador.setEstoyEnCola(true);
            jugadorServicio.actualizar(jugador);
            usuario.setJugador(jugador.getClaveJugador());
            usuarioServicio.actualizar(usuario);
            Partida partida = partidaServicio.comprobarPartidaLibre();

            if (partida == null) {

                Partida partidaNueva = partidaServicio.empezarPartida2();
                partidaNueva.setJugador1(jugador.getClaveJugador());
                partidaNueva.setTurno(jugador.getClaveJugador());
                jugador.setPartida(partidaNueva.getIdpartida());
                aleatorio = (int) (Math.random() * 14);
                jugador.setTux(arrayTux.get(aleatorio));
                jugadorServicio.actualizar(jugador);
                partidaServicio.actualizar(partidaNueva);
                //return perfil y mostrar cola
                model.addAttribute("enCola", enCola);
                model.addAttribute("enColaImagen", imagenCola);
                return "/perfil";

            } else {

                posicion = partidaServicio.comprobarHueco(partida.getIdpartida());
                jugador.setPartida(partida.getIdpartida());
                jugadorServicio.actualizar(jugador);

                if (posicion == 1) {
                    partida.setJugador1(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                } else if (posicion == 2) {
                    aleatorio = (int) (Math.random() * 14);
                    while (arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador1()).getTux())) {
                        aleatorio = (int) (Math.random() * 14);
                    }
                    jugador.setTux(arrayTux.get(aleatorio));
                    jugadorServicio.actualizar(jugador);
                    partida.setJugador2(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                } else if (posicion == 3) {
                    aleatorio = (int) (Math.random() * 14);
                    while (arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador1()).getTux()) || arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador2()).getTux())) {
                        aleatorio = (int) (Math.random() * 14);
                    }
                    jugador.setTux(arrayTux.get(aleatorio));
                    jugadorServicio.actualizar(jugador);
                    partida.setJugador3(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                } else if (posicion == 4) {

                    aleatorio = (int) (Math.random() * 14);
                    while (arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador1()).getTux()) || arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador2()).getTux()) || arrayTux.get(aleatorio).equals(jugadorServicio.buscar(partida.getJugador3()).getTux())) {
                        aleatorio = (int) (Math.random() * 14);
                    }
                    jugador.setTux(arrayTux.get(aleatorio));
                    jugadorServicio.actualizar(jugador);

                    partida.setJugador4(jugador.getClaveJugador());
                    //aqui voy a poner a los 4 jugadores de esta partida el atributo estoy jugando
                    //a true y el dinero  xq ya hay 4 jugadores y puede empezar el juego
                    Key keyPlayer = partida.getJugador1();
                    Jugador player = jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    player.setEstoyEnCola(false);
                    player.setEstoyEnCarcel(false);
                    //meter todas las calles al jugador
                    /*
                    for(Casilla c:casillaServicio.getAll()){
                    List<Key> calles =player.getCalles();
                    calles.add(c.getIdCasilla());
                    player.setCalles(calles);
                    jugadorServicio.actualizar(player);
                    }*/

                    jugadorServicio.actualizar(player);
                    keyPlayer = partida.getJugador2();
                    player = jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    player.setEstoyEnCola(false);
                    player.setEstoyEnCarcel(false);
                    jugadorServicio.actualizar(player);



                    keyPlayer = partida.getJugador3();
                    player = jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    player.setEstoyEnCola(false);
                    player.setEstoyEnCarcel(false);
                    jugadorServicio.actualizar(player);

                    keyPlayer = partida.getJugador4();
                    player = jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    player.setEstoyEnCola(false);
                    player.setEstoyEnCarcel(false);


                    partida.setFechaTurno(System.currentTimeMillis() + 60000);
                    partida.setCompleta(true);
                    jugadorServicio.actualizar(player);
                    partida.setBote(0);

                    partidaServicio.actualizar(partida);
                    model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                    //return ... el metodo que falta que carga el modelo

                    return "redirect:comenzarPartida";

                }
            }
        } else if (usuarioServicio.getCurrentUser().getJugador() != null) {

            Jugador jugQueJodiaTodo = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());

            if (jugQueJodiaTodo.getEstoyJugando() == false) {

                model.addAttribute("enCola", enCola);
                model.addAttribute("enColaImagen", imagenCola);
                return "/perfil";//else return perfil y mostrar cola
            } else if (jugQueJodiaTodo.getEstoyJugando() == true) {//if esta jugando...

                model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                return "redirect:comenzarPartida";//return ... el metodo que falta que carga el modelo
            }

            model.addAttribute("enCola", enCola);
            model.addAttribute("enColaImagen", imagenCola);
            return "/perfil";//else return perfil y mostrar cola




        }





        model.addAttribute("enCola", enCola);
        model.addAttribute("enColaImagen", imagenCola);
        return "/perfil";
    }

    @RequestMapping(value = "/comenzarPartida", method = RequestMethod.GET)
    public String comenzarPartida(Model model, HttpSession sesion) {
        Jugador jugador = (Jugador) sesion.getAttribute("jugador");
        Partida partida = partidaServicio.buscar(jugador.getPartida());
        int i;
        //cargar en modelo casillas
        List<Calle> calles = new ArrayList(35);
        List<Casilla> casillas = casillaServicio.getAll();
        for (i = 0; i <= casillas.size() - 1; i++) {

            if (casillas.get(i).getTipoCasilla() != null) {
                calles.add(i, calleServicio.buscar(casillas.get(i).getTipoCasilla()));
            } else {
                Calle calle = new Calle();
                calle.setPrecio(0);
                calles.add(i, calle);
            }
        }
        model.addAttribute("calles", calles);
        model.addAttribute("casillas", casillas);


        //cargar en modelo posiciones
        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        String nick1 = "";
        String nick2 = "";
        String nick3 = "";
        String nick4 = "";
        int dinero1 = -1;
        int dinero2 = -1;
        int dinero3 = -1;
        int dinero4 = -1;
        String tux1 = "";
        String tux2 = "";
        String tux3 = "";
        String tux4 = "";

        if (partida.getJugador1() != null) {
            Jugador jugador1 = jugadorServicio.buscar(partida.getJugador1());
            pos1 = jugador1.getPosicion();
            nick1 = jugador1.getNick();
            dinero1 = jugador1.getDinero();
            tux1 = jugador1.getTux();
        }
        if (partida.getJugador2() != null) {
            Jugador jugador2 = jugadorServicio.buscar(partida.getJugador2());
            pos2 = jugador2.getPosicion();
            nick2 = jugador2.getNick();
            dinero2 = jugador2.getDinero();
            tux2 = jugador2.getTux();
        }
        if (partida.getJugador3() != null) {
            Jugador jugador3 = jugadorServicio.buscar(partida.getJugador3());
            pos3 = jugador3.getPosicion();
            nick3 = jugador3.getNick();
            dinero3 = jugador3.getDinero();
            tux3 = jugador3.getTux();
        }
        if (partida.getJugador4() != null) {
            Jugador jugador4 = jugadorServicio.buscar(partida.getJugador4());
            pos4 = jugador4.getPosicion();
            nick4 = jugador4.getNick();
            dinero4 = jugador4.getDinero();
            tux4 = jugador4.getTux();
        }

        //cargar en el modelo las posiciones
        model.addAttribute("jugador1", pos1);
        model.addAttribute("jugador2", pos2);
        model.addAttribute("jugador3", pos3);
        model.addAttribute("jugador4", pos4);
        //cargar en modelo nombres
        model.addAttribute("nombre1", nick1);
        model.addAttribute("nombre2", nick2);
        model.addAttribute("nombre3", nick3);
        model.addAttribute("nombre4", nick4);
        //cargar en modelo dinero
        model.addAttribute("dinero1", dinero1);
        model.addAttribute("dinero2", dinero2);
        model.addAttribute("dinero3", dinero3);
        model.addAttribute("dinero4", dinero4);

        model.addAttribute("tux1", tux1);
        model.addAttribute("tux2", tux2);
        model.addAttribute("tux3", tux3);
        model.addAttribute("tux4", tux4);
        //cargar en modelo el turno
        Jugador jugTurno = jugadorServicio.buscar(partida.getTurno());
        String turno = jugTurno.getNick();
        model.addAttribute("turno", turno);
        return "/tablero2";
    }

    private boolean comprobarQueEsTurno(int numJugador, Partida partida) {
        if (numJugador == 1) {
            if (partida.getTurno().equals(partida.getJugador1())) {
                return true;
            }
        } else if (numJugador == 2) {
            if (partida.getTurno().equals(partida.getJugador2())) {
                return true;
            }
        } else if (numJugador == 3) {
            if (partida.getTurno().equals(partida.getJugador3())) {
                return true;
            }
        } else if (numJugador == 4) {
            if (partida.getTurno().equals(partida.getJugador4())) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/datosDeJuego")
    public @ResponseBody
    String datosDeJuego(HttpSession sesion) {
        Jugador jugador = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
        Partida partida = partidaServicio.buscar(jugador.getPartida());

        int cantidadJugadores = 0;

        int posicion1 = 0;
        int dinero1 = 0;
        int posicion2 = 0;
        int dinero2 = 0;
        int posicion3 = 0;
        int dinero3 = 0;
        int posicion4 = 0;
        int dinero4 = 0;
        if (partida.getJugador1() == null) {
            posicion1 = 0;
            dinero1 = 0;
        } else {
            cantidadJugadores++;
            posicion1 = jugadorServicio.buscar(partida.getJugador1()).getPosicion();
            dinero1 = jugadorServicio.buscar(partida.getJugador1()).getDinero();
        }
        if (partida.getJugador2() == null) {
            posicion2 = 0;
            dinero2 = 0;
        } else {
            cantidadJugadores++;
            posicion2 = jugadorServicio.buscar(partida.getJugador2()).getPosicion();
            dinero2 = jugadorServicio.buscar(partida.getJugador2()).getDinero();
        }
        if (partida.getJugador3() == null) {
            posicion3 = 0;
            dinero3 = 0;
        } else {
            cantidadJugadores++;
            posicion3 = jugadorServicio.buscar(partida.getJugador3()).getPosicion();
            dinero3 = jugadorServicio.buscar(partida.getJugador3()).getDinero();
        }
        if (partida.getJugador4() == null) {
            posicion4 = 0;
            dinero4 = 0;
        } else {
            cantidadJugadores++;
            posicion4 = jugadorServicio.buscar(partida.getJugador4()).getPosicion();
            dinero4 = jugadorServicio.buscar(partida.getJugador4()).getDinero();
        }
        Jugador jugTurno = jugadorServicio.buscar(partida.getTurno());
        String turno = jugTurno.getNick();


        return this.datosDeJuegoJson(posicion1, posicion2, posicion3, posicion4, dinero1, dinero2, dinero3, dinero4, turno, cantidadJugadores, partida.getBote()).toString();
    }

    private JSONObject datosDeJuegoJson(int posicion1, int posicion2, int posicion3, int posicion4,
            int dinero1, int dinero2, int dinero3, int dinero4, String turno, int cantidadJugadores, int botePartida) {
        JSONObject json = new JSONObject();
        try {

            json.put("posicion1", posicion1);
            json.put("dinero1", dinero1);
            json.put("posicion2", posicion2);
            json.put("dinero2", dinero2);
            json.put("posicion3", posicion3);
            json.put("dinero3", dinero3);
            json.put("posicion4", posicion4);
            json.put("dinero4", dinero4);
            json.put("turno", turno);
            json.put("cantidadJugadores", cantidadJugadores);
            json.put("bote", botePartida);

        } catch (JSONException ex) {
        }
        return json;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saberSiYaPuedeJugar")
    public @ResponseBody
    String saberSiYaPuedeJugar(HttpSession sesion) {

        if (usuarioServicio.getCurrentUser().getJugador() == null) {

            return "/perfil";
        } else {

            Jugador jugador = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
            if (jugador.getEstoyJugando() == true) {

                boolean todoListo = true;
                return this.saberSiYaPuedeJugarJson(todoListo).toString();
            }

            boolean todoListo = false;
            return this.saberSiYaPuedeJugarJson(todoListo).toString();
        }


    }

    private JSONObject saberSiYaPuedeJugarJson(boolean todoListo) {
        JSONObject json = new JSONObject();
        try {

            json.put("todoListo", todoListo);
            if (todoListo == false) {
                Partida partida = partidaServicio.buscar(jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()).getPartida());
                int jugadoresQueFaltan = 4 - juegoControlador.jugadorQueQuedan(partida);

                String jugadoresRestantes = ", faltan " + jugadoresQueFaltan + " jugadores";
                json.put("jugadoresRestantes", jugadoresRestantes);
            }


        } catch (JSONException ex) {
        }
        return json;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getVenderPropiedadesURL")
    public String getIrVenderPropiedadesURL() {

        return "/venderPropiedades";
    }

    private String casillasVenderJson(List casillas) {
        String salida = "[";
        try {
            for (int i = 0; i < casillas.size(); i++) {
                salida += this.datosCasilla((Casilla) casillas.get(i));
                if (i < casillas.size() - 1) {
                    salida += ",";
                }
            }
        } catch (Exception ex) {
        }
        salida += "]";
        return salida;
    }

    private JSONObject datosCasilla(Casilla casilla) {
        JSONObject json = new JSONObject();
        int i;
        try {
            json.put("nombre", casilla.getNombre());
            Calle micalle = calleServicio.buscar(casilla.getTipoCasilla());
            json.put("precioVenta", micalle.getPrecio() * 0.7);
            json.put("numero", casilla.getNumeroCasilla());
        } catch (JSONException ex) {
        }

        return json;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/casillasConPosibilidadVenta")
    public @ResponseBody
    String casillasConPosibilidadVenta() throws JSONException {

        Jugador jugador = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
        List<Key> casillas = jugador.getCalles();
        List<Casilla> casillasDelJugador = new ArrayList<Casilla>();

        for (Key c : casillas) {
            Casilla miCasilla = casillaServicio.buscar(c);
            casillasDelJugador.add(miCasilla);
        }
        if (casillasDelJugador.size() > 0) {


            return casillasVenderJson(casillasDelJugador).toString();
        }
        return "No tienes casillas";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/venderCasillas")
    public @ResponseBody
    String venderCasillas(@RequestParam("calles") String calles) throws JSONException {
        System.out.println("calles" + calles);
        Jugador jugador = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
        StringTokenizer tokens = new StringTokenizer(calles, ",");
        while (tokens.hasMoreTokens()) {
            Casilla casilla = casillaServicio.buscarPorNumero(Integer.valueOf(tokens.nextToken().toString()));
            Calle calle = calleServicio.buscar(casilla.getTipoCasilla());
            jugador.getCalles().remove(casilla.getIdCasilla());
            jugador.setDinero((int) (jugador.getDinero() + calle.getPrecio() * 0.7));
            jugadorServicio.actualizar(jugador);
        }

        return "Vendidas";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verPropiedadesOtrosUser")
    public @ResponseBody
    String verPropiedadesOtrosUser(HttpSession sesion, @RequestParam("numJugador") int numJugador) {
        Jugador jugador = jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
        Partida partida = partidaServicio.buscar(jugador.getPartida());
        if (numJugador == 1) {
            jugador = jugadorServicio.buscar(partida.getJugador1());
        } else if (numJugador == 2) {
            jugador = jugadorServicio.buscar(partida.getJugador2());
        } else if (numJugador == 3) {
            jugador = jugadorServicio.buscar(partida.getJugador3());
        } else {
            jugador = jugadorServicio.buscar(partida.getJugador4());
        }

        List<Key> casillas = jugador.getCalles();
        List<Casilla> casillasDelJugador = new ArrayList<Casilla>();

        for (Key c : casillas) {
            Casilla miCasilla = casillaServicio.buscar(c);
            casillasDelJugador.add(miCasilla);
        }
        if (casillasDelJugador.size() > 0) {


            return this.casillasVenderJson(casillasDelJugador).toString();
        }
        return "No tiene casillas";



    }

    @RequestMapping(method = RequestMethod.GET, value = "/jugadoresDisponibles")
    public @ResponseBody
    String jugadoresDisponibles(HttpSession sesion) {
        List<Usuario> usuarios = usuarioServicio.conseguirUsuarios();
        List<String> disponibles = new ArrayList<String>();
        for (Usuario usuario : usuarios) {
            if (usuario.getJugador() == null) {
                disponibles.add(usuario.getNick());
            }
        };
        return this.jugadoresDisponiblesJson(disponibles).toString();
    }

    private JSONObject jugadoresDisponiblesJson(List<String> disponibles) {
        JSONObject json = new JSONObject();
        try {

            json.put("disponibles", disponibles);


        } catch (JSONException ex) {
        }
        return json;
    }

    @RequestMapping(value = "/adCrearPartida", method = RequestMethod.POST)
    public String doAdCrearPartida(@RequestParam("jug1") String jugador1,
    Model model,
    @RequestParam("jug2") String jugador2,
    @RequestParam("jug3") String jugador3,
    @RequestParam("jug4") String jugador4,
    @RequestParam("avatar1") Integer avatar1,
    @RequestParam("avatar2") Integer avatar2,
    @RequestParam("avatar3") Integer avatar3,
    @RequestParam("avatar4") Integer avatar4,
    @RequestParam("dinero1") Integer dinero1,
    @RequestParam("dinero2") Integer dinero2,
    @RequestParam("dinero3") Integer dinero3,
    @RequestParam("dinero4") Integer dinero4,
    @RequestParam("pos1") Integer pos1,
    @RequestParam("pos2") Integer pos2,
    @RequestParam("pos3") Integer pos3,
    @RequestParam("pos4") Integer pos4,
    @RequestParam("bote") Integer bote) {
        boolean sePuedeCrear=true;
        
        if(usuarioServicio.buscar(jugador1).getJugador()!=null){
            sePuedeCrear=false;
        }else if(usuarioServicio.buscar(jugador2).getJugador()!=null){
            sePuedeCrear=false;
        }else if(usuarioServicio.buscar(jugador3).getJugador()!=null){
            sePuedeCrear=false;
        }else if(usuarioServicio.buscar(jugador4).getJugador()!=null){
            sePuedeCrear=false;
        }
        
        List<String>nombres=new ArrayList <String>();
        nombres.add(jugador2);
        nombres.add(jugador3);
        nombres.add(jugador4);
        if(nombres.contains(jugador1)){
            sePuedeCrear=false;
        }
        
        nombres=new ArrayList <String>();
        nombres.add(jugador1);
        nombres.add(jugador3);
        nombres.add(jugador4);
        if(nombres.contains(jugador2)){
            sePuedeCrear=false;
        }
        
        nombres=new ArrayList <String>();
        nombres.add(jugador1);
        nombres.add(jugador2);
        nombres.add(jugador4);
        if(nombres.contains(jugador3)){
            sePuedeCrear=false;
        }
        
        nombres=new ArrayList <String>();
        nombres.add(jugador1);
        nombres.add(jugador2);
        nombres.add(jugador3);
        if(nombres.contains(jugador4)){
            sePuedeCrear=false;
        }
        
        List<Integer>tuxex=new ArrayList <Integer>();
        tuxex.add(avatar2);
        tuxex.add(avatar3);
        tuxex.add(avatar4);
        if(tuxex.contains(avatar1)){
            sePuedeCrear=false;
        }
        
        tuxex=new ArrayList <Integer>();
        tuxex.add(avatar1);
        tuxex.add(avatar3);
        tuxex.add(avatar4);
        if(tuxex.contains(avatar2)){
            sePuedeCrear=false;
        }
        
        tuxex=new ArrayList <Integer>();
        tuxex.add(avatar1);
        tuxex.add(avatar2);
        tuxex.add(avatar4);
        if(tuxex.contains(avatar3)){
            sePuedeCrear=false;
        }
        
        tuxex=new ArrayList <Integer>();
        tuxex.add(avatar1);
        tuxex.add(avatar2);
        tuxex.add(avatar3);
        if(tuxex.contains(avatar4)){
            sePuedeCrear=false;
        }
        
        
        if(sePuedeCrear){
        List<String> arrayTux = new ArrayList(15);
        int iTux;
        String cadena;
        for (iTux = 0; iTux < 15; iTux++) {
            cadena = "Estilos/tux/tux" + iTux + ".png";
            arrayTux.add(iTux, cadena);
        }

        Partida partida = partidaServicio.empezarPartida2();

        Usuario usuario = usuarioServicio.buscar(jugador1);
        Jugador jugador = new Jugador();
        jugadorServicio.crear(jugador);
        jugador.setNick(jugador1);
        jugador.setDinero(dinero1);
        jugador.setTux(arrayTux.get(avatar1));
        jugador.setEstoyEnCola(false);
        jugador.setEstoyJugando(true);
        jugador.setPosicion(pos1);
        jugador.setEstoyEnCarcel(false);
        jugador.setPartida(partida.getIdpartida());
        partida.setJugador1(jugador.getClaveJugador());
        partida.setTurno(jugador.getClaveJugador());
        jugadorServicio.actualizar(jugador);
        usuario.setJugador(jugador.getClaveJugador());
        usuarioServicio.actualizar(usuario);

        usuario = usuarioServicio.buscar(jugador2);
        jugador = new Jugador();
        jugadorServicio.crear(jugador);
        jugador.setNick(jugador2);
        jugador.setDinero(dinero2);
        jugador.setTux(arrayTux.get(avatar2));
        jugador.setEstoyEnCola(false);
        jugador.setEstoyJugando(true);
        jugador.setPosicion(pos2);
        jugador.setPartida(partida.getIdpartida());
        partida.setJugador2(jugador.getClaveJugador());
        jugador.setEstoyEnCarcel(false);
        jugadorServicio.actualizar(jugador);
        usuario.setJugador(jugador.getClaveJugador());
        usuarioServicio.actualizar(usuario);

        usuario = usuarioServicio.buscar(jugador3);
        jugador = new Jugador();
        jugadorServicio.crear(jugador);
        jugador.setNick(jugador3);
        jugador.setDinero(dinero3);
        jugador.setTux(arrayTux.get(avatar3));
        jugador.setEstoyEnCola(false);
        jugador.setEstoyJugando(true);
        jugador.setPosicion(pos3);
        jugador.setPartida(partida.getIdpartida());
        partida.setJugador3(jugador.getClaveJugador());
        jugador.setEstoyEnCarcel(false);
        jugadorServicio.actualizar(jugador);
        usuario.setJugador(jugador.getClaveJugador());
        usuarioServicio.actualizar(usuario);

        usuario = usuarioServicio.buscar(jugador4);
        jugador = new Jugador();
        jugadorServicio.crear(jugador);
        jugador.setNick(jugador4);
        jugador.setDinero(dinero4);
        jugador.setTux(arrayTux.get(avatar4));
        jugador.setEstoyEnCola(false);
        jugador.setEstoyJugando(true);
        jugador.setPosicion(pos4);
        jugador.setPartida(partida.getIdpartida());
        partida.setJugador4(jugador.getClaveJugador());
        jugador.setEstoyEnCarcel(false);
        jugadorServicio.actualizar(jugador);
        usuario.setJugador(jugador.getClaveJugador());
        usuarioServicio.actualizar(usuario);

        partida.setCompleta(true);
        partida.setHaTirado(false);
        partida.setBote(bote);
        partida.setFechaTurno(System.currentTimeMillis() + 60000);
        partidaServicio.actualizar(partida);
        }
        
        return "/perfil";
    }
}