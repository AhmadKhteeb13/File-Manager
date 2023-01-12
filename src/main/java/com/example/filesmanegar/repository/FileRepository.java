package com.example.filesmanegar.repository;
import com.example.filesmanegar.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Long>{
    @Query("SELECT s FROM FileModel s WHERE s.fileName = ?1")
    Optional<FileModel> findFileByName(String name);
    @Query("SELECT s FROM FileModel s WHERE s.id2 = ?1")
    Optional<FileModel> findFileById(Long id2);

    @Query("SELECT s FROM FileModel s WHERE s.fileOwnreName = ?1")
    Optional<List<FileModel>> findFileByOwnerName(String name);

}
