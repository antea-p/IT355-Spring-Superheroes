package ac.rs.metropolitan.anteaprimorac5157.user;

import ac.rs.metropolitan.anteaprimorac5157.entity.User;
import ac.rs.metropolitan.anteaprimorac5157.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        Optional<User> actualResult = userRepository.findByUsername("admin");
        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getUsername()).isEqualTo("admin");
    }
}
