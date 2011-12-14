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
    
    @RequestMapping(method=RequestMethod.POST, value="/enviarMensajes")
    public String enviarMensajes(HttpSession sesion, 
                    @RequestParam("contenidoMensaje") String contenidoMensaje,
                    @RequestParam("destinatario") String destinatario
                   ){
      
        Mensaje mensaje=new Mensaje();
        Usuario usuario=(Usuario) sesion.getAttribute("usuario");
        mensaje.setContenido(contenidoMensaje);
        mensaje.setAutor(usuario.getNick());
        mensaje.setDestinatario(usuarioServicio.buscar(destinatario).getNick());
        mensajeServicio.crear(mensaje);
        usuarioServicio.buscar(destinatario).getBandejaEntrada().add(mensaje);       
        return "/perfil2";
        
        
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/recibidos")
    public String verRecibidos(HttpSession sesion,Model model){
        Usuario usuario=(Usuario) sesion.getAttribute("usuario");
        System.out.println("Mensajes!!!");
        List<Mensaje> recibidos=new ArrayList<Mensaje>();
        recibidos=mensajeServicio.conseguirMensajesRecibidos(usuario.getNick());
        System.out.println(recibidos.get(4).getContenido());
        return mensajesJson(recibidos).toString();
    }

    private JSONObject datosMensaje(Mensaje mensaje){
        JSONObject json=new JSONObject();
        int i;
        try{
                json.put("mensaje", mensaje);
        }catch (JSONException ex){

        }
        
        return json;

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
     
     
    
    
}