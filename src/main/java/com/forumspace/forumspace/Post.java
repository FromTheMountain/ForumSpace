package com.forumspace.forumspace;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Post {
    private static SimpleDateFormat format = 
        new SimpleDateFormat("EEEEE dd MMMMM yyyy HH:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String content;

    private String poster;

    private Date postedAt = new Date();

    public String postedAtString() {
        return format.format(postedAt);        
    }
}
