package dk.gundmann.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.gundmann.security.IsAdmin;
import dk.gundmann.userclient.UserService;

@RestController
@RequestMapping("/")
public class NewsController {

	private NewsRepository newsRepository;
	private UserService userNotifyer;

	public NewsController(NewsRepository newsRepository, UserService userNotifyer) {
		this.newsRepository = newsRepository;
		this.userNotifyer = userNotifyer;
	}
	
	@GetMapping("/all")
	public Iterable<News> get() {
		return this.newsRepository.findAll();
	}
	
	@PostMapping("/update")
	@IsAdmin
	public void set(@RequestBody News news) {
		if (news.getId() == null) {
			this.userNotifyer.notifiy("Nyheder");
		}
		this.newsRepository.save(news);
	}
	
	@PostMapping("/delete/{id}")
	@IsAdmin
	public void delete(@PathVariable String id) {
		this.newsRepository.deleteById(id);
		
	}
	
}
