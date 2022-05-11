package com.sasicodes.redditClone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "subreddit_Id")
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(mappedBy = "subreddit",fetch = LAZY)
    private List<Post> posts =  new ArrayList<>();
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    private User user;

    //to be in sync for bi directional
    public void addPost(Post post) {
        if(!posts.contains(post)){
            posts.add(post);
            post.setSubreddit(this);
        }
    }

    public void removePost(Post post) {
        if(posts.contains(post)){
            posts.remove(post);
            post.setSubreddit(null);
        }
    }
}