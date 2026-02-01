package security.security_jwt.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security_jwt.Auth.Models.AuthResponse;
import security.security_jwt.Auth.Models.LoginRequest;
import security.security_jwt.Auth.Models.RegisterRequest;
import security.security_jwt.User.Role;
import security.security_jwt.User.User;
import security.security_jwt.User.UserRepository;

import java.net.URI;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest req) {
        return null;
    }

    public AuthResponse register(RegisterRequest req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(req.getPassword())
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .country(req.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse
                .builder()
                .token(null)
                .build();
    }
}
