package com.example.filesmanegar.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

@Getter
@Setter
@Entity
@Data
@Table(name = "groups")
//@Builder
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    @Column(unique = true)
    private String groupName;
    private String groupOwnerName;
    private String groupPath;
    @Transient
    private ArrayList<Long> groupFiles  = new ArrayList<>();
    @Transient
    private List<Long> groupUsers = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "groupFiles",joinColumns = {
            @JoinColumn(name = "group_id",referencedColumnName = "group_id")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "file_id",referencedColumnName = "id2")
            }
    )

    private List<FileModel> files;
    public GroupModel() {
    }

    public GroupModel(String groupName, String groupOwnerName, String groupPath) {
        this.groupName = groupName;
        this.groupOwnerName = groupOwnerName;
        this.groupPath = groupPath;
    }
}
