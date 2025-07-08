package com.example.demo.mapper;


import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {
    default UserDetails toUserDetails(User user) {
        if (user == null) {
            return null;
        }
        String authority = "ROLE_" + user.getRole().name();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword().getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(authority))
        );
    }
}

