package com.example.filesmanegar.services;
import com.example.filesmanegar.model.*;
import com.example.filesmanegar.repository.GroupRepository;
import com.example.filesmanegar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.filesmanegar.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private  GroupRepository groupRepository;
    @Autowired
    private  UserRepository userRepository;

//    public FileService(FileRepository fileRepository,
//                       GroupRepository groupRepository,
//                       UserRepository userRepository){this.fileRepository = fileRepository;
//        this.groupRepository = groupRepository;
//        this.userRepository = userRepository;
//    }
    public void inity(String fileName, String groupName)
    {
        Path path = Paths.get("C:\\Users\\ahmad\\Desktop\\internetapp\\filesmanegar\\groups\\" + groupName + "\\" + fileName+".txt");
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*//*/*/"+path);
        try {
            Path createdFilePath = Files.createFile(path);
            System.out.println("Created a file at : "+createdFilePath);
        }
        catch (IOException e) {
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*//*/*/");
            e.printStackTrace();
        }
    }
    public FileModel createFile(FileModel fileModel, String groupName) {
        boolean isExistesFile = fileRepository.findFileByName(fileModel.getFileName()).isPresent();
        boolean isExistesGroup = groupRepository.findGroupByName(groupName).isPresent();
        if(isExistesFile)
        {
            throw new IllegalStateException(" This File Name Already  Exists ! ");
        }
        else if(!isExistesGroup)
        {
            throw new IllegalStateException(" This Group Name Dosen't  Exists ! ");
        }
        else
        {
            fileModel.getFileFolders().add(groupRepository.findGroupByName(groupName).get().getId());
            Date date = new Date();
            fileModel.setDateFileEdit(date.toString());
            //fileModel.setDateFileReserved(date.toString());
            fileModel.setDateFileUpload(date.toString());
            fileRepository.save(fileModel);
            return fileModel;
        }
    }

    public List<FileModel> getAllFiles() {
        List<FileModel> fileModels
                = fileRepository.findAll();

        return fileModels;
    }

    public String deleteFile(FileModel fileModel,String userName) {
        boolean isfileExistes = fileRepository
                .findFileByName(fileModel.getFileName()).isPresent();
        if (!isfileExistes) {
            throw new IllegalStateException(" File Dose Not Exists ! ");
        }
        else {
            FileModel fily = fileRepository.findFileByName(fileModel.getFileName()).get();
            if(fily.getFileOwnreName().equals(userName) && !(fily.isBookedUp()))
            {
                fileRepository.delete(fily);
                return "true";
            }
            else
            {
                throw new IllegalStateException(" You Are Not Owner To This File ! ");
            }
        }
    }
    public String deletfileFromGroup(FileModel fileModel, String groupName, String useerName)
    {
        boolean isExistesFile = fileRepository.findFileByName(fileModel.getFileName()).isPresent();
        boolean isExistesGroup = groupRepository.findGroupByName(groupName).isPresent();
        boolean isExistesUser = userRepository.findUserByName(useerName).isPresent();
        if(!isExistesFile)
        {
            throw new IllegalStateException(" This File Dose Not Exists ! ");
        }
        else if(!isExistesGroup)
        {
            throw new IllegalStateException(" This Group Dose Not Exists ! ");
        }
        else if(!isExistesUser)
        {
            throw new IllegalStateException(" This User Dose Not Exists ! ");
        }
        else if(fileRepository.findFileByName(fileModel.getFileName()).get().getFileOwnreName().equals(useerName))
        {
            ArrayList<Long> Temp = new ArrayList<>();
            for (Long id:groupRepository.findGroupByName(groupName).get().getGroupFiles()
                 ) {
                if(id == fileRepository.findFileByName(fileModel.getFileName()).get().getId2())
                {
                    if(fileRepository.findFileByName(fileModel.getFileName()).get().getFileFolders().isEmpty())
                    {
                        deleteFile(fileModel, useerName);
                    }
                }
                else {
                    Temp.add(id);
                }
            }
            groupRepository.findGroupByName(groupName).get().setGroupFiles(Temp);
        }
        return "Deleted";
    }
    public GroupModel addFileToGroup(FileModel fileModel, String groupName, String userName)
    {
        boolean isfileExistes = fileRepository
                .findFileByName(fileModel.getFileName()).isPresent();
        boolean isgroupExistes = groupRepository.findGroupByName(groupName).isPresent();
        if(!isgroupExistes)
        {
            throw new IllegalStateException(" This Group Dosen't Found ! ");
        }
        else if (!isfileExistes) {
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*//*/*/");
            inity(fileModel.getFileName(),groupName);
            createFile(fileModel,groupName);
            return groupRepository.findGroupByName(groupName).get();
        }
        else{
            System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*//*/*/");
            inity(fileModel.getFileName(),groupName);
            groupRepository.findGroupByName(groupName).get().getGroupFiles().add(fileModel.getId2());
            return groupRepository.findGroupByName(groupName).get();
        }
    }

    public FileModel getFileById(Long id) {
        FileModel fileModel = fileRepository.findById(id).get();
        return fileModel;
    }
    public FileModel updateFile(Long id, FileModel fileModel) {
        FileModel fileModelEntity = fileRepository.findById(id).get();
        fileModelEntity.setFilePath(fileModel.getFilePath());
        fileModelEntity.setFileName(fileModel.getFileName());
        fileModelEntity.setBookedUp(fileModel.isBookedUp());
        fileModelEntity.setId2(fileModel.getId2());
        fileRepository.save(fileModel);
        return fileModel;
    }
    public boolean isFileBooked(String fileName)
    {
        boolean isfileExistes = fileRepository
                .findFileByName(fileName).isPresent();
        if (!isfileExistes) {
            throw new IllegalStateException(" File Dose Not Exists ! ");
        }
        else
        {
            FileModel fily = fileRepository.findFileByName(fileName).get();
            if(fily.isBookedUp())
            {
                return true;
            }
            else
            {
                return false;
            }

        }

    }
    public List<FileModel> groupFilesIsnotBooked(GroupModel groupModel)
    {
        List<Long> groupFiles = groupRepository.findGroupByName(groupModel.getGroupName()).get().getGroupFiles();
        List<FileModel> Temp2=new ArrayList<>();
        FileModel Temp;
        for (Long id:groupFiles
             ) {
            Temp = fileRepository.findFileById(id).get();
            if(!Temp.isBookedUp())
            {
                Temp2.add(Temp);
            }
//            else
//            {
//                Temp2.add(Temp);
//            }
        }
        return Temp2;
    }
    public FileModel FileBooked(String fileName) {
        boolean isfileExistes = fileRepository
                .findFileByName(fileName).isPresent();
        if (!isfileExistes) {
            throw new IllegalStateException(" File Dose Not Exists ! ");
        } else {
            FileModel fily = fileRepository.findFileByName(fileName).get();
            fily.setBookedUp(true);
            fileRepository.save(fily);
            return fily;
        }
    }

    public FileModel cancelResevedFile(String fileName) {
        boolean isfileExistes = fileRepository
                .findFileByName(fileName).isPresent();
        if (!isfileExistes) {
            throw new IllegalStateException(" File Dose Not Exists ! ");
        } else {
            FileModel fily = fileRepository.findFileByName(fileName).get();
            fily.setBookedUp(false);
            fileRepository.save(fily);
            return fily;
        }
    }

    public String FilesBooked(List<FileModel> filesName) {

        for(FileModel file :filesName){
            boolean isfileExistes = fileRepository
                    .findFileByName(file.getFileName()).isPresent();
            if (!isfileExistes) {
                throw new IllegalStateException(" File Dose Not Exists ! ");
            }

        }

        for(FileModel file :filesName){
            FileModel fily = fileRepository.findFileByName(file.getFileName()).get();
            if(fily.isBookedUp()){
                throw new IllegalStateException(" there are File is blocked ! ");

            }}
        for(FileModel file :filesName){
            FileModel fily = fileRepository.findFileByName(file.getFileName()).get();
            fily.setBookedUp(true);
            fileRepository.save(fily);


        }
        return "success";
    }

    public FileModel readFile(String fileName){
        FileModel file = fileRepository.findFileByName(fileName).get();
        return file;
    }

    public String showUserNameReserv(String fileName){
        Boolean result =isFileBooked(fileName);
        if(result){
            FileModel file =fileRepository.findFileByName(fileName).get();
            int n =file.getReports().size();
            return file.getReports().get(n-1).getUserReservedName();
        }
        return "non ";
    }

    public List<FileModel> getAllFilesForGroup(String groupName) {

        GroupModel group   = groupRepository.findGroupByName(groupName).get();

        return  group.getFiles();
    }

    public List<FileModel> getAllFilesForUsr(String userName) {

        List<FileModel> file   = fileRepository.findFileByOwnerName(userName).get();

        return  file;
    }
}
