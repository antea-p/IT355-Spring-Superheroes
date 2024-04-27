package ac.rs.metropolitan.anteaprimorac5157.repository;

import ac.rs.metropolitan.anteaprimorac5157.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByPublisherName(String publisherName);
}

