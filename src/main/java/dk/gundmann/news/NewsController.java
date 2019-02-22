package dk.gundmann.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dk.gundmann.security.IsAdmin;

@RestController
public class NewsController {

	private NewsRepository newsRepository;

	public NewsController(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	@GetMapping
	public Iterable<News> get() {
		return this.newsRepository.findAll();
	}
	
	@PostMapping
	@IsAdmin
	public void set(@RequestBody News news) {
		this.newsRepository.save(news);
	}
	
}
