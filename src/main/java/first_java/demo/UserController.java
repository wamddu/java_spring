package first_java.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController{

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
        { return userRepository.findById(id).map(
            existing-> {
                existing.setUsername(user.getUsername());
                existing.setEmail(user.getEmail());
                return userRepository.save(existing);
            }).orElseThrow(()-> new RuntimeException("User not found!"));
        }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}