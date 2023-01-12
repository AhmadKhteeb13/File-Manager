package com.example.filesmanegar.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@Table(name = "Reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Rep_id;
    private String userReservedName;
    private String dateFileReserved;

    private String dateFileEdit;
    private String dateFileReservedCancel;
    private String fileName;
@JsonIgnore

  @ManyToOne
@JoinColumn(name = "file_id")
   private FileModel file;


}
