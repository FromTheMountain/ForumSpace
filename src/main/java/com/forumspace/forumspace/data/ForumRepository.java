package com.forumspace.forumspace.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumspace.forumspace.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long> {
    public Forum findByNameIgnoringCase(String name);
    
}
