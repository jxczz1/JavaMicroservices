package co.com.poli.users.services;

import co.com.poli.users.entities.User;
import co.com.poli.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {
    private final UserRepository userRepository;
    @Override
    public void save(User user) {
      userRepository.save(user);
    }

    @Override
    public void delete(User user) {
     userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
