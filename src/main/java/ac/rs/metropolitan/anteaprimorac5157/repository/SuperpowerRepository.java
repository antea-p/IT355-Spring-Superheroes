package ac.rs.metropolitan.anteaprimorac5157.repository;

import ac.rs.metropolitan.anteaprimorac5157.entity.Superpower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperpowerRepository extends JpaRepository<Superpower, Long>{
}
