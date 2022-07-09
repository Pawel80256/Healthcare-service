package com.healthcare_service.authentication;

import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.repository.AdminRepository;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(doctorRepository.existsByUsername(username)){
            var doctor = doctorRepository.findByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority("DOCTOR"));}};
            return new User(doctor.getUsername(), doctor.getPassword(), authorities);
        }
        if(clientRepository.existsByUsername(username)){
            var client = clientRepository.findByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority("CLIENT"));}};
            return new User(client.getUsername(),client.getPassword(), authorities);
        }
        if(username.equals("admin")){
            var admin = adminRepository.findByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority("ADMIN"));}};
            return new User(admin.getUsername(),admin.getPassword(), authorities);
        }else {
            throw new NotFoundException("Nie znaleziono uzytkownika");
        }
    }
}
