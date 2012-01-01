package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Calle;


public interface CalleServicio {
    public void crear(Calle calle);
    public Calle buscar(Key idCalle );
    public void borrar(Calle calle);
    public void actualizar(Calle calle);
    public List<Calle> buscarPorColor(String color);
}
