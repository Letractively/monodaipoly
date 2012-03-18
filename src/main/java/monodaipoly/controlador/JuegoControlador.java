package monodaipoly.controlador;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.com.google.common.base.Log2;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Lob;

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


@Controller
public class JuegoControlador {
    
    private JugadorServicio jugadorServicio;
    private CasillaServicio casillaServicio;
    private UsuarioServicio usuarioServicio;
    private PartidaServicio partidaServicio;
    private CalleServicio calleServicio;

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

    
    @RequestMapping(value = "/cambiarTurnoManual", method = RequestMethod.GET)
    public @ResponseBody String cambiarTurnoManualmente(
        @RequestParam("jugQueTira") String  jugQueTira){
        Jugador jugador= jugadorServicio.buscar(usuarioServicio.buscar(jugQueTira).getJugador());
        if(jugador.getPartida()!=null && jugador.getEstoyJugando()==true){
                Partida partida=partidaServicio.buscar(jugador.getPartida());
                if(partida!=null){
                    if(this.jugadorQueQuedan(partida)<4){
                        this.cambiarTurnoMenosDe4(partida);
                    }else{
                        System.out.println("vamos a cambiar el turno desde cambiarTurnoManualmente");
                        cambiarTurno(partida);
                    }
                return "turno de "+ partida.getTurno();
            }
        }
         
         
         return "";
         
    }



