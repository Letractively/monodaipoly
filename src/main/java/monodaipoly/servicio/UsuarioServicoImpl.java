/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import monodaipoly.dao.*;
import monodaipoly.dao.UsuarioDAO;
import monodaipoly.persistencia.Rol;
import monodaipoly.persistencia.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author adler
 */
@Service("usuarioServicio")
public class UsuarioServicoImpl implements UsuarioServicio{
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Autowired
    @Required
    public void setUsuarioDAO(UsuarioDAO usuarioDAO){
        this.usuarioDAO=usuarioDAO;
    }

    @Autowired
    @Required
    public void setRolDAO(RolDAO rolDAO){
        this.rolDAO=rolDAO;
    }

    public Usuario getUsuario(String idUsuario) {
        return usuarioDAO.find(Usuario.class, idUsuario);
    }

    public Collection<Rol> getRoles(Usuario usuario) {
        Collection<Rol> roles = new ArrayList<Rol>();
        for (Key keyRol : usuario.getRoles()) {
            Rol rol = rolDAO.find(Rol.class, keyRol);
            roles.add(rol);
        }
        return roles;
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

    @Override
    @PostConstruct
    public void preload_usuarios() {
        Usuario u1=new Usuario();
        u1.setNick("admin");
        u1.setContrasena("123456");
        Rol r1 = new Rol();
        r1.setNombre("ROLE_ADMIN");
        this.anadirRol(u1, r1);
        this.crear(u1);
    }

    @Override
    public void anadirRol(Usuario usuario, Rol rol) {
        rolDAO.insert(rol);
        System.out.println(rol.getId());
        usuario.getRoles().add(rol.getId());
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            return getUsuario(username);
        } else {
            return null;
        }
    }

    @PreAuthorize("isAuthenticated()")
    public boolean isAdmin() {
        final String ROLE_ADMIN = "ROLE_ADMIN";
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            User user = ((User) auth.getPrincipal());
            Collection<GrantedAuthority> roles = user.getAuthorities();
            if (roles != null) {
                Iterator<GrantedAuthority> it = roles.iterator();
                while (!isAdmin && it.hasNext()) {
                    GrantedAuthority rol = it.next();
                    if (rol.getAuthority().equals(ROLE_ADMIN)) {
                        isAdmin = true;
                    }
                }
            }
        }
        return isAdmin;
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {
        System.out.println("AQUI UsuarioServiceImpl loadUserByUsername usuarioDao="+usuarioDAO);
        Usuario usuario = usuarioDAO.find(Usuario.class, nombreUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + nombreUsuario);
        } else {
            return makeUser(usuario);
        }
    }
    
    private org.springframework.security.core.userdetails.User makeUser(Usuario usuario) {
        return new org.springframework.security.core.userdetails.User(usuario.getNick(), usuario.getContrasena(),
                true, true, true, true, makeGrantedAuthorities(usuario));
    }
    
    private Collection<GrantedAuthority> makeGrantedAuthorities(Usuario usuario) {
        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        int i = 0;
        for (Rol rol : getRoles(usuario)) {
            result.add(new GrantedAuthorityImpl(rol.getNombre()));
        }
        return result;
    }


}
