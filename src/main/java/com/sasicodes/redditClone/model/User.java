package com.sasicodes.redditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name = "user_id")
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
    //here the user is enabled after completing the email verification process
    private boolean enabled;
    @OneToMany(mappedBy = "user",fetch = LAZY)
    private List<Subreddit> subreddits = new ArrayList<>();


    //to be in sync for bi directional
    public void addSubReddits(Subreddit subreddit) {
        if(!subreddits.contains(subreddit)){
            subreddits.add(subreddit);
            subreddit.setUser(this);
        }
    }

    public void removeSubReddits(Subreddit subreddit) {
        if(subreddits.contains(subreddit)){
            subreddits.remove(subreddit);
            subreddit.setUser(null);
        }
    }

}
