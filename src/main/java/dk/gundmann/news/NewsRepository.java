package dk.gundmann.news;

import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, String> {
 
}
