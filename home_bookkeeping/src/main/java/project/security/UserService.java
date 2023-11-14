package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dao.UserDbDao;
import project.model.User;


@Service
public class UserService implements UserDetailsService {

    private final UserDbDao userRepository;

    @Autowired
    public UserService(UserDbDao userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String userlogin) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(userlogin);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}