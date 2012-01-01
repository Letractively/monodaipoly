package monodaipoly.servicio;

import java.util.List;
import monodaipoly.persistencia.Mensaje;


public interface MensajeServicio {
     public void crear(Mensaje mensaje);
     public void borrar(Mensaje mensaje);
     public List conseguirMensajes();
     public List conseguirMensajesRecibidos(String nombre);
    
}
