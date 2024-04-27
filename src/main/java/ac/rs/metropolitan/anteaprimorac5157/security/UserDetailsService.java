package ac.rs.metropolitan.anteaprimorac5157.security;

import ac.rs.metropolitan.anteaprimorac5157.entity.User;
import ac.rs.metropolitan.anteaprimorac5157.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return new ac.rs.metropolitan.anteaprimorac5157.security.UserDetails(user);
    }
}
