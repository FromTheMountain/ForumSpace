package com.forumspace.forumspace.web;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forumspace.forumspace.Forum;
import com.forumspace.forumspace.ForumThread;
import com.forumspace.forumspace.NewThreadForm;
import com.forumspace.forumspace.Post;
import com.forumspace.forumspace.data.ForumRepository;
import com.forumspace.forumspace.data.ForumThreadRepository;
import com.forumspace.forumspace.data.PostRepository;
import com.forumspace.forumspace.exceptions.ResourceNotFoundException;

@Controller
@RequestMapping("/forum")
public class ForumController {
    private ForumRepository forumRepo;
    private ForumThreadRepository threadRepo;
    private PostRepository postRepo;

    public ForumController(ForumRepository forumRepo, 
            ForumThreadRepository threadRepo,
            PostRepository postRepo) {
        
        this.forumRepo = forumRepo;
        this.threadRepo = threadRepo;
        this.postRepo = postRepo;
    }

    @GetMapping(path = "{forumName}")
    @Transactional
    public String showForum(Model model,
            @PathVariable("forumName") String forumName) {

        Forum forum = forumRepo.findByNameIgnoringCase(forumName);

        if (forum == null) {
            throw new ResourceNotFoundException("That forum does not exist.");
        }

        model.addAttribute("forum", forum);

        return "forum";
    }

    @GetMapping("/{forumName}/new")
    public String newThread(Model model,
            @PathVariable("forumName") String forumName) {
        
        model.addAttribute("forumName", forumName);
        return "new_thread";
    }

    @PostMapping("/{forumName}/new")
    @Transactional
    public String createThread(@Valid NewThreadForm form,
            Errors errors, 
            @PathVariable("forumName") String forumName) {

        Forum forum = forumRepo.findByNameIgnoringCase(forumName);

        Post post = new Post();
        post.setContent(form.getContent());
        post.setPoster(form.getPoster());
        postRepo.save(post);

        ForumThread thread = new ForumThread(form.getName());
        thread.getPosts().add(post);

        // Use the new Thread object with the correct ID.
        // We need this for redirecting the browser.
        thread = threadRepo.save(thread);

        forum.getThreads().add(thread);
        forumRepo.save(forum);

        return "redirect:/forum/" + forumName + "/" + thread.getId();
    }
}
