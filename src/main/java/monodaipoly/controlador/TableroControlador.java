 package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
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
public class TableroControlador {

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
    public String volverPerfil(Model model,HttpSession sesion) {
            /*Usuario usuario = (Usuario)sesion.getAttribute("usuario");
            Jugador jugador=this.jugadorServicio.buscar(usuario.getJugador());
            this.jugadorServicio.borrar(jugador);
            usuario.setJugador(null);
            usuarioServicio.actualizar(usuario);
            model.addAttribute("usuario", usuarioServicio.getCurrentUser());*/
        return "/perfilPrueba";
    }



        @RequestMapping(method=RequestMethod.GET, value="/moverJugador")
        public @ResponseBody String dado(HttpSession sesion,@RequestParam("jugQueTira") String jugQueTira){
            //Usuario usuario = (Usuario)sesion.getAttribute("usuario");
            //Jugador jugador=jugadorServicio.buscar(usuario.getJugador());
            Jugador jugador = (Jugador)sesion.getAttribute("jugador");
            Partida partida =partidaServicio.buscar(jugador.getPartida());
          
            int numJugador=0;
            if(jugadorServicio.buscar(partida.getJugador1()).getNick().compareTo(jugQueTira)==0){
                numJugador=1;
            }else if(jugadorServicio.buscar(partida.getJugador2()).getNick().compareTo(jugQueTira)==0){
                numJugador=2;
            }else if(jugadorServicio.buscar(partida.getJugador3()).getNick().compareTo(jugQueTira)==0){
                numJugador=3;
            }else if(jugadorServicio.buscar(partida.getJugador4()).getNick().compareTo(jugQueTira)==0){
                numJugador=4;
            }
            if(comprobarQueEsTurno(numJugador,partida)&&partida.getHaTirado()==false){



                int dado = (int)(6.0 * Math.random()) + 1;
                //int dado=((int)Math.random()*6);
                if(jugador.getPosicion()+dado>35){
                    int posAnt=jugador.getPosicion();
                    int dif=36-posAnt;
                    jugador.setPosicion(0+dado-dif);
                    jugador.setDinero(jugador.getDinero()+200);
                }else{
                    jugador.setPosicion(jugador.getPosicion()+dado);
                }

                jugadorServicio.actualizar(jugador);
                partida.setHaTirado(true);
                partidaServicio.actualizar(partida);
                return this.dadoJson(jugador.getPosicion(),dado,numJugador).toString();
            }else{
                return "no";
            }

    }

        private JSONObject dadoJson(int posicion,int dado,int numJugador){
        JSONObject json=new JSONObject();
        try{
         
            json.put("nuevaPosicion",posicion);
            json.put("dado", dado);
            json.put("numJugador",numJugador);

        }catch (JSONException ex){

        }
        return json;
    }

