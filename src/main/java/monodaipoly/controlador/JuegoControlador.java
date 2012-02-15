 package monodaipoly.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Partida;
import monodaipoly.servicio.CasillaServicio;
import monodaipoly.servicio.JugadorServicio;
import monodaipoly.servicio.PartidaServicio;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        partida.setFechaTurno(System.currentTimeMillis()+10000);
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

       
}

