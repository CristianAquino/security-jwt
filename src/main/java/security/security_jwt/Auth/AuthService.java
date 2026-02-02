package security.security_jwt.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.security_jwt.Auth.Models.AuthResponse;
import security.security_jwt.Auth.Models.LoginRequest;
import security.security_jwt.Auth.Models.RegisterRequest;
import security.security_jwt.Jwt.JwtService;
import security.security_jwt.User.Role;
import security.security_jwt.User.User;
import security.security_jwt.User.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest req) {
        // validamos credenciales comparando la contraseña enviada con la registrada en BD
        // usando UserDetailService
        // si no coincide salta exception
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
        // una vez validado obtenemos los datos
        UserDetails user = userRepository.findByUsername(req.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .country(req.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
