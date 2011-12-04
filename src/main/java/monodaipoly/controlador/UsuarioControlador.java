/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.controlador;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Rol;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.UsuarioServicio;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @RequestMapping(method=RequestMethod.GET, value="/getDatosURL")
    public String getDatosURL() {
        return "/datos";
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
                Usuario usuario= new Usuario(nick,contrasena,nombre,apellido,fechaDia,fechaMes,fechaAno);
                usuarioServicio.anadirRol(usuario,usuarioServicio.buscarRol("ROLE_USER"));
                this.usuarioServicio.crear(usuario);
                return "redirect:monodaipoly?registrado";

            }
            return "/registro";
    }

    @RequestMapping(value="/entrar", method =RequestMethod.POST)
    public String doEntrar(@RequestParam("nick") String nick, @RequestParam("contrasena")String contrasena,Model model){
        try {
                //System.out.println("AQUI 1 UserController login ");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                //System.out.println("AQUI 2 UserController login ");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(nick,contrasena);
                //System.out.println("AQUI 3 UserController login ");
		authToken.setDetails(authentication.getDetails());
                //System.out.println("AQUI 4 UserController login authenticationManager="+authenticationManager);
		Authentication newAuth = authenticationManager.authenticate(authToken);
                //System.out.println("AQUI 5 UserController login ");
		SecurityContextHolder.getContext().setAuthentication(newAuth);
                //System.out.println("AQUI 6 UserController login ");
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
        return "/perfil2";


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
        SecurityContextHolder.getContext().setAuthentication(null);
        Usuario u = (Usuario) sesion.getAttribute("usuario");
        sessionStatus.setComplete();
        //Otra forma: sesion.invalidate();

        return "redirect:index.html";
    }

    @RequestMapping(method=RequestMethod.GET, value="/modificarDatosAsincronamente")
    public @ResponseBody String modi(HttpSession sesion,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("fechaDia") String dia,
            @RequestParam("fechaMes") String mes,
            @RequestParam("fechaAno") String ano){
        Usuario usuario = (Usuario)sesion.getAttribute("usuario");
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getApellido());
        try{
            Integer.parseInt(dia);
            Integer.parseInt(ano);
             if(dia!=null && mes!=null && ano!=null && !dia.trim().equals("")
                && !mes.equals("") && !ano.trim().equals("")){
            usuario.setFechaNacimiento(dia, mes, ano);
        }
        }catch(NumberFormatException nfe){
        }

        if (nombre != null && !nombre.trim().equals("")) {
            usuario.setNombre(nombre.trim());
        }

        if (apellido != null && !apellido.trim().equals("")) {
            usuario.setApellido(apellido.trim());
        }
       
         this.usuarioServicio.actualizar(usuario);
         
        return this.pasarJson(usuario.getNombre(),usuario.getApellido(),usuario.getFechaNacimiento()).toString();
    }

    

    private JSONObject pasarJson(String nombre,String apellido, String fecha){
        JSONObject json=new JSONObject();
        try{
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("fecha", fecha);
        }catch (JSONException ex){

        }
        return json;
    }
   
    @RequestMapping(method=RequestMethod.GET, value="/estadisticas")
    public String doShowEstadisticas(HttpSession sesion,Model model){
        int i,j,c;
        Usuario aux;
        List<Usuario> usuarios=usuarioServicio.conseguirUsuarios();
        System.out.println(usuarios.get(0).getNick());
        List usuariosMejores =new ArrayList();
        System.out.println(usuarios.get(0).getNick());
      
        if(usuarios.size()>10){
            for(c=10;c<0;c++){
                usuariosMejores.add(c,usuarios.get(c));
            }
            model.addAttribute("usuariosMejores", usuariosMejores);
        
        }else{
            model.addAttribute("usuariosMejores", usuarios);
        }
        System.out.println(usuarios.get(0).getNick());
        
        return "/estadisticas";
    }
    

}
