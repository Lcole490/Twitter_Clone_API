package com.team_1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team_1.demo.entities.Tweet;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query(value = "SELECT * FROM tweet WHERE deleted=false ORDER BY posted DESC", nativeQuery = true)
    List<Tweet> getTweetsNotDeleted();

    @Query(value = "SELECT * FROM tweet " +
                    "INNER JOIN user_mentions " +
                    "ON tweet.id=tweet_id " +
                    "INNER JOIN user_table " +
                    "ON user_id=user_table.id " +
                    "WHERE user_mentions.user_id=?1 " +
                    "AND tweet.deleted=false " +
                    "ORDER BY posted DESC", nativeQuery = true)
    List<Tweet> getUserMentionedTweets(Long id);

    List<Tweet> findByRepostOfAndDeletedFalse(Tweet tweet);

    List<Tweet> findByContentContainingAndDeletedFalse(String s);

    @Query(value = "SELECT * FROM tweet WHERE inReplyTo=?1", nativeQuery = true)
    List<Tweet> getAllAfter(Long id);

}
