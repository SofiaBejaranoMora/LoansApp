package org.una.programmingIII.loans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.una.programmingIII.loans.Security.JwtUtil;
import org.una.programmingIII.loans.models.User;
import org.una.programmingIII.loans.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new Exception("Usuario no encontrado");

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}