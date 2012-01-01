package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Calle;


public interface CalleDAO extends GenericDAO <Calle, Key>{
    public List<Calle> buscarPorColor(String color);
}
