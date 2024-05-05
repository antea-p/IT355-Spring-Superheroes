package ac.rs.metropolitan.anteaprimorac5157;

import ac.rs.metropolitan.anteaprimorac5157.controller.MainController;
import ac.rs.metropolitan.anteaprimorac5157.controller.PublisherController;
import ac.rs.metropolitan.anteaprimorac5157.controller.SuperheroController;
import ac.rs.metropolitan.anteaprimorac5157.controller.SuperpowerController;
import ac.rs.metropolitan.anteaprimorac5157.repository.PublisherRepository;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperheroRepository;
import ac.rs.metropolitan.anteaprimorac5157.repository.SuperpowerRepository;
import ac.rs.metropolitan.anteaprimorac5157.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	// https://www.jvt.me/posts/2021/06/25/spring-context-test/
	@Test
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();

		assertThat(context.getBean(MainController.class)).isNotNull();

		assertThat(context.getBean(SuperheroController.class)).isNotNull();
		assertThat(context.getBean(SuperheroRepository.class)).isNotNull();

		assertThat(context.getBean(SuperpowerController.class)).isNotNull();
		assertThat(context.getBean(SuperpowerRepository.class)).isNotNull();

		assertThat(context.getBean(PublisherController.class)).isNotNull();
		assertThat(context.getBean(PublisherRepository.class)).isNotNull();

		assertThat(context.getBean(UserRepository.class)).isNotNull();
	}

}
