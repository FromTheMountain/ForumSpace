package com.forumspace.forumspace.data;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumspace.forumspace.ForumThread;

@Transactional
public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
    
}
