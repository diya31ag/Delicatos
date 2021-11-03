package com.example.delicatos.Services;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImplementation {
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImplementation userDetailsService;

    @Autowired
    public SecurityServiceImplementation(AuthenticationManager authenticationManager, UserDetailsServiceImplementation userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public void notVerified() {
        SecurityContextHolder.clearContext();
    }

    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,password,userDetails.getAuthorities()
        );
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}