    public void cambiarTurno(Partida partida) {
        System.out.println("Llega a cambiar turno");
        if(jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true && jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true && jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true && jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true)
             partida.setTurno(partida.getJugador1());
        else{
        if(partida.getTurno().compareTo(partida.getJugador1())==0){
           System.out.println("Entra 1");
           partida.setTurno(partida.getJugador2());
           if(jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true){
                cambiarTurno(partida);
                jugadorServicio.buscar(partida.getJugador2()).setEstoyEnCarcel(false);
            }
        }else{
            if(partida.getTurno().compareTo(partida.getJugador2())==0){
                System.out.println("Entra 2");
                partida.setTurno(partida.getJugador3());
                if(jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true){
                    cambiarTurno(partida);
                    jugadorServicio.buscar(partida.getJugador3()).setEstoyEnCarcel(false);
                }
            }else{
                 if(partida.getTurno().compareTo(partida.getJugador3())==0){
                     System.out.println("Entra 3");
                       partida.setTurno(partida.getJugador4());
                       if(jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true){
                            cambiarTurno(partida);
                            jugadorServicio.buscar(partida.getJugador4()).setEstoyEnCarcel(false);
                        }
                }else{
                     System.out.println("Entra 4");
                     partida.setTurno(partida.getJugador1());
                     if(jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true){
                        cambiarTurno(partida);
                        jugadorServicio.buscar(partida.getJugador1()).setEstoyEnCarcel(false);
                    }
                 }
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
                
                if(System.currentTimeMillis()>=partida.getFechaTurno()){
                    if(partida.getHaTirado()==false){
                        
                        Jugador jugador=new Jugador();
                        //primero sacamos el jugador al que le tocaria tirar
                        //tirar el dado por el que es su turno...y no dejarle comprar
                        if(partida.getJugador1()!=null && partida.getTurno().equals(partida.getJugador1())){
                            jugador=jugadorServicio.buscar(partida.getJugador1());
                            //System.out.println("Se le va a saltar el turno al jug 1: "+jugador.getNick());
                        }
                        if(partida.getJugador2()!=null && partida.getTurno().equals(partida.getJugador2())){
                            jugador=jugadorServicio.buscar(partida.getJugador2());
                            //System.out.println("Se le va a saltar el turno al jug 2: "+jugador.getNick());
                        }
                        if(partida.getJugador3()!=null && partida.getTurno().equals(partida.getJugador3())){
                            jugador=jugadorServicio.buscar(partida.getJugador3());
                            //System.out.println("Se le va a saltar el turno al jug 3: "+jugador.getNick());
                        }if(partida.getJugador4()!=null && partida.getTurno().equals(partida.getJugador4())){
                            jugador=jugadorServicio.buscar(partida.getJugador4());
                            //System.out.println("Se le va a saltar el turno al jug 4: "+jugador.getNick());
                        }
                        
                            System.out.println("jugado: "+jugador.getClaveJugador());
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

                        String noMulta=this.comprobarCasillaQueHasCaido(jugador);


                        if(noMulta!=null && noMulta.indexOf("noMulta")!=-1){
                            if(this.jugadorQueQuedan(partida)<4){
                                this.cambiarTurnoMenosDe4(partida);

                            }else{
                                cambiarTurno(partida);
                            }
                        }
                        
                    }

                    
                }

            }
        }else{
            System.out.println("No ai partidas llenas");
            Logger.getLogger(JuegoControlador.class.getName()).info("No ai partidas llenas");
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

        Usuario usuario=usuarioServicio.getCurrentUser();
        Jugador jugador=jugadorServicio.buscar(usuario.getJugador());

         
         return this.comprobarCasillaQueHasCaido(jugador);
 
    }




    private JSONObject multaJson(int multa,int numJugador,String tipo,boolean eliminado,boolean fin){
        JSONObject json=new JSONObject();
        try{

            json.put("multa", multa);
            json.put("numJugador",numJugador);
            json.put("tipo", tipo);
            json.put("eliminado", eliminado);
            json.put("fin", fin);

        }catch (JSONException ex){

        }
        return json;
    }



    private JSONObject opcionCompraJson(int precio,String nombre,String tipo){
        JSONObject json=new JSONObject();
        try{
            json.put("tipo", tipo);
            json.put("precio", precio);
            json.put("nombre",nombre);
            

        }catch (JSONException ex){

        }
        return json;
    }


        private int numDelJugador(Jugador jugador,Partida partida){
              int n=0;
                if(partida.getJugador1()!= null && partida.getJugador1().compareTo(jugador.getClaveJugador())==0){
                    n= 1;
                }
                if(partida.getJugador2()!= null && partida.getJugador2().compareTo(jugador.getClaveJugador())==0){
                    n= 2;
                }
                if(partida.getJugador3()!= null && partida.getJugador3().compareTo(jugador.getClaveJugador())==0){
                    n=3;
                }if(partida.getJugador4()!= null && partida.getJugador4().compareTo(jugador.getClaveJugador())==0){
                    n=4;
                }
              return n;
        }



        @RequestMapping(value = "/comprarCalle", method = RequestMethod.GET)
        private @ResponseBody String comprarCalle(HttpSession sesion,@RequestParam("jugQueTira") String jugQueTira){

            //Jugador jugador = (Jugador)sesion.getAttribute("jugador");
            Jugador jugador=jugadorServicio.buscar(usuarioServicio.buscar(jugQueTira).getJugador());
            Partida partida =partidaServicio.buscar(jugador.getPartida());

            Casilla casilla=casillaServicio.buscarPorNumero(jugador.getPosicion());
            Calle calle=calleServicio.buscar(casilla.getTipoCasilla());


            if(jugador.getDinero()>=calle.getPrecio()){
                jugador.setDinero(jugador.getDinero()-calle.getPrecio());
                jugador.getCalles().add(casilla.getIdCasilla());

                jugadorServicio.actualizar(jugador);
                return "calle comprada";
            }
            return "insuficiente_dinero";
        }


        //va a ser llamado cuando el jugAQuienPagas !=jugQueJuega y la casilla.getTipoCasilla()!=null
        //jugAQuienPagas es el jugador dueño de la casilla!!!
        //el jugQueJuega es el nº del jug que tiene que pagar la multa
        private boolean pagarMulta(Jugador jugador, int jugAQuienPagas,Partida partida,Calle calle,Casilla casilla,int jugQueJuega){
            boolean eliminado=false;
            int multa=calle.getMulta();
            Jugador jugadorPagas=this.jugadorPertenecienteAlNumero(jugQueJuega, jugAQuienPagas, partida);
            //Desde Aqui es para doblar la multa si tiene todas las casillas del mismo color el jugadorPagas
            //System.out.println("multa inicial: "+multa);
           
            if(this.casillasMismoColor(jugadorPagas, calle)){
                multa=multa*2;
            }
            if(jugador.getDinero()>=multa){
                jugador.setDinero(jugador.getDinero()-multa);
                //se la sumamos al jugador a Quien hay que pagarselo
                //jugadorServicio.buscar(partida.getJugador1()).setDinero(jugadorServicio.buscar(partida.getJugador1()).getDinero()+multa);
                jugadorPagas.setDinero(jugadorPagas.getDinero()+multa);
                jugadorServicio.actualizar(jugador);
                jugadorServicio.actualizar(jugadorPagas);
                this.cambiarTurnoManualmente(jugador.getNick());
            }if(jugador.getDinero()<multa){
                jugadorPagas.setDinero(jugadorPagas.getDinero()+jugador.getDinero());
                //System.out.println("Llegamos a ponerle el dinero al jug al que pagas");
                this.cambiarTurnoManualmente(jugadorPagas.getNick());
                //System.out.println("Estamos cambiando el turno antes de eliminar al jugador!!");
                eliminado=this.eliminarJugador(jugador);
                partidaServicio.actualizar(partida);
                //System.out.println("Actualizamos partida despues de Eliminar jug");
                //System.out.println("AQUI  this.jugadorQueQuedan(partida)=  "+this.jugadorQueQuedan(partida));

            }
            jugadorServicio.actualizar(jugadorPagas);
            //System.out.println("hay que pagarsela al jugador nº "+jugAQuienPagas);
            //System.out.println("multa es de : "+calle.getMulta());
            return eliminado;
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
            int jugDuenoCasilla=0;
            boolean fin=false;
            Partida partida =partidaServicio.buscar(jugador.getPartida());            
            numeroDeJug=this.numDelJugador(jugador, partida);
            
            
            //comprobamos que la casilla no sea una de las esquinas...
            if(jugador.getPosicion()!=0 && jugador.getPosicion()!=9 && jugador.getPosicion()!=18 && jugador.getPosicion()!=27){
                Casilla casilla=casillaServicio.buscarPorNumero(jugador.getPosicion());
                if(casilla.getTipoCasilla()==null){

                     if(jugador.getPosicion()==3 || jugador.getPosicion()==16 || jugador.getPosicion()==21 || jugador.getPosicion()==34){
                        //Es una calle de suerte asique lo tratamos especial
                        String [] suerte=new String []{"Compraste una premiun antes del cierre de Megaupload -200$",
                            "Chuck Norris te da una palmadita en la espalda y te env�a directamente a la Salida. Cobras los 200$",
                            "Javier hizo rm -r / a tu dinero...solo pudiste salvar 200$",
                            "Tu aplicacion Android da sus resultados +200$",
                            "Te dispararon una fecha a la rodilla jugando a Skyrim, costes medicos -200$",
                            "Inviertes en Mariscos Recio +200$",
                            "Te pillan robando el proyector, a la Carcel !",
                            "Descubres los Toroides +200$"};
                        int random = (int)(8.0 * Math.random());
                       //en este if y else if voy a acer las suertes ways y en el else las de +-200
                        if(random==1){
                            jugador.setPosicion(0);
                            jugador.setDinero(jugador.getDinero()+200);
                            jugadorServicio.actualizar(jugador);
                        }else if(random==2){
                            if(jugador.getDinero()>200){
                                partida.setBote(partida.getBote()+jugador.getDinero()-200);
                                jugador.setDinero(200);
                                partidaServicio.actualizar(partida);
                                jugadorServicio.actualizar(jugador);
                            }
                        }else if(random==6){
                            jugador.setPosicion(27);
                            jugadorServicio.actualizar(jugador);
                        }else{
                        if(random%2==0){
                            if(jugador.getDinero()>=200){
                                partida.setBote(partida.getBote()+200);
                                jugador.setDinero(jugador.getDinero()-200);
                            }else{
                                partida.setBote(partida.getBote()+jugador.getDinero());
                                return this.suerteJson(suerte[random],true).toString();
                            }
                        }else{
                            jugador.setDinero(jugador.getDinero()+200);
                        }
                        }
                        this.cambiarTurnoManualmente(jugador.getNick());
                        return this.suerteJson(suerte[random],false).toString();
                    }



                    Logger.getLogger(JuegoControlador.class.getName()).info("Casilla.getTipoCasilla()==null");
                    this.mostrarCallesDeLosJugadores(partida);
                    this.cambiarTurnoManualmente(jugador.getNick());
                    return null;

                }   
                if(casilla.getTipoCasilla()!=null){
                        Calle calle=calleServicio.buscar(casilla.getTipoCasilla());
                        //Logger.getAnonymousLogger().info("calle: "+ calle.getIdCalle());
                        Logger.getLogger(JuegoControlador.class.getName()).info("calle: "+ calle.getIdCalle());
                        boolean tieneDuenoLaCasilla=false;
                      //if ( (jugadorServicio.buscar(partida.getJugador1())!=null && jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla()))  || (jugadorServicio.buscar(partida.getJugador2())!=null && jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla()) ) || (jugadorServicio.buscar(partida.getJugador3())!=null && jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla()) )|| (jugadorServicio.buscar(partida.getJugador4())!=null && jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla())  )){
                        if(partida.getJugador1()!=null && jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla())){
                              tieneDuenoLaCasilla=true;
                        }else if(partida.getJugador2()!=null && jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla())){
                            tieneDuenoLaCasilla=true;
                        }else if(partida.getJugador3()!=null && jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla())){
                            tieneDuenoLaCasilla=true;
                        }else if(partida.getJugador4()!=null && jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla())){
                            tieneDuenoLaCasilla=true;
                        }
                        
                        if(tieneDuenoLaCasilla){
                          Logger.getLogger(JuegoControlador.class.getName()).info("Alguien tiene esa casilla");
                          jugDuenoCasilla=this.jugadorDuenoDeLaCasilla(casilla, partida);
                          if(jugDuenoCasilla!=0 && jugDuenoCasilla!=numeroDeJug){
                              
                              //ahora llamamos al metodo que estaba haciendo...
                              boolean eliminado=this.pagarMulta(jugador, jugDuenoCasilla , partida, calle, casilla,numeroDeJug );
                              if(this.jugadorQueQuedan(partida)==2 && eliminado){
                                  fin=true;
                              }
                              Jugador j=this.jugadorPertenecienteAlNumero(numeroDeJug, jugDuenoCasilla, partida);
                              if(this.casillasMismoColor(j, calle)){
                                  return this.multaJson(calle.getMulta()*2, jugDuenoCasilla, "multa",eliminado,fin).toString();
                              }
                              Logger.getLogger(JuegoControlador.class.getName()).info("Casilla.getTipoCasilla()!=null pagar multa");
                                this.mostrarCallesDeLosJugadores(partida);

                              return this.multaJson(calle.getMulta(), jugDuenoCasilla, "multa",eliminado,fin).toString();

                          }if(jugDuenoCasilla==numeroDeJug){
                                Logger.getLogger(JuegoControlador.class.getName()).info("Casilla.getTipoCasilla()!=null casilla tuya");
                                this.mostrarCallesDeLosJugadores(partida);
                                this.cambiarTurnoManualmente(jugador.getNick());
                                return "tuya";
                          }
                          
                      }
                      return this.opcionCompraJson(calle.getPrecio(), casilla.getNombre(), "noMulta").toString();
                }else{
                    if(jugador.getPosicion()==3 || jugador.getPosicion()==16 || jugador.getPosicion()==21 || jugador.getPosicion()==34){
                        //Es una calle de suerte
                        String [] suerte=new String []{"pagas 200$","te pagan 200$","pagas 200$","te pagan 200$"};
                        int random = (int)(3.0 * Math.random());
                        if(random%2==0){
                            if(jugador.getDinero()>=200){
                                jugador.setDinero(jugador.getDinero()-200);
                            }else{
                                this.eliminarJugador(jugador);
                                return this.suerteJson(suerte[random],true).toString();
                            }
                        }else{
                            jugador.setDinero(jugador.getDinero()+200);
                        }
                        return this.suerteJson(suerte[random],false).toString();
                    }else{
                    //esto es pq has caido en una calle de hidroelectrica o estacion
                    Logger.getLogger(JuegoControlador.class.getName()).info("Casilla.getTipoCasilla()==null suerte,hidroelectrica...");
                    this.mostrarCallesDeLosJugadores(partida);
                    this.cambiarTurnoManualmente(jugador.getNick());
                    return null;
                    }
                }

            }
            if(jugador.getPosicion()==18){
                jugador.setDinero(jugador.getDinero()+partida.getBote());
                partida.setBote(0);
                jugadorServicio.actualizar(jugador);
                partidaServicio.actualizar(partida);
            }
        
            //esto es porque has caido en una calle de las esquinas
            Logger.getLogger(JuegoControlador.class.getName()).info("Casilla.getTipoCasilla()==null has caido en una esquina");
            this.mostrarCallesDeLosJugadores(partida);
            this.cambiarTurnoManualmente(jugador.getNick());
            return null;

        }
        
        
        private int jugadorDuenoDeLaCasilla(Casilla casilla,Partida partida){
            if(partida.getJugador1()!=null && jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla())){
                return 1;
            }if(partida.getJugador2()!=null && jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla())){
                return 2;
            }if(partida.getJugador3()!=null && jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla())){
                return 3;
            }if(partida.getJugador4()!=null && jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla())){
            return 4;
            }
            return 0;
        }


         
         private  boolean eliminarJugador(Jugador jugador){

            int numJugador;

            //Jugador jugador = (Jugador)sesion.getAttribute("jugador");
            Partida partida =partidaServicio.buscar(jugador.getPartida());
            int cantidadDeJugadores=this.jugadorQueQuedan(partida);
            //System.out.println("llegamos a sacar la partida");

            numJugador=this.numDelJugador(jugador, partida);
            if(numJugador==1){
                partida.setJugador1(null);
            }
            if(numJugador==2){
                partida.setJugador2(null);              
            }
            if(numJugador==3){
                partida.setJugador3(null);
            }
            if(numJugador==4){
                partida.setJugador4(null);
            }

            
            partidaServicio.actualizar(partida);

            if(cantidadDeJugadores==2){
                usuarioServicio.buscar(jugador.getNick()).setPartidasGanadas(usuarioServicio.buscar(jugador.getNick()).getPartidasGanadas()+1);
                usuarioServicio.actualizar(usuarioServicio.buscar(jugador.getNick()));
             }
            //jugadorServicio.borrar(jugador);
            //System.out.println("Se ha eliminado el jugador"+numJugador);
            return true;

         }

         public int jugadorQueQuedan(Partida partida){
             int numJugadoresQueQuedan=4;


             if(partida.getJugador1()==null){
                 numJugadoresQueQuedan--;
             }
             if(partida.getJugador2()==null){
                 numJugadoresQueQuedan--;
             }
             if(partida.getJugador3()==null){
                 numJugadoresQueQuedan--;
             }
             if(partida.getJugador4()==null){
                 numJugadoresQueQuedan--;

             }
             Logger.getLogger(JuegoControlador.class.getName()).info(" "+numJugadoresQueQuedan);
             
             return numJugadoresQueQuedan;
         }



         //deberia recibir el numero de jugadores que quedan en la partida y si fuese 1 terminaria la partida
         private void cambiarTurnoMenosDe4(Partida partida){
             int jugadoresQuedan=this.jugadorQueQuedan(partida);
             int cuantosJugadoresHayEnCarcel=0;
             
                if(partida.getJugador1()!=null && jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true ){
                    cuantosJugadoresHayEnCarcel++;
                    if(partida.getJugador2()!=null && jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true ){
                        cuantosJugadoresHayEnCarcel++;
                        if(partida.getJugador3()!=null && jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true ){
                            cuantosJugadoresHayEnCarcel++;
                            if(partida.getJugador4()!=null && jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true ){
                                cuantosJugadoresHayEnCarcel++;

                            }
                        }
                    }
                }
             if(cuantosJugadoresHayEnCarcel==jugadoresQuedan){
                partida.setTurno(partida.getJugador1());
                jugadorServicio.buscar(partida.getJugador1()).setEstoyEnCarcel(false);
             }else {
             Jugador jugador=new Jugador();
             jugador=jugadorServicio.buscar(partida.getTurno());
             partidaServicio.actualizar(partida);
             int numJugador=this.numDelJugador(jugador, partida);
             Logger.getLogger(JuegoControlador.class.getName()).info("Antes de cambiar el turno al nº "+ numJugador);
             if(numJugador==1){
                 if(partida.getJugador2()==null){
                     if(partida.getJugador3()==null){
                         if(partida.getJugador4()==null){
                             System.out.println("Has ganado");
                         }else {
                             partida.setTurno(partida.getJugador4());
                             if(jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador4()).setEstoyEnCarcel(false);
                             }
                         }
                     }else{
                         partida.setTurno(partida.getJugador3());
                         if(jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador3()).setEstoyEnCarcel(false);
                         }
                     }
                 }else{
                     partida.setTurno(partida.getJugador2());
                    if(jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador2()).setEstoyEnCarcel(false);
                    }
                 }
             }

             if(numJugador==2){
                 if(partida.getJugador3()==null){
                     if(partida.getJugador4()==null){
                         if(partida.getJugador1()==null){
                            System.out.println("Has ganado");
                         }else{
                             partida.setTurno(partida.getJugador1());
                             if(jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador1()).setEstoyEnCarcel(false);
                             }
                         }
                     }else{
                         partida.setTurno(partida.getJugador4());
                         if(jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador4()).setEstoyEnCarcel(false);
                         }
                     }
                 }else{
                     partida.setTurno(partida.getJugador3());
                     if(jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador3()).setEstoyEnCarcel(false);
                     }
                 }
             }

             if(numJugador==3){
                 if(partida.getJugador4()==null){
                     if(partida.getJugador1()==null){
                         if(partida.getJugador2()==null){
                            System.out.println("Has ganado");
                         }else{
                             partida.setTurno(partida.getJugador2());
                            if(jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador2()).setEstoyEnCarcel(false);
                             }
                         }
                     }else{
                         partida.setTurno(partida.getJugador1());
                         if(jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador1()).setEstoyEnCarcel(false);
                         }
                     }
                 }else{
                     partida.setTurno(partida.getJugador4());
                    if(jugadorServicio.buscar(partida.getJugador4()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador4()).setEstoyEnCarcel(false);
                    }
                 }
             }

             if(numJugador==4){
                 if(partida.getJugador1()==null){
                     if(partida.getJugador2()==null){
                         if(partida.getJugador3()==null){
                             System.out.println("Has ganado");

                         }else{
                             partida.setTurno(partida.getJugador3());
                             if(jugadorServicio.buscar(partida.getJugador3()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador3()).setEstoyEnCarcel(false);
                             }
                         }
                     }else{
                         partida.setTurno(partida.getJugador2());
                         if(jugadorServicio.buscar(partida.getJugador2()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador2()).setEstoyEnCarcel(false);
                         }
                     }
                 }else{
                     partida.setTurno(partida.getJugador1());
                     if(jugadorServicio.buscar(partida.getJugador1()).getEstoyEnCarcel()== true){
                                 cambiarTurnoMenosDe4(partida);
                                 jugadorServicio.buscar(partida.getJugador1()).setEstoyEnCarcel(false);
                     }
                 }
             }
            }
             partida.setFechaTurno(System.currentTimeMillis()+60000);
             partida.setHaTirado(false);
             partidaServicio.actualizar(partida);
         }

        @RequestMapping(value = "/terminarJugadorPartida", method = RequestMethod.GET)
        private String terminarJugadorPartida(HttpSession sesion){
            
            //Jugador jugador = (Jugador)sesion.getAttribute("jugador");
            Usuario usuario=usuarioServicio.getCurrentUser();
            Jugador jugador=jugadorServicio.buscar(usuario.getJugador());
            Partida partida=partidaServicio.buscar(jugador.getPartida());
            boolean turnoJugador=false;

            if(partida.getTurno().compareTo(jugador.getClaveJugador())==0){
                turnoJugador=true;
                this.cambiarTurnoMenosDe4(partida);
            }
            if(this.jugadorQueQuedan(partida)==2){

                List<Jugador>jugadores=jugadorServicio.todosJugadoresDePartida(partida.getIdpartida());

                Jugador jugador1=jugadores.get(0);
                Jugador jugador2=jugadores.get(1);
                Jugador jugador3=jugadores.get(2);
                Jugador jugador4=jugadores.get(3);

                usuarioServicio.buscar(jugador1.getNick()).setPartidasJugadas(usuarioServicio.buscar(jugador1.getNick()).getPartidasJugadas()+1);
                usuarioServicio.buscar(jugador2.getNick()).setPartidasJugadas(usuarioServicio.buscar(jugador2.getNick()).getPartidasJugadas()+1);
                usuarioServicio.buscar(jugador3.getNick()).setPartidasJugadas(usuarioServicio.buscar(jugador3.getNick()).getPartidasJugadas()+1);
                usuarioServicio.buscar(jugador4.getNick()).setPartidasJugadas(usuarioServicio.buscar(jugador4.getNick()).getPartidasJugadas()+1);

                usuarioServicio.buscar(jugador.getNick()).setPartidasGanadas(usuarioServicio.buscar(jugador.getNick()).getPartidasGanadas()+1);

                usuarioServicio.actualizar(usuarioServicio.buscar(jugador1.getNick()));
                usuarioServicio.actualizar(usuarioServicio.buscar(jugador2.getNick()));
                usuarioServicio.actualizar(usuarioServicio.buscar(jugador3.getNick()));
                usuarioServicio.actualizar(usuarioServicio.buscar(jugador4.getNick()));

            }

            if(partida.getJugador1()!=null && partida.getJugador1().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador1(null);
            }
            if(partida.getJugador2()!=null && partida.getJugador2().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador2(null);
            }
            if(partida.getJugador3()!=null && partida.getJugador3().compareTo(jugador.getClaveJugador())==0 ){
                partida.setJugador3(null);
            }
            if(partida.getJugador4()!=null && partida.getJugador4().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador4(null);

            }
            partidaServicio.actualizar(partida);

            
            
            //partidaServicio.actualizar(partida);
            
            usuario.setJugador(null);
            usuarioServicio.actualizar(usuario);
            return "/perfil";
    
        }
        
        /*@RequestMapping(value="/volverAtras",method=RequestMethod.GET)
        public String intermedio(Model model){
            Usuario usuario=usuarioServicio.getCurrentUser();
            //this.doEntrar(usuario.getNick(),usuario.getContrasena(), model);
            return "redirect:entrar?nick="+usuario.getNick()+"&contrasena="+usuario.getContrasena()+"&model="+model;
    }*/


        @RequestMapping(value= "/jugadorGanadorPartida", method = RequestMethod.GET)
        private String jugadorGanadorPartida(){
            Jugador jugador=jugadorServicio.buscar(usuarioServicio.getCurrentUser().getJugador());
            Usuario usuario=usuarioServicio.getCurrentUser();
            //Logger.getLogger(JuegoControlador.class.getName()).info("Antes de sumarle 2 al ganador:  "+usuario.getPartidasGanadas());
            usuario.setPartidasGanadas(usuario.getPartidasGanadas()+2);
            //Logger.getLogger(JuegoControlador.class.getName()).info("Despues de sumarle 2 al ganador:  "+usuario.getPartidasGanadas());
            usuario.setJugador(null);
            usuarioServicio.actualizar(usuario);
            //Logger.getLogger(JuegoControlador.class.getName()).info("Despues de actualizarlo:  "+usuario.getPartidasGanadas());

            Partida partida=partidaServicio.buscar(jugador.getPartida());

            if(partida.getJugador1()!=null && partida.getJugador1().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador1(null);
            }if(partida.getJugador2()!=null && partida.getJugador2().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador2(null);
            }if(partida.getJugador3()!=null && partida.getJugador3().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador3(null);
            }if(partida.getJugador4()!=null && partida.getJugador4().compareTo(jugador.getClaveJugador())==0){
                partida.setJugador4(null);
            }
            partidaServicio.actualizar(partida);

            List<Jugador> todosLosJugadores=jugadorServicio.todosJugadoresDePartida(partida.getIdpartida());
            for(Jugador j:todosLosJugadores){
                //Logger.getLogger(JuegoControlador.class.getName()).info("Antes de eliminar el objeto jugador:  "+j.getNick());
                j.setCalles(null);
                j.setPartida(null);
                jugadorServicio.borrar(j);
            }
            //Logger.getLogger(JuegoControlador.class.getName()).info("Antes de eliminar el objeto partida:  "+partida.getIdpartida());
            //partidaServicio.terminar(partida);
            //Logger.getLogger(JuegoControlador.class.getName()).info("Despues de eliminar el objeto partida:  "+partida.getIdpartida());
            return "redirect:finPartida?idPartida="+partida.getIdString();
        }


        private boolean casillasMismoColor(Jugador jugadorPagas,Calle calle){
            boolean comprobacionCallesMismoColor=true;
            List<Calle> callesMismoColor=calleServicio.buscarPorColor(calle.getColor());
            List<Casilla> casillasMismoColor=new ArrayList();
            for(Calle c:callesMismoColor){
                casillasMismoColor.add(casillaServicio.buscarPorCalle(c.getIdCalle()));
            }
            for(Casilla c:casillasMismoColor){
                if(!jugadorPagas.getCalles().contains(c.getIdCasilla())){
                    comprobacionCallesMismoColor=false;
                }
            }

            //System.out.println("comprobacionCallesMismoColor:  "+comprobacionCallesMismoColor);
            return comprobacionCallesMismoColor;
        }

        private Jugador jugadorPertenecienteAlNumero(int jugQueJuega,int jugAQuienPagas,Partida partida){
            Jugador jugador=new Jugador();
            if(jugAQuienPagas==1 && jugQueJuega!=1){
                //comprobamos si el jugador 1 es qien tiene la casilla en la q has caido
                jugador= jugadorServicio.buscar(partida.getJugador1());
            }if(jugAQuienPagas==2 && jugQueJuega!=2){
                //comprobamos si el jugador 2 es qien tiene la casilla en la q has caido
                jugador= jugadorServicio.buscar(partida.getJugador2());
            }if(jugAQuienPagas==3 && jugQueJuega!=3){
                //comprobamos si el jugador 3 es qien tiene la casilla en la q has caido
                jugador= jugadorServicio.buscar(partida.getJugador3());
            }if(jugAQuienPagas==4 && jugQueJuega!=4){
                //comprobamos si el jugador 4 es qien tiene la casilla en la q has caido
                jugador= jugadorServicio.buscar(partida.getJugador4());
            }
            return jugador;
        }


    @RequestMapping(method=RequestMethod.GET, value="/finPartida")
    public String finPartida(@RequestParam(value="idPartida", required=false,defaultValue="") String idPartida,
        Model model,
        HttpSession sesion){
        Usuario usuario=usuarioServicio.getCurrentUser();
        Key idKeyPartida=KeyFactory.stringToKey(idPartida);
        partidaServicio.terminar(partidaServicio.buscar(idKeyPartida));
        model.addAttribute("usuario", usuario);
        return "/perfil";
    }


    public void mostrarCallesDeLosJugadores(Partida partida){

        if(partida.getJugador1()!=null){
        Logger.getLogger(JuegoControlador.class.getName()).info("Calles del jug1= "+ jugadorServicio.buscar(partida.getJugador1()).getCalles().toString());
        }if(partida.getJugador2()!=null){
          Logger.getLogger(JuegoControlador.class.getName()).info("Calles del jug2= "+ jugadorServicio.buscar(partida.getJugador2()).getCalles().toString());
            }if(partida.getJugador3()!=null){
            Logger.getLogger(JuegoControlador.class.getName()).info("Calles del jug3= "+ jugadorServicio.buscar(partida.getJugador3()).getCalles().toString());
            }if(partida.getJugador4()!=null){
            Logger.getLogger(JuegoControlador.class.getName()).info("Calles del jug4= "+ jugadorServicio.buscar(partida.getJugador4()).getCalles().toString());
            }

    }

        private JSONObject suerteJson(String suerte,boolean arruinado){
        JSONObject json=new JSONObject();
        try{
            json.put("tipo", "suerte");
            json.put("contenido", suerte);
            json.put("arruinado", arruinado);



        }catch (JSONException ex){

        }
        return json;
    }


       
}
