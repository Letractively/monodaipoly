/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import java.util.List;
import monodaipoly.persistencia.Rol;
import monodaipoly.persistencia.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 *
 * @author adler
 */
public interface UsuarioServicio extends UserDetailsService{
    public void crear(Usuario usuario);
    public Usuario buscar(String nick);
    public void borrar(Usuario usuario);
    public void borrarPorClave(String nick);
    public void actualizar(Usuario usuario);
    void preload_usuarios();
    void anadirRol(Usuario usuario, Rol rol);
    public Rol buscarRol(String nombre);
    Usuario getCurrentUser();
    boolean isAdmin();
    public List conseguirUsuarios();

}
