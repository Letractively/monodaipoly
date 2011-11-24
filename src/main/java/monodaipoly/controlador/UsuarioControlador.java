/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.controlador;

import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author instalador
 */
@Controller
@SessionAttributes({"usuario"})
public class UsuarioControlador {

    private UsuarioServicio usuarioServicio;
    private AuthenticationManager authenticationManager ;

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio=usuarioServicio;
    }
    @Autowired
    @Required
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

     @RequestMapping(method=RequestMethod.GET, value="/getUsuarioConectado", headers="Accept=application/json")
    public @ResponseBody Usuario getUser() {
        return usuarioServicio.getCurrentUser();
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
        try {
                System.out.println("AQUI 1 UserController login ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("AQUI 2 UserController login ");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(nick,contrasena);
                System.out.println("AQUI 3 UserController login ");
		authToken.setDetails(authentication.getDetails());
                System.out.println("AQUI 4 UserController login authenticationManager="+authenticationManager);
		Authentication newAuth = authenticationManager.authenticate(authToken);
                System.out.println("AQUI 5 UserController login ");
		SecurityContextHolder.getContext().setAuthentication(newAuth);
                System.out.println("AQUI 6 UserController login ");
                if (newAuth.isAuthenticated()) {
                    model.addAttribute("usuario", usuarioServicio.getCurrentUser());
                    //rdo = "Estás conectado como" + username;
                } else {
                    return "redirect:monodaipoly?error";

                }
                //System.out.println("AQUI 7 UserController login ");

	} catch (Exception unfe) {
		return "redirect:monodaipoly?error";

	}
        return "/perfil";


    }

    @RequestMapping(value="/modificarDatos", method =RequestMethod.POST)
    public String doModificarDatos(@RequestParam("nick") String nick,Model model){
      
        return "/modificardatos";

    }

@RequestMapping(value="/modificando", method =RequestMethod.POST)
    public String doGuardarModificaciones(@RequestParam("nombre") String nombre, Model model
            ,@RequestParam("apellido") String apellido
            ,@RequestParam("fechaDia") String fechaDia
            ,@RequestParam("fechaMes") String fechaMes
            ,@RequestParam("fechaAno") String fechaAno
            ,@RequestParam("contrasenaNueva") String contrasenaNueva){
            Usuario usuario =this.getUser();
            usuario.setApellido(apellido);
            usuario.setNombre(nombre);
            if(contrasenaNueva.compareTo("")!=0){
                usuario.setContrasena(contrasenaNueva);
            }
            if(fechaDia.compareTo("")!=0 && fechaMes.compareTo("0")!=0 && fechaAno.compareTo("")!=0){
                usuario.setFechaNacimiento(fechaDia, fechaMes, fechaAno);
            }
            this.usuarioServicio.actualizar(usuario);
            return "/perfil";
    }



        


    @RequestMapping(method=RequestMethod.GET, value="/logout")
    public String logout(HttpSession sesion, SessionStatus sessionStatus, @ModelAttribute("usuario") Usuario usuario) throws java.io.IOException {
        System.out.println("UsuarioController logout se está desconectando "+usuario.getNick());
        SecurityContextHolder.getContext().setAuthentication(null);
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        System.out.println("UsuarioController logout se está desconectando2 "+usuario.getNick());
        sessionStatus.setComplete();
        //Otra forma: sesion.invalidate();

        return "redirect:index.html";
    }


}
