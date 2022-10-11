package com.forumspace.forumspace.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumspace.forumspace.ForumGroup;

public interface ForumGroupRepository extends JpaRepository<ForumGroup, Long> {
    
}
