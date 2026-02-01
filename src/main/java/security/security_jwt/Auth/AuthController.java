package security.security_jwt.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.security_jwt.Auth.Models.AuthResponse;
import security.security_jwt.Auth.Models.LoginRequest;
import security.security_jwt.Auth.Models.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req){
        return ResponseEntity.created(authService.register(req));
    }
}
