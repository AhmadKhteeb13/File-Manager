package com.example.filesmanegar.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Data
@Table(name = "files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id2;
    private String fileName;
    private String filePath;
    private boolean isBookedUp;
    @Transient
    private List<Long> fileFolders = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "file_id")
    private List<Report>  reports;
    @JsonIgnore
    @ManyToMany(mappedBy = "files",fetch = FetchType.LAZY)
    // @JsonBackReference
    private List<GroupModel> groups;
    private String fileOwnreName;
    private String dateFileReserved;
    private String dateFileUpload;
    private String dateFileEdit;
    private String dateFileReservedCancel;


}
