
package com.example.demo.controllers;
import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import com.example.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final JwtUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/api/login")
    public JwtResponse createToken(@RequestBody JwtRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
            ));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS");
        }
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("Пользователь" + request.getUsername() +"не найден"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());


        String token = tokenManager.generateToken(userDetails);
        UserDto userDto = userMapper.toDto(user);







        return new JwtResponse(token, userDto);
    }
}