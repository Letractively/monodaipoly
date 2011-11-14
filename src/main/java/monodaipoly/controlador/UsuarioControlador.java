/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.controlador;

import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author instalador
 */
@Controller
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio=usuarioServicio;
    }

    @RequestMapping(value = "/monodaipoly", method = RequestMethod.GET)
    public String doShowPagInicio() {
        return "/monodaipoly";
    }

    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public String doShowRegistro() {
        return "/registro";
    }

    @RequestMapping(value="/registrando", method =RequestMethod.POST)
    public String doRegistrar(@RequestParam("nombre") String nombre, Model model
            ,@RequestParam("apellido") String apellido
            ,@RequestParam("fechaDia") String fechaDia
            ,@RequestParam("fechaMes") String fechaMes
            ,@RequestParam("fechaAno") String fechaAno
            ,@RequestParam("nick") String nick
            ,@RequestParam("contrasena") String contrasena){
            if(nick.length()==0 || contrasena.length()==0){
                return "/registro";
            }
            if(this.usuarioServicio.buscar(nick)==null){
                Usuario usuario= new Usuario(nick,contrasena,nombre,apellido,fechaDia,fechaMes,fechaMes);
                this.usuarioServicio.crear(usuario);
                return "redirect:monodaipoly?registrado";
            }
            return "/registro";
    }

    @RequestMapping(value="/entrar", method =RequestMethod.POST)
    public String doEntrar(@RequestParam("nick") String nick, @RequestParam("contrasena")String contrasena,Model model){
        
        if(this.usuarioServicio.buscar(nick)!=null && this.usuarioServicio.buscar(nick).getContrasena().equals(contrasena)){
            model.addAttribute("usuario", this.usuarioServicio.buscar(nick));
            return "/perfil";
        }else{
 
            return "redirect:monodaipoly?error";
         
     }
    }

    @RequestMapping(value="/modificarDatos", method =RequestMethod.POST)
    public String doModificarDatos(@RequestParam("nick") String nick,Model model){
        model.addAttribute("usuario", this.usuarioServicio.buscar(nick));
        return "/modificardatos";

    }

@RequestMapping(value="/modificando", method =RequestMethod.POST)
    public String doGuardarModificaciones(@RequestParam("nombre") String nombre, Model model
            ,@RequestParam("apellido") String apellido
            ,@RequestParam("fechaDia") String fechaDia
            ,@RequestParam("fechaMes") String fechaMes
            ,@RequestParam("fechaAno") String fechaAno
            ,@RequestParam("nick") String nick
            ,@RequestParam("contrasenaNueva") String contrasenaNueva){
            Usuario usuario =this.usuarioServicio.buscar(nick);
            usuario.setApellido(apellido);
            usuario.setNombre(nombre);
            if(contrasenaNueva.compareTo("")!=0){
                usuario.setContrasena(contrasenaNueva);
            }
            if(fechaDia.compareTo("")!=0 && fechaMes.compareTo("0")!=0 && fechaAno.compareTo("")!=0){
                usuario.setFechaNacimiento(fechaDia, fechaMes, fechaAno);
            }
            this.usuarioServicio.actualizar(usuario);
            model.addAttribute("usuario", usuario);
            return "/perfil";
    }


}
