package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Rol;


public interface RolDAO extends GenericDAO<Rol, Key>{

        public Rol buscarRol(String nombre);

}
