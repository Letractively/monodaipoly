/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import monodaipoly.persistencia.Usuario;

/**
 *
 * @author adler
 */
public interface UsuarioServicio {
    public void crear(Usuario usuario);
    public Usuario buscar(String nick);
    public void borrar(Usuario usuario);
    public void borrarPorClave(String nick);
    public void actualizar(Usuario usuario);

}
