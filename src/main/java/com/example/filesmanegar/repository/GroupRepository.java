package com.example.filesmanegar.repository;
import com.example.filesmanegar.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long>{
    @Transactional
    @Modifying
    @Query("delete from GroupModel g")
    int deleteFirstBy();
    @Query("SELECT s FROM GroupModel s WHERE s.groupName = ?1")
    Optional<GroupModel> findGroupByName(String name);

    @Query("SELECT s FROM GroupModel s WHERE s.groupOwnerName = ?1")
    Optional<List<GroupModel>> findGroupByOwnerName(String groupOwnerName);
}
