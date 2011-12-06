/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.servicio;

import java.util.List;
import monodaipoly.dao.MensajeDAO;
import monodaipoly.persistencia.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adri
 */
@Service
public class MensajeServicioImpl implements MensajeServicio{
    
    private MensajeDAO mensajeDAO;
    @Autowired
    @Required
    public void setMensajeDAO(MensajeDAO mensajeDAO){
        this.mensajeDAO=mensajeDAO;
    }
     
    @Override
    public void crear(Mensaje mensaje) {
        mensajeDAO.insert(mensaje);
    }

    @Override
    public void borrar(Mensaje mensaje) {
        mensajeDAO.remove(mensaje);
    }

    @Override
    public List conseguirMensajes() {
        return mensajeDAO.getAll(Mensaje.class);
    }
    
    @Override
    public List conseguirMensajesRecibidos(String nombre){
        return mensajeDAO.conseguirMensajesRecibidos(nombre);
    }
    
}