    /*@RequestMapping(value = "/prepararPartida", method = RequestMethod.GET)
    public String prepararPartida(Model model,HttpSession sesion) {
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        int posicion;
        String enCola="en cola";
        if(usuarioServicio.getCurrentUser().getJugador()!=null){
            //System.out.println("1 tiene jugador");
            if(jugadorServicio.comprobarJugadorConPartida(usuario.getJugador())!=null){
                //System.out.println(" 2 tiene partida este jugador");
                //System.out.println(" estoy jugando:"+jugadorServicio.buscar(usuario.getJugador()).getEstoyJugando() );
                if(jugadorServicio.buscar(usuario.getJugador()).getEstoyJugando()==null){
                    //System.out.println("4 return");
                    model.addAttribute("enCola",enCola );
                    return "/perfilPrueba";//else return perfil y mostrar cola
                }else if(jugadorServicio.buscar(usuario.getJugador()).getEstoyJugando()==true){//if esta jugando...
                    //System.out.println("3 cargar en modelo el jugador");
                    model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                    return "redirect:comenzarPartida";//return ... el metodo que falta que carga el modelo
                }
                    //System.out.println("5 return");
                
                model.addAttribute("enCola",enCola );

                    return "/perfilPrueba";//else return perfil y mostrar cola
                
            
                
            }
        }else if(usuarioServicio.getCurrentUser().getJugador()==null){
            Jugador jugador=new Jugador();
            jugadorServicio.crear(jugador);
            jugador.setNick(usuario.getNick());
            jugadorServicio.actualizar(jugador);
            usuario.setJugador(jugador.getClaveJugador());
            usuarioServicio.actualizar(usuario);
            Partida partida=partidaServicio.comprobarPartidaLibre();
            if(partida!=null){
                posicion=partidaServicio.comprobarHueco(partida.getIdpartida());
                jugador.setPartida(partida.getIdpartida());
                jugadorServicio.actualizar(jugador);
                if(posicion==1){
                    partida.setJugador1(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion ==2){
                    partida.setJugador2(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion==3){
                    partida.setJugador3(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion==4){
                    partida.setJugador4(jugador.getClaveJugador());
                    //aqui voy a poner a los 4 jugadores de esta partida el atributo estoy jugando
                    //a true y el dinero  xq ya hay 4 jugadores y puede empezar el juego
                    Key keyPlayer=partida.getJugador1();
                    Jugador player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    jugadorServicio.actualizar(player);
                    keyPlayer=partida.getJugador2();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    jugadorServicio.actualizar(player);
                    keyPlayer=partida.getJugador3();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    jugadorServicio.actualizar(player);
                    keyPlayer=partida.getJugador4();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
                    
                    
                    partida.setFechaTurno(System.currentTimeMillis()+60000);
                    jugadorServicio.actualizar(player);
                    partidaServicio.actualizar(partida);
                    model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                    //return ... el metodo que falta que carga el modelo
                    
                    return "redirect:comenzarPartida";

                }
                
            }else{
                Partida partidaNueva=partidaServicio.empezarPartida2();
                partidaNueva.setJugador1(jugador.getClaveJugador());
                partidaNueva.setTurno(jugador.getClaveJugador());
                jugador.setPartida(partidaNueva.getIdpartida());
                jugadorServicio.actualizar(jugador);
                partidaServicio.actualizar(partidaNueva);
                //return perfil y mostrar cola

            }



        }
        model.addAttribute("enCola",enCola );
            return "/perfilPrueba";
    }*/





