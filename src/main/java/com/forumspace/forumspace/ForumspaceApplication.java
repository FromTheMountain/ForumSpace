package com.forumspace.forumspace;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.forumspace.forumspace.data.ForumGroupRepository;
import com.forumspace.forumspace.data.ForumRepository;

@SpringBootApplication
public class ForumspaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForumspaceApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(
			ForumRepository forumRepo, ForumGroupRepository groupRepo) {
		return args -> {
			addToRepos("Food", 
				Arrays.asList("Cookie", "Donut", "Pizza"), forumRepo, groupRepo);
			addToRepos("Drink", 
				Arrays.asList("Tea", "Coffee", "Vodka"), forumRepo, groupRepo);
			addToRepos("Movie", 
				Arrays.asList("Harry Potter", "Star Wars", "Star Trek"), forumRepo, groupRepo);
		};
	}

	private void addToRepos(String forumGroupName, List<String> forumNames,
			ForumRepository forumRepo, ForumGroupRepository groupRepo) {
		
		ForumGroup group = new ForumGroup(forumGroupName);
			
		for (String forumString : forumNames) {
			Forum forum = new Forum(forumString);
			forumRepo.save(forum);
			group.getForums().add(forum);
		}

		groupRepo.save(group);
	}
}
