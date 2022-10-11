package com.forumspace.forumspace;

import java.util.ArrayList;
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
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class ForumGroup {

    public ForumGroup(String name) {
        this.name = name;
        forums = new ArrayList<Forum>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String name;

    @OneToMany
    private final List<Forum> forums;
}
