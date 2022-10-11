package com.forumspace.forumspace.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.forumspace.forumspace.data.ForumGroupRepository;

@Controller
public class HomeContoller {
    private ForumGroupRepository forumGroupRepository;

    public HomeContoller(ForumGroupRepository forumGroupRepository) {
        this.forumGroupRepository = forumGroupRepository;
    }

    @ModelAttribute
    public void loadForumsToModel(Model model) {
        model.addAttribute("forumGroups", forumGroupRepository.findAll());
    }

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }
}
