/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.controlador;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import monodaipoly.persistencia.Mensaje;
import monodaipoly.persistencia.Usuario;
import monodaipoly.servicio.MensajeServicio;
import monodaipoly.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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
 * @author Adri
 */
@Controller
public class MensajeControlador {

     private UsuarioServicio usuarioServicio;
     private MensajeServicio mensajeServicio;

    @Autowired
    @Required
    public void setUsuarioServicio(UsuarioServicio usuarioServicio){
        this.usuarioServicio=usuarioServicio;
    }
    @Autowired
    @Required
    public void setMensajeServicio(MensajeServicio mensajeServicio){
        this.mensajeServicio=mensajeServicio;
    }

    @RequestMapping(value = "/mensajes", method = RequestMethod.GET)
    public String doShowMensajes() {
        return "/mensajes";
    }

    @RequestMapping(method=RequestMethod.GET, value="/enviarMensajes")
    public String enviarMensajes(HttpSession sesion,
                    @RequestParam("contenidoMensaje") String contenidoMensaje,
                    @RequestParam("destinatario") String destinatario
                   ){
        Mensaje mensaje=new Mensaje();
        Usuario usuario=usuarioServicio.getCurrentUser();
        if(usuarioServicio.buscar(destinatario)!=null){

        mensaje.setContenido(contenidoMensaje);
        mensaje.setAutor(usuario.getNick());
        mensaje.setDestinatario(usuarioServicio.buscar(destinatario).getNick());
        mensajeServicio.crear(mensaje);
        usuarioServicio.buscar(destinatario).getBandejaEntrada().add(mensaje.getIdMensaje());
        usuario.getBandejaEntrada().add(mensaje.getIdMensaje());
        usuarioServicio.actualizar(usuario);
            return "redirect:estadoMensaje?estado=CORRECTO";
        }

        else{
            return "redirect:estadoMensaje?estado=ERROR";
        }

    }

    @RequestMapping(method=RequestMethod.GET, value="/recibidos")
    public @ResponseBody String verRecibidos(HttpSession sesion,Model model){
        Usuario usuario=(Usuario) sesion.getAttribute("usuario");
        List<Mensaje> recibidos=new ArrayList<Mensaje>();
        recibidos=mensajeServicio.conseguirMensajesRecibidos(usuario.getNick());
        System.out.println("MensajeControlador");
        if(!recibidos.isEmpty()){
            System.out.println("tiene mensajes Controlador");
            System.out.println("Mensajes:  "+ recibidos.get(0).getContenido());
            return mensajesJson(recibidos).toString();
        }
        else{
            String noMensajes="No tienes mensajes";
            System.out.println("No tiene mensajes Controlador");
            return noMensajes;
        }
        
    }
    @RequestMapping(method=RequestMethod.GET, value="/enviados")
    public @ResponseBody String verEnviados(HttpSession sesion,Model model){
        Usuario usuario=(Usuario) sesion.getAttribute("usuario");
        List<Mensaje> enviados=new ArrayList<Mensaje>();
        enviados=mensajeServicio.conseguirMensajesEnviados(usuario.getNick());
        System.out.println("MensajeControlador");
        if(!enviados.isEmpty()){
            System.out.println("tiene mensajes Controlador");
            System.out.println("Mensajes:  "+ enviados.get(0).getContenido());
            return mensajesJson(enviados).toString();
        }
        else{
            String noMensajes="No tienes mensajes";
            System.out.println("No tiene mensajes Controlador");
            return noMensajes;
        }
        
    }


    private JSONObject datosMensaje(Mensaje mensaje){
        JSONObject json=new JSONObject();
        int i;
        try{
                json.put("contenido", mensaje.getContenido());
                json.put("author", mensaje.getAutor());
                json.put("destinatario", mensaje.getDestinatario());
        }catch (JSONException ex){

        }

        return json;

    }

@RequestMapping(method=RequestMethod.GET, value="/estadoMensaje")
    public String comprobarEstadoMensaje(@RequestParam(value="estado", required=false,defaultValue="") String estado,
        Model model,
        HttpSession sesion){
        Usuario usuario =(Usuario)sesion.getAttribute("usuario");
        model.addAttribute("estado",estado );
        model.addAttribute("usuario", usuario);
        return "/perfil";
    }


    private String mensajesJson(List mensajes){
        String salida = "[";
        try {
            for (int i= 0; i < mensajes.size(); i++) {
                salida +=this.datosMensaje((Mensaje) mensajes.get(i));
                if (i < mensajes.size() -1) {
                    salida += ",";
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MensajeControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        salida += "]";
        return salida;
    }


    @RequestMapping(method=RequestMethod.GET, value="/verMensajesRecibidosURL")
    public  String verMensajesRecibidos(HttpSession sesion,Model model){
        Usuario usuario=usuarioServicio.getCurrentUser();
        List<Mensaje> recibidos=new ArrayList<Mensaje>();
        recibidos=mensajeServicio.conseguirMensajesRecibidos(usuario.getNick());
        System.out.println("MensajeControlador ¡¡");
        
        if(recibidos.isEmpty()){
           String noMensajes="No tienes Mensajes";
           System.out.println(noMensajes);
           model.addAttribute("noMensajes", noMensajes);
           return "/verBandejaEntrada";
        }
        else{
            System.out.println(recibidos.size());
            model.addAttribute("mensajesTotal",recibidos.size() );
            model.addAttribute("mensajes", recibidos);
            return "/verBandejaEntrada";
        }

    }




}