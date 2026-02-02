package security.security_jwt.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.security_jwt.Auth.Models.AuthResponse;
import security.security_jwt.Auth.Models.LoginRequest;
import security.security_jwt.Auth.Models.RegisterRequest;

@RestController
@RequiredArgsConstructor // mejor que All porque unicamente incluye los final
@RequestMapping("/auth")
public class AuthController {
    /* recuerda que la declaramos como constante
    por ello necesitamos un constructor para
    inicializarla */
    private final AuthService authService;

    // para utilizar Autowired no debe ser final

    /* esto puede reemplazar lombok
    public AuthController(AuthService authService){
        this.authService = authService;
    } */

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req){
        return ResponseEntity.ok(authService.register(req));
    }
}
