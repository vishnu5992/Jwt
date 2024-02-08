package com.auth.jwt.repository;

import com.auth.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

 Optional<User> findByEmailIgnoreCase(String username);

 @Query("from User u where upper(u.email) = upper(:email)  and u.isDeleted = :flag")
 User findByEmailId(@Param("email") String email, @Param("flag") boolean flag);
}
