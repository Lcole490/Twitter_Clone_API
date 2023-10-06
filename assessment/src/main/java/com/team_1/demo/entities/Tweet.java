package com.team_1.demo.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Timestamp posted;

    private boolean deleted = false;

    private String content;

    @ManyToOne
    private Tweet repostOf;

    @ManyToOne
    private Tweet inReplyTo;

    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts;

    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies;

    @ManyToMany(mappedBy = "liked_tweets")
    private List<User> liked_by_users;

    @ManyToMany
    @JoinTable(name = "user_mentions",
    joinColumns = @JoinColumn(name = "tweet_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> mentionedUsers;

    @ManyToOne
    private User author;

    @ManyToMany
    @JoinTable(
            name = "tweet_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags;
}
