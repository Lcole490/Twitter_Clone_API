package com.team_1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team_1.demo.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user_table WHERE username=?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * from user_table " +
                    "WHERE ID IN " +
                    "( SELECT followers_id FROM followers_following WHERE following_id=?1)" +
                    " AND deleted=false", nativeQuery = true)
    List<User> getActiveFollowers(Long id);

    @Query(value = "SELECT * from user_table " +
                    "WHERE ID IN " +
                    "( SELECT following_id FROM followers_following WHERE followers_id=?1) " +
                    "AND deleted=false", nativeQuery = true)
    List<User> getActiveFollowing(Long id);

    Optional<User> findByCredentialsUsernameAndDeletedFalse(String username);

    @Query(value = "SELECT * FROM user_table " +
                    "INNER JOIN user_mentions " +
                    "ON user_table.id=user_id " +
                    "WHERE tweet_id=?1", nativeQuery = true)
    List<User> getMentioned(Long tweet_id);


}
