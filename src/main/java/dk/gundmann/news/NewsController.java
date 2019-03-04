package dk.gundmann.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.gundmann.security.IsAdmin;
import dk.gundmann.userclient.UserClient;

@RestController
@RequestMapping("/")
public class NewsController {

	private NewsRepository newsRepository;
	private UserClient userClient;

	public NewsController(NewsRepository newsRepository, UserClient userClient) {
		this.newsRepository = newsRepository;
		this.userClient = userClient;
	}
	
	@GetMapping("/all")
	public Iterable<News> get() {
		return this.newsRepository.findAll();
	}
	
	@PostMapping("/update")
	@IsAdmin
	public void set(@RequestBody News news) {
		this.newsRepository.save(news);
		this.userClient.notifiy("Nyheder");
	}
	
	@PostMapping("/delete/{id}")
	@IsAdmin
	public void delete(@PathVariable String id) {
		this.newsRepository.deleteById(id);
		
	}
	
}
