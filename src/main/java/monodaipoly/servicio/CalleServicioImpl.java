package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.dao.CalleDAO;
import monodaipoly.persistencia.Calle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;


@Service
public class CalleServicioImpl implements CalleServicio{

    private CalleDAO calleDAO;

    @Autowired
    @Required
    public void setCalleDAO(CalleDAO calleDAO){
        this.calleDAO=calleDAO;
    }

    @Override
    public void crear(Calle calle) {
        this.calleDAO.insert(calle);
    }

    @Override
    public Calle buscar(Key idCalle) {
        return this.calleDAO.find(Calle.class, idCalle);
    }

    @Override
    public void borrar(Calle calle) {
        this.calleDAO.remove(calle);
    }

    @Override
    public void actualizar(Calle calle) {
        this.calleDAO.update(calle);
    }

    @Override
    public List<Calle> buscarPorColor(String color) {
        return this.calleDAO.buscarPorColor(color);
    }

}
