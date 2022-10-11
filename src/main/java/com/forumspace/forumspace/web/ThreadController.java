package com.forumspace.forumspace.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forumspace.forumspace.ForumThread;
import com.forumspace.forumspace.Post;
import com.forumspace.forumspace.data.ForumThreadRepository;
import com.forumspace.forumspace.data.PostRepository;
import com.forumspace.forumspace.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/forum/{forum}/{threadId}")
public class ThreadController {
    private ForumThreadRepository threadRepo;
    private PostRepository postRepo;
    private ForumThread thread;

    public ThreadController(ForumThreadRepository threadRepo,
            PostRepository postRepo) {
        this.threadRepo = threadRepo;
        this.postRepo = postRepo;
    }

    @ModelAttribute(name = "newPost")
    public Post newPost() {
        return new Post();
    }

    @GetMapping
    public String showThread(Model model,
            @PathVariable long threadId) {
        
        Optional<ForumThread> thread = threadRepo.findById(threadId);

        if (!thread.isPresent()) {
            throw new ResourceNotFoundException("That thread does not exist.");
        }

        this.thread = thread.get();

        model.addAttribute("thread", thread.get());
                
        return "thread";
    }

    @PostMapping
    public String addPost(Post newPost, 
            @PathVariable String forum) {

        postRepo.save(newPost);
        
        this.thread.getPosts().add(newPost);
        threadRepo.save(this.thread);

        return "redirect:/forum/" + forum + "/" + this.thread.getId();
    }
}
