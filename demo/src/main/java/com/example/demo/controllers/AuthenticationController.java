
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
import org.springframework.security.core.Authentication;
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
       final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
       UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       String token = tokenManager.generateToken(userDetails);
       User userEntity = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()-> new UsernameNotFoundException("пользователь не найден"));
       UserDto userDto = userMapper.toDto(userEntity);
       return new JwtResponse(token, userDto);

    }
}