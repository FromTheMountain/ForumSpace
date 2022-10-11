package com.forumspace.forumspace;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;

    @OneToMany
    private List<ForumThread> threads;
}
