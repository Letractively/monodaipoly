package monodaipoly.controlador;

import java.util.ArrayList;
import java.util.Map;
import monodaipoly.persistencia.Casilla;
import monodaipoly.servicio.CasillaServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TableroControlador {

    private CasillaServicio casillaServicio;

     @RequestMapping(value = "/tablero2", method = RequestMethod.GET)
    public String doShowTablero(Model model) {

         /*Casilla[] casillas=new Casilla[4];
         Casilla casilla1=new Casilla();
         casilla1.setColor("rojo");
         casillas[0]=casilla1;
         */
        int c;
         ArrayList casillas=new ArrayList();
         for(c=0;c<=37;c++){
             Casilla casilla = new Casilla();
             casilla.setNumeroCasilla(c);
             casillas.add(casilla);
         }
        model.addAttribute("casillas",casillas);
        return "/tablero2";
    }
        @RequestMapping(value = "/tablero", method = RequestMethod.GET)
    public String doShowTablero1(Model model) {

        return "/tablero";
    }

}
