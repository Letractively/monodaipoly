package monodaipoly.dao;

import monodaipoly.persistencia.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl extends GenericDAOImpl <Usuario, String> implements UsuarioDAO{

}