    //metodo refactorizado .... 
    @RequestMapping(value = "/prepararPartida2", method = RequestMethod.GET)
    public String prepararPartida2(Model model,HttpSession sesion) {
        int posicion;
        String enCola="en cola";
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        if(usuarioServicio.getCurrentUser().getJugador()==null){
            Jugador jugador=new Jugador();
            jugadorServicio.crear(jugador);
            jugador.setNick(usuario.getNick());
            jugadorServicio.actualizar(jugador);
            usuario.setJugador(jugador.getClaveJugador());
            usuarioServicio.actualizar(usuario);
            Partida partida=partidaServicio.comprobarPartidaLibre();
            if(partida==null){
                Partida partidaNueva=partidaServicio.empezarPartida2();
                partidaNueva.setJugador1(jugador.getClaveJugador());
                partidaNueva.setTurno(jugador.getClaveJugador());
                jugador.setPartida(partidaNueva.getIdpartida());
                jugadorServicio.actualizar(jugador);
                partidaServicio.actualizar(partidaNueva);
                //return perfil y mostrar cola
                model.addAttribute("enCola",enCola );
                return "/perfilPrueba";
            }else{
                posicion=partidaServicio.comprobarHueco(partida.getIdpartida());
                jugador.setPartida(partida.getIdpartida());
                jugadorServicio.actualizar(jugador);
                if(posicion==1){
                    partida.setJugador1(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion ==2){
                    partida.setJugador2(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion==3){
                    partida.setJugador3(jugador.getClaveJugador());
                    partidaServicio.actualizar(partida);
                }else if(posicion==4){
                    partida.setJugador4(jugador.getClaveJugador());
                    //aqui voy a poner a los 4 jugadores de esta partida el atributo estoy jugando
                    //a true y el dinero  xq ya hay 4 jugadores y puede empezar el juego
                    Key keyPlayer=partida.getJugador1();
                    Jugador player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(2000);
//meter todas las calles al jugador 
                    for(Casilla c:casillaServicio.getAll()){
                        List<Key> calles =player.getCalles();
                        calles.add(c.getIdCasilla());
                        player.setCalles(calles);
                        jugadorServicio.actualizar(player);
                    }


                    keyPlayer=partida.getJugador2();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(20);
                    jugadorServicio.actualizar(player);
                    keyPlayer=partida.getJugador3();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(20);
                    jugadorServicio.actualizar(player);
                    keyPlayer=partida.getJugador4();
                    player=jugadorServicio.buscar(keyPlayer);
                    player.setEstoyJugando(true);
                    player.setDinero(20);


                    partida.setFechaTurno(System.currentTimeMillis()+60000);
                    partida.setCompleta(true);
                    jugadorServicio.actualizar(player);
                    partidaServicio.actualizar(partida);
                    model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                    //return ... el metodo que falta que carga el modelo

                    return "redirect:comenzarPartida";

                }
            }
        }else if(usuarioServicio.getCurrentUser().getJugador()!=null){
            //System.out.println("1 tiene jugador");
             //Logger.getLogger(JuegoControlador.class.getName()).info("1.2");
             Jugador jugQueJodiaTodo=jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
             //Logger.getLogger(JuegoControlador.class.getName()).info(jugQueJodiaTodo.getEstoyJugando().toString());
                if(jugQueJodiaTodo.getEstoyJugando()==false){
                    //System.out.println("4 return");
                    //Logger.getLogger(JuegoControlador.class.getName()).info("3");
                    model.addAttribute("enCola",enCola );
                    return "/perfilPrueba";//else return perfil y mostrar cola
                }else if(jugQueJodiaTodo.getEstoyJugando()==true){//if esta jugando...
                    //System.out.println("3 cargar en modelo el jugador");
                    //Logger.getLogger(JuegoControlador.class.getName()).info("4");
                    model.addAttribute("jugador", jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador()));
                    return "redirect:comenzarPartida";//return ... el metodo que falta que carga el modelo
                }
                   //System.out.println("5 return");
                   //Logger.getLogger(JuegoControlador.class.getName()).info("5");
                   model.addAttribute("enCola",enCola );
                   return "/perfilPrueba";//else return perfil y mostrar cola



            
        }





        model.addAttribute("enCola",enCola );
        return "/perfilPrueba";
    }






















































    @RequestMapping(value = "/comenzarPartida", method = RequestMethod.GET)
    public String comenzarPartida(Model model,HttpSession sesion) {
       Jugador jugador  = (Jugador)sesion.getAttribute("jugador");
       Partida partida=partidaServicio.buscar(jugador.getPartida());
       //cargar en modelo casillas
       List<Casilla>casillas=casillaServicio.getAll();
       model.addAttribute("casillas",casillas);
       //cargar en modelo posiciones
       Jugador jugador1=jugadorServicio.buscar(partida.getJugador1());
       model.addAttribute("jugador1",jugador1.getPosicion());
       Jugador jugador2=jugadorServicio.buscar(partida.getJugador2());
       model.addAttribute("jugador2",jugador2.getPosicion());
       Jugador jugador3=jugadorServicio.buscar(partida.getJugador3());
       model.addAttribute("jugador3",jugador3.getPosicion());
       Jugador jugador4=jugadorServicio.buscar(partida.getJugador4());
       model.addAttribute("jugador4",jugador4.getPosicion());
       //cargar en modelo nombres
       model.addAttribute("nombre1",jugador1.getNick());
       model.addAttribute("nombre2",jugador2.getNick());
       model.addAttribute("nombre3",jugador3.getNick());
       model.addAttribute("nombre4",jugador4.getNick());
       //cargar en modelo dinero
       model.addAttribute("dinero1",jugador1.getDinero());
       model.addAttribute("dinero2",jugador2.getDinero());
       model.addAttribute("dinero3",jugador3.getDinero());
       model.addAttribute("dinero4",jugador4.getDinero());
       return "/tablero2";
    }


    private boolean comprobarQueEsTurno(int numJugador, Partida partida){
        if(numJugador==1){
          if(partida.getTurno().equals(partida.getJugador1())){
              return true;
            }
        }else if(numJugador==2){
            if(partida.getTurno().equals(partida.getJugador2())){
              return true;
            }
        }else if(numJugador==3){
            if(partida.getTurno().equals(partida.getJugador3())){
              return true;
            }
        }else if(numJugador==4){
            if(partida.getTurno().equals(partida.getJugador4())){
              return true;
            }
        }
        return false;
    }




}
