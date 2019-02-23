package dk.gundmann.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.gundmann.security.IsAdmin;

@RestController
@RequestMapping("/")
public class NewsController {

	private NewsRepository newsRepository;

	public NewsController(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	@GetMapping("all")
	public Iterable<News> get() {
		return this.newsRepository.findAll();
	}
	
	@PostMapping("/update")
	@IsAdmin
	public void set(@RequestBody News news) {
		this.newsRepository.save(news);
	}
	
	@PostMapping("/delete/{id}")
	@IsAdmin
	public void deletee(@PathVariable String id) {
		this.newsRepository.deleteById(id);
	}
	
}
