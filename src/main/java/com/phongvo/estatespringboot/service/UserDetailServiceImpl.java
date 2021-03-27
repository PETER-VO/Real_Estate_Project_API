package com.phongvo.estatespringboot.service;


import com.phongvo.estatespringboot.dto.MyUserDetail;
import com.phongvo.estatespringboot.entity.Role;
import com.phongvo.estatespringboot.entity.User;
import com.phongvo.estatespringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    final private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> userOptional = userRepository.findByUsernameAndEnable(username, true);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username : " + username));
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
        }
        MyUserDetail myUserDetail = new MyUserDetail(username,user.getPassword(),true,true,true,true,authorities);
        BeanUtils.copyProperties(user, myUserDetail);
        return myUserDetail;

//        return new org.springframework.security
//                .core.userdetails.User(user.getUsername(), user.getPassword(),
//                user.isEnable(), true, true,
//                true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
