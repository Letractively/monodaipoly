/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import monodaipoly.dao.UsuarioDAO;
import monodaipoly.persistencia.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

/**
 *
 * @author adler
 */
@Service
public class UsuarioServicoImpl implements UsuarioServicio{
    private UsuarioDAO usuarioDAO;

    @Autowired
    @Required
    public void setUsuarioDAO(UsuarioDAO usuarioDAO){
        this.usuarioDAO=usuarioDAO;
    }



    @Override
    public void crear(Usuario usuario) {
        usuarioDAO.insert(usuario);
    }

    @Override
    public Usuario buscar(String nick) {
        return usuarioDAO.find(Usuario.class, nick);
    }

    @Override
    public void borrar(Usuario usuario) {
        usuarioDAO.remove(usuario);
    }
    @Override
    public void borrarPorClave(String nick) {
        usuarioDAO.remove(Usuario.class, nick);
    }


    @Override
    public void actualizar(Usuario usuario) {
        usuarioDAO.update(usuario);
    }



}
