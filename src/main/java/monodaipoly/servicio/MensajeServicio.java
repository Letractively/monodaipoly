/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.servicio;

import java.util.List;
import monodaipoly.persistencia.Mensaje;

/**
 *
 * @author Adri
 */
public interface MensajeServicio {
     public void crear(Mensaje mensaje);
     public void borrar(Mensaje mensaje);
      public List conseguirMensajes();
    
}
