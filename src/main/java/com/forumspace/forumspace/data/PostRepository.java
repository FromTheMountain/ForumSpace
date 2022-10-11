package com.forumspace.forumspace.data;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forumspace.forumspace.Post;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
