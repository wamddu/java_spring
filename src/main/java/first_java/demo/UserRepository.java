package first_java.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    Long id(Long id);

    Long Id(Long id);
}