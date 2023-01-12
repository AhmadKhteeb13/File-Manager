package com.example.filesmanegar.services;
import com.example.filesmanegar.model.FileModel;
import com.example.filesmanegar.model.GroupModel;
import com.example.filesmanegar.repository.FileRepository;
import com.example.filesmanegar.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.FileSystemUtils;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;



    //*****************************************
    //private final Path root = Paths.get("groups");

    public void init(String groupName) {
        File file = new File("C:\\Users\\ahmad\\Desktop\\internetapp\\filesmanegar\\groups\\" + groupName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    public String createGroup(GroupModel groupModel) {
        boolean isGroupNameExist = groupRepository.findGroupByName(groupModel.getGroupName()).isPresent();
        if (isGroupNameExist) {
            throw new IllegalStateException(" Group Name Already Exists ! ");
        } else {
            init(groupModel.getGroupName());
            groupModel.setGroupPath("C:\\Users\\ahmad\\Desktop\\internetapp\\filesmanegar\\groups\\" +groupModel.getGroupName());
            groupRepository.save(groupModel);
            return "New Group";
        }
    }

    public List<GroupModel> getAllGroups() {
        List<GroupModel> groupModels
                = groupRepository.findAll();
        return groupModels;
    }

    public String deleteGroup(GroupModel groupModel) {
        boolean isGroupExistes = groupRepository
                .findGroupByName(groupModel.getGroupName()).isPresent();
        if (!isGroupExistes) {
            throw new IllegalStateException(" Group Dose Not Exists ! ");
        } else {
            GroupModel groupy = groupRepository.findGroupByName(groupModel.getGroupName()).get();
            List<Long > groupFiles = groupy.getGroupFiles();

            for (Long fileId:groupFiles
                 ) {
                if(fileRepository.findFileById(fileId).get().isBookedUp())
                {
                    throw new IllegalStateException(" Group Have Files Booked ! ");
                }
            }
            groupRepository.delete(groupRepository.findGroupByName(groupModel.getGroupName()).get());
        }
        return "Deleted";
    }

    public GroupModel getGroupById(Long id) {
        GroupModel groupModel = groupRepository.findById(id).get();
        return groupModel;
    }

    public GroupModel updateGroup(GroupModel groupModel) {
        GroupModel groupModelEntity = groupRepository.findGroupByName(groupModel.getGroupName()).get();
        groupModelEntity.setGroupFiles(groupModel.getGroupFiles());
        groupModelEntity.setGroupUsers(groupModel.getGroupUsers());
        groupModelEntity.setGroupName(groupModel.getGroupName());
        groupModelEntity.setId(groupModel.getId());
        groupRepository.save(groupModel);
        return groupModel;
    }

    public String addFileToGroup(GroupModel groupModel) {
        boolean isGroupExistes = groupRepository
                .findGroupByName(groupModel.getGroupName()).isPresent();
        if (!isGroupExistes) {
            throw new IllegalStateException(" Group Dose Not Exists ! ");
        } else {
            //fileService.createFile(groupModel.getGroupFileModels().get(0));
            groupRepository.findGroupByName(groupModel.getGroupName()).get().getGroupFiles().add(groupModel.getGroupFiles().get(0));
            return "true";
        }
    }

    public String showUserGroups(String groupOwnerName) {
        return groupRepository.findAll().toString();
    }

    public String addUserToGroup(GroupModel groupModel) {

        boolean isGroupExistes = groupRepository
                .findGroupByName(groupModel.getGroupName()).isPresent();
        if (!isGroupExistes) {
            throw new IllegalStateException(" Group Dose Not Exists ! ");
        } else {
            GroupModel groupModel2 = groupRepository.findGroupByName(groupModel.getGroupName()).get();
            if (groupModel2.getGroupOwnerName().equals(groupModel.getGroupOwnerName())) {
                //group2.getGroupUsers().add(group.getGroupUsers().get(0));
                return "true";
            } else {
                throw new IllegalStateException(" You Are Not Owner To This Group ! ");
            }
        }
    }
}
//    public String deletFileFromGroup(GroupModel request)
//    {
//        int i=0;
//        boolean isGroupExistes = groupRepository
//                .findGroupByName(request.getGroupName()).isPresent();
//        //FileModel Temp = fileRepository.findFileByName(request.getGroupFileModels().get(0).getFileName()).get();
////        if(!(Temp.getFileOwnreName().equals(request.getGroupOwnerName())))
////        {
////            throw new IllegalStateException(" You Are Not Owner This File ! ");
////        }
////        else if (!isGroupExistes) {
////            throw new IllegalStateException(" Group Dose Not Exists ! ");
////        }
////        else {
////            List<FileModel> groupFileModels = groupRepository.findGroupByName(request.getGroupName()).get().getGroupFileModels();
////            List<FileModel> groupFiles2 = new ArrayList<>();
////            while (i< groupFileModels.size())
////            {
////                if(request.getGroupFileModels().get(0).equals(groupFileModels.get(i)))
////                {
////
////                }
////                else
////                {
////                    groupFiles2.add(groupFileModels.get(i));
////                }
////            }
////            groupRepository.findGroupByName(request.getGroupName()).get().setGroupFileModels(groupFiles2);
////            return "Deleted";
////        }
//    }
//}
