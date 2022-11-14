package com.ar.Grupo3.Security.Controller;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.Grupo3.Security.Dto.JwtDto;
import com.ar.Grupo3.Security.Dto.LoginUsuario;
import com.ar.Grupo3.Security.Entity.Rol;
import com.ar.Grupo3.Security.Entity.Usuario;
import com.ar.Grupo3.Security.Enums.NombresRol;
import com.ar.Grupo3.Security.jwt.JwtProvider;
import com.ar.Grupo3.seguridad.negocio.DaoRolImpl;
import com.ar.Grupo3.seguridad.negocio.DaoUsuarioImpl;

@RestController
@RequestMapping("/gestor/auth")
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    DaoUsuarioImpl usuarioService;
    @Autowired
    DaoRolImpl rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Usuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<Object>(new Mensaje("Campos Mal colocados"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existeNombreUsuario(nuevoUsuario.getUsername())) {
            return new ResponseEntity<Object>(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        // Usuario us = new Usuario(nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.obtenerNombreRol(NombresRol.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("ADMIN")) {
            roles.add(rolService.obtenerNombreRol(NombresRol.ROLE_ADMIN).get());
        }

        nuevoUsuario.setRoles(roles);
        usuarioService.agregar(nuevoUsuario);

        return new ResponseEntity<Object>(new Mensaje("Usuario ha sido guardado asdsdasdasdasdas"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos incorrectos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getUsername(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }

}
