package com.example.filesmanegar.repository;
import com.example.filesmanegar.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("SELECT s FROM UserModel s WHERE s.userName = ?1")
    Optional<UserModel> findUserByName(String userName);

    @Query("SELECT s FROM UserModel s WHERE s.email = ?1")
    Optional<UserModel> findUserByEmail(String name);
}
