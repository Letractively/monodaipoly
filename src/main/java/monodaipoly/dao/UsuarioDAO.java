package monodaipoly.dao;

import java.util.List;
import monodaipoly.persistencia.Usuario;

public interface UsuarioDAO extends GenericDAO <Usuario, String> {
    
    public List<Usuario> conseguirUsuarios();
}
