package com.example.filesmanegar.repository;

import com.example.filesmanegar.model.FileModel;
import com.example.filesmanegar.model.GroupModel;
import com.example.filesmanegar.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface reportRepository extends JpaRepository<Report,Long> {

//    @Query("SELECT s FROM Report s WHERE s.dateFileReserved = ?1")
//    Optional<Report> findGrouByDate(Date dateFileReserved);
//
   @Query("SELECT s FROM Report s WHERE s.userReservedName = ?1")
   Optional <Report> findReportByuserReservedName(String name);
}
