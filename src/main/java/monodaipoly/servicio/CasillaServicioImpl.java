
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import monodaipoly.dao.CasillaDAO;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class CasillaServicioImpl implements CasillaServicio{
    
   private CasillaDAO casillaDAO;
   
    @Autowired
    @Required
    public void setCasillaDAO(CasillaDAO casillaDAO){
        this.casillaDAO=casillaDAO;
    }
    
    
    public void crear(Casilla casilla) {
        casillaDAO.insert(casilla);
    }

   
    public void buscar(Key idCasilla) {
        casillaDAO.find(Casilla.class, idCasilla);
    }

 
    public void borrar(Casilla casilla) {
        casillaDAO.remove(casilla);
    }
    @Override
    public Jugador buscarDueno(Key idCasilla){
        return casillaDAO.buscarDueno(idCasilla);
    }
    
}
