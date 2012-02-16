 package monodaipoly.controlador;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.List;

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

        partida.setFechaTurno(System.currentTimeMillis()+100000);
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
    private @ResponseBody String comprobarCalle(HttpSession sesion,@RequestParam("jugQueTira") String jugQueTira){

         Jugador jugador = (Jugador)sesion.getAttribute("jugador");
         Partida partida =partidaServicio.buscar(jugador.getPartida());

         Casilla casilla=casillaServicio.buscarPorNumero(jugador.getPosicion());
         //comprobamos que no es nulo el tipo casilla
         //porque las q son de tipo nulo no se pueden comprar...
         //son las estaciones,hidroelectricas....
         //y se trataran de forma especial
         if(casilla.getTipoCasilla()!=null){
             //busco la calle en la q ha caido para saber la multa o el precio
         Calle calle=calleServicio.buscar(casilla.getTipoCasilla());

                 

         if (jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla()) || jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla()) ){

             //si no es tuya la calle....comprobaremos de quien es
             if(!jugador.getCalles().contains(casilla.getIdCasilla())){
                 int multa=0;
                 int numJug=this.numDelJugador(jugador, partida);
                 int jugadorPagas=0;

                 if(jugadorServicio.buscar(partida.getJugador1()).getCalles().contains(casilla.getIdCasilla()) && numJug!=1){
                     //paga la multa y se lo sumamos al jugador 1
                     multa=calle.getMulta();
                     jugador.setDinero(jugador.getDinero()-calle.getMulta());
                     jugadorServicio.buscar(partida.getJugador1()).setDinero(jugadorServicio.buscar(partida.getJugador1()).getDinero()+multa);
                     jugadorServicio.actualizar(jugadorServicio.buscar(partida.getJugador1()));
                     jugadorPagas=1;
                     //ahora enviaremos un mensaje que diga que a pagado una multa
                     


                 }
                 if(jugadorServicio.buscar(partida.getJugador2()).getCalles().contains(casilla.getIdCasilla()) && numJug!=2){
                     //paga la multa y se lo sumamos al jugador 2
                     jugador.setDinero(jugador.getDinero()-calle.getMulta());
                     jugadorServicio.buscar(partida.getJugador2()).setDinero(jugadorServicio.buscar(partida.getJugador2()).getDinero()+calle.getMulta());
                     jugadorServicio.actualizar(jugadorServicio.buscar(partida.getJugador2()));
                     //ahora enviaremos un mensaje que diga que a pagado una multa
                     multa=calle.getMulta();
                     jugadorPagas=2;


                 }if(jugadorServicio.buscar(partida.getJugador3()).getCalles().contains(casilla.getIdCasilla()) && numJug!=3){
                     //paga la multa y se lo sumamos al jugador 3
                     jugador.setDinero(jugador.getDinero()-calle.getMulta());
                     jugadorServicio.buscar(partida.getJugador3()).setDinero(jugadorServicio.buscar(partida.getJugador3()).getDinero()+calle.getMulta());
                     jugadorServicio.actualizar(jugadorServicio.buscar(partida.getJugador3()));
                     //ahora enviaremos un mensaje que diga que a pagado una multa
                     multa=calle.getMulta();
                     jugadorPagas=3;


                 }
                 if(jugadorServicio.buscar(partida.getJugador4()).getCalles().contains(casilla.getIdCasilla()) && numJug!=4){
                     //paga la multa y se lo sumamos al jugador 4
                     jugador.setDinero(jugador.getDinero()-calle.getMulta());
                     jugadorServicio.buscar(partida.getJugador4()).setDinero(jugadorServicio.buscar(partida.getJugador4()).getDinero()+calle.getMulta());
                     jugadorServicio.actualizar(jugadorServicio.buscar(partida.getJugador4()));
                     //ahora enviaremos un mensaje que diga que a pagado una multa
                     multa=calle.getMulta();
                     jugadorPagas=4;

                 }

                 jugadorServicio.actualizar(jugador);


                 return multaJson(multa,jugadorPagas,"multa").toString();
             }
             //la calle es tuya...no hacemos nada...
             return "tuya";

         }

         //vamos a darle la opcion de comprar la calle en la q ha caido...ya q no es de nadie
         return opcionCompraJson(calle.getPrecio(),casilla.getNombre(),"noMulta").toString();

        }
         //no se puede dar la opcion de compra pq es una estacion...
        return null;
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
                    return 1;
                }
                if(partida.getJugador3().compareTo(jugador.getClaveJugador())==0){
                    return 1;
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
                jugador.getCalles().add(casilla.getIdCasilla());
                jugadorServicio.actualizar(jugador);
                return "calle comprada";
            }
            return "insuficiente_dinero";
        }


       
}

