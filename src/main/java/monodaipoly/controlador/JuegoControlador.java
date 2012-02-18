 package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.com.google.common.base.Log2;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Lob;

import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Calle;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import monodaipoly.servicio.CalleServicio;
import monodaipoly.servicio.CasillaServicio;
import monodaipoly.servicio.JugadorServicio;
import monodaipoly.servicio.PartidaServicio;
import monodaipoly.servicio.UsuarioServicio;
import org.apache.commons.logging.Log;
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
    private CalleServicio calleServicio;
    //private Logger logger;


    /*@Autowired
    @Required
    public void setLogger(Logger logger){
        this.logger = logger;
    }*/

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio=usuarioServicio;
    }

    @Autowired
    @Required
    public void setCalleServicio(CalleServicio calleServicio){
        this.calleServicio=calleServicio;
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
    
    
     @RequestMapping(value = "/cambiarTurnoManual", method = RequestMethod.GET)
    private @ResponseBody String cambiarTurnoManualmente(HttpSession sesion,
            @RequestParam("jugQueTira") String  jugQueTira){
         
         Jugador jugador = (Jugador)sesion.getAttribute("jugador");
         if(jugador.getPartida()!=null && jugador.getEstoyJugando()==true){
            Partida partida=partidaServicio.buscar(jugador.getPartida());
            if(partida!=null){
             this.cambiarTurno(partida);
             return "turno de "+ partida.getTurno();
            }
         }
         
         
         return "";
         
     }



    public void cambiarTurno(Partida partida) {
        String cambiadoTurno="no";
        //System.out.println("Llega a cambiar turno");
        if(partida.getTurno().equals(partida.getJugador1())){
           //System.out.println("Entra");
            cambiadoTurno="si";
           partida.setTurno(partida.getJugador2());             
        }else{
            if(partida.getTurno().equals(partida.getJugador2())){
                partida.setTurno(partida.getJugador3());
                cambiadoTurno="si";
            }else{
                 if(partida.getTurno().equals(partida.getJugador3())){
                       partida.setTurno(partida.getJugador4());
                       cambiadoTurno="si";
                }else{
                     partida.setTurno(partida.getJugador1());
                     cambiadoTurno="si";
                 }
            }
        }

        //debemos hacer un metodo que envie algo a tablero para indicir que se a cambiado el turno y de quien es

        partida.setFechaTurno(System.currentTimeMillis()+60000);
        partida.setHaTirado(false);
        partidaServicio.actualizar(partida);
    }


    @RequestMapping(value = "/cambiarTurnoTimer", method = RequestMethod.GET)
    private String turno(){
        List<Partida> partidasCompletas=partidaServicio.partidaCompleta();
        if(partidasCompletas.size()>0){
            Partida partida=new Partida();
            int i;
            for(i=0;i<=partidasCompletas.size()-1;i++){
                partida=partidasCompletas.get(i);
                //System.out.println(partida.getTurno());
                if(System.currentTimeMillis()>=partida.getFechaTurno()){
                    if(partida.getHaTirado()==false){
                        
                        Jugador jugador=new Jugador();
                        //primero sacamos el jugador al que le tocaria tirar
                        //tirar el dado por el que es su turno...y no dejarle comprar
                        if(partida.getTurno().equals(partida.getJugador1())){
                            jugador=jugadorServicio.buscar(partida.getJugador1());
                            System.out.println("Se le va a saltar el turno al jug 1: "+jugador.getNick());
                        }
                        if(partida.getTurno().equals(partida.getJugador2())){
                            jugador=jugadorServicio.buscar(partida.getJugador2());
                            System.out.println("Se le va a saltar el turno al jug 2: "+jugador.getNick());
                        }
                        if(partida.getTurno().equals(partida.getJugador3())){
                            jugador=jugadorServicio.buscar(partida.getJugador3());
                            System.out.println("Se le va a saltar el turno al jug 3: "+jugador.getNick());
                        }if(partida.getTurno().equals(partida.getJugador4())){
                            jugador=jugadorServicio.buscar(partida.getJugador4());
                            System.out.println("Se le va a saltar el turno al jug 4: "+jugador.getNick());
                        }
                        //ahora tiramos por el...
                        int dado = (int)(6.0 * Math.random()) + 1;

                        if(jugador.getPosicion()+dado>35){
                            int posAnt=jugador.getPosicion();
                            int dif=36-posAnt;
                            jugador.setPosicion(0+dado-dif);
                            jugador.setDinero(jugador.getDinero()+200);
                        }else{
                            jugador.setPosicion(jugador.getPosicion()+dado);
                        }

                    jugadorServicio.actualizar(jugador);
                    //this.comprobarCalle(jugador.getClaveJugador());
                    this.comprobarCasillaQueHasCaido(jugador);
                    //System.out.println("this.comprobarCasillaQueHasCaido(jugador):  "+ this.comprobarCasillaQueHasCaido(jugador));

                    }
                    cambiarTurno(partida);
                }
                //System.out.println(partida.getTurno());
            }
        }else{
            System.out.println("No ai partidas llenas");
        }
        return "/index";
    }



    /*me puede devolver o null(pq es de tipo estacion o suerte...)
    o el literal "tuya" pq la calle es tuya y no puedes ni comprarla ni vas a pagar
    o un objeto de tipo json...dependiendo de si no es de nadie...que te manda un tipo="noMulta"
    o si ya es de alguien, en el objeto json, te manda un tipo="multa"
     */


    
    @RequestMapping(value = "/comprobarCalle", method = RequestMethod.GET)
    private @ResponseBody String comprobarCalle(HttpSession sesion,
            @RequestParam("jugQueTira") String  jugQueTira){

         Jugador jugador = (Jugador)sesion.getAttribute("jugador");
         return this.comprobarCasillaQueHasCaido(jugador);
 
    }




    private JSONObject multaJson(int multa,int numJugador,String tipo){
        JSONObject json=new JSONObject();
        try{

            json.put("multa", multa);
            json.put("numJugador",numJugador);
            json.put("tipo", tipo);

        }catch (JSONException ex){

        }
        return json;
    }



    private JSONObject opcionCompraJson(int precio,String nombre,String tipo){
        JSONObject json=new JSONObject();
        try{

            json.put("precio", precio);
            json.put("nombre",nombre);
            json.put("tipo", tipo);

        }catch (JSONException ex){

        }
        return json;
    }


        private int numDelJugador(Jugador jugador,Partida partida){
              if(partida.getJugador1().compareTo(jugador.getClaveJugador())==0){
                    return 1;
                }
                if(partida.getJugador2().compareTo(jugador.getClaveJugador())==0){
                    return 2;
                }
                if(partida.getJugador3().compareTo(jugador.getClaveJugador())==0){
                    return 3;
                }else{
                    return 4;
                }
        }



        @RequestMapping(value = "/comprarCalle", method = RequestMethod.GET)
        private @ResponseBody String comprarCalle(HttpSession sesion,@RequestParam("jugQueTira") String jugQueTira){

            Jugador jugador = (Jugador)sesion.getAttribute("jugador");
            Partida partida =partidaServicio.buscar(jugador.getPartida());

            Casilla casilla=casillaServicio.buscarPorNumero(jugador.getPosicion());
            Calle calle=calleServicio.buscar(casilla.getTipoCasilla());


            if(jugador.getDinero()>=calle.getPrecio()){
                jugador.setDinero(jugador.getDinero()-calle.getPrecio());
                List<Key> casillasDelJugador= jugador.getCalles();
                casillasDelJugador.add(casilla.getIdCasilla());
                jugador.setCalles(casillasDelJugador);
                jugadorServicio.actualizar(jugador);
                return "calle comprada";
            }
            return "insuficiente_dinero";
        }


        //va a ser llamado cuando el jugAQuienPagas !=jugQueJuega y la casilla.getTipoCasilla()!=null
        //jugAQuienPagas es el jugador dueño de la casilla!!!
        //el jugQueJuega es el nº del jug que tiene que pagar la multa
        private void pagarMulta(Jugador jugador, int jugAQuienPagas,Partida partida,Calle calle,Casilla casilla,int jugQueJuega){
            
            Jugador jugadorPagas=new Jugador();
            if(jugAQuienPagas==1 && jugQueJuega!=1){
                //comprobamos si el jugador 1 es qien tiene la casilla en la q has caido
                jugadorPagas= jugadorServicio.buscar(partida.getJugador1());
            }if(jugAQuienPagas==2 && jugQueJuega!=2){
                //comprobamos si el jugador 2 es qien tiene la casilla en la q has caido
                jugadorPagas= jugadorServicio.buscar(partida.getJugador2());
            }if(jugAQuienPagas==3 && jugQueJuega!=3){
                //comprobamos si el jugador 3 es qien tiene la casilla en la q has caido
                jugadorPagas= jugadorServicio.buscar(partida.getJugador3());
            }if(jugAQuienPagas==4 && jugQueJuega!=4){
                //comprobamos si el jugador 4 es qien tiene la casilla en la q has caido
                jugadorPagas= jugadorServicio.buscar(partida.getJugador4());
            }
            //restamos el dinero de la multa al jugador
            jugador.setDinero(jugador.getDinero()-calle.getMulta());
            //se la sumamos al jugador a Quien hay que pagarselo
            //jugadorServicio.buscar(partida.getJugador1()).setDinero(jugadorServicio.buscar(partida.getJugador1()).getDinero()+multa);
            jugadorPagas.setDinero(jugadorPagas.getDinero()+calle.getMulta());
            jugadorServicio.actualizar(jugadorPagas);
            jugadorServicio.actualizar(jugador);
            System.out.println("hay que pagarsela al jugador nº "+jugAQuienPagas);
            System.out.println("multa es de : "+calle.getMulta());

        }


        /*Este metodo lo que hace es le pasas el jugador y saca la posicion en la que ha caido...
         * de ahí saca la casilla que es esa posicion
         * si casilla.getTipo()!=null
         * si es asi
         * tb saca la calle,el numero de jugador que eres...
         * si alguien tiene esa casilla....y de quien es...
         * si el propietario de la casilla y el jugador q ha caido en ella son distintos llama pagarMulta
         * devuelve "tuya" si la casilla era tuya...
         * devuelve "multa" si has tenido que pagar multa...
         * y devuelve "tipo nulo" si la casilla es nula
         */
        private String comprobarCasillaQueHasCaido(Jugador jugador){
            int numeroDeJug=0;
            int jugDueñoCasilla=0;
            Partida partida =partidaServicio.buscar(jugador.getPartida());
            numeroDeJug=this.numDelJugador(jugador, partida);
            //comprobamos que la casilla no sea una de las esquinas...
            if(jugador.getPosicion()!=0 && jugador.getPosicion()!=9 && jugador.getPosicion()!=18 && jugador.getPosicion()!=27){
                Casilla casilla=casillaServicio.buscarPorNumero(jugador.getPosicion());
                if(casilla.getTipoCasilla()==null){
                    return null;
                }   
                if(casilla.getTipoCasilla()!=null){
                        Calle calle=calleServicio.buscar(casilla.getTipoCasilla());
                        Logger.getAnonymousLogger().info("calle: "+ calle.getIdCalle());
                        Logger.getLogger(JuegoControlador.class.getName()).info("calle: "+ calle.getIdCalle());

                      if (jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla()) ){
                          jugDueñoCasilla=this.jugadorDueñoDeLaCasilla(casilla, partida);
                          if(jugDueñoCasilla!=numeroDeJug){
                              
                              //ahora llamamos al metodo que estaba haciendo...
                              this.pagarMulta(jugador, jugDueñoCasilla , partida, calle, casilla,numeroDeJug );
                              return this.multaJson(calle.getMulta(), jugDueñoCasilla, "multa").toString();
                          }if(jugDueñoCasilla!=numeroDeJug){
                              return "tuya";
                          }
                          
                      }


                        System.out.println("Calles del jug1= "+ jugadorServicio.buscar(partida.getJugador1()).getCalles().toString());


                        System.out.println("Calles del jug2= "+ jugadorServicio.buscar(partida.getJugador2()).getCalles().toString());


                        System.out.println("Calles del jug3= "+ jugadorServicio.buscar(partida.getJugador3()).getCalles().toString());


                        System.out.println("Calles del jug4= "+ jugadorServicio.buscar(partida.getJugador4()).getCalles().toString());


                      return this.opcionCompraJson(calle.getPrecio(), casilla.getNombre(), "noMulta").toString();
                }else{
                    //esto es pq has caido en una calle de suerte,hidroelectrica o estacion
                    return null;
                }

            }
            //esto es porque has caido en una calle de las esquinas
            return null;

        }
        
        
        private int jugadorDueñoDeLaCasilla(Casilla casilla,Partida partida){
            if(jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla())){
                return 1;
            }if(jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla())){
                return 2;
            }if(jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla())){
                return 3;
            }
            return 4;
        }


       
}

