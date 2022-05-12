package com.sasicodes.redditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

//whenever the user is registered we generate a token and stored it to the db, and send as a part of link
//when the user clicks the activation link we will check the token and we will enable the user
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerficationToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "token_id")
    private Long id;
    private String token;
    @OneToOne(fetch = LAZY)
    private User user;
    private Instant expiryDate;

}
