package com.forumspace.forumspace;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<Post>();    
}
