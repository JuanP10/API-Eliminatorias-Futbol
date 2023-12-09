package com.example.eliminatoriasapi.security.controllers;
import java.util.HashSet;
import java.util.Set;
import com.example.eliminatoriasapi.security.dtos.JwtDto;
import com.example.eliminatoriasapi.security.dtos.LoginUser;
import com.example.eliminatoriasapi.security.dtos.NewUser;
import com.example.eliminatoriasapi.security.entities.Message;
import com.example.eliminatoriasapi.security.entities.Role;
import com.example.eliminatoriasapi.security.entities.User;
import com.example.eliminatoriasapi.security.enums.RoleList;
import com.example.eliminatoriasapi.security.jwt.JwtProvider;
import com.example.eliminatoriasapi.security.services.RoleService;
import com.example.eliminatoriasapi.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final RoleService roleService;

    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
                          UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult){
        if(bidBindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        try {
            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDto jwtDto = new JwtDto(jwt);
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> register(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUserName(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roleService.getByRoleName(RoleList.ROLE_USER).ifPresent(roles::add);
        if (newUser.getRoles().contains(RoleList.ROLE_ADMIN))
            roleService.getByRoleName(RoleList.ROLE_ADMIN).ifPresent(roles::add);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesión"), HttpStatus.CREATED);
    }


}
