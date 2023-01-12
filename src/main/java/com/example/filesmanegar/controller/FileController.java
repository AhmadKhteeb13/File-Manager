package com.example.filesmanegar.controller;
import com.example.filesmanegar.model.FileModel;
import com.example.filesmanegar.model.GroupModel;
import com.example.filesmanegar.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class FileController {
    private Logger logger=  LoggerFactory.getLogger(FileController.class);
    @Autowired
    private  FileService fileService;

    @PostMapping("/creatfile")
    public FileModel creatFile(@RequestBody FileModel request, @RequestParam(name = "groupName") String groupName)
    {
        return fileService.createFile(request, groupName);
    }

    @DeleteMapping("/deletfile")
    public String deletFile(@RequestBody FileModel fileModel, @RequestParam(name = "userName") String userName)
    {
        return fileService.deleteFile(fileModel, userName);
    }

    @PostMapping("/addFileToGroup")
    public GroupModel addFileToGroup(@RequestBody FileModel fileModel, @RequestParam(name = "groupName") String groupName, @RequestParam(name = "userName") String userName)
    {
        return fileService.addFileToGroup(fileModel,groupName,userName);
    }

    @DeleteMapping("/deletfileFromGroup")
    public String deletfileFromGroup(@RequestBody FileModel fileModel, @RequestParam(name = "groupName") String groupName, @RequestParam(name = "userName") String userName)
    {
        return fileService.deletfileFromGroup(fileModel, groupName, userName);
    }

    @GetMapping("/isFileBooked/{fileName}")
    public boolean isFileBooked(@PathVariable String fileName)
    {
        return fileService.isFileBooked(fileName);
    }

    @PostMapping("/FileBooked/{fileName}")
    public FileModel FileBooked(@PathVariable String fileName)
    {
        return fileService.FileBooked(fileName);
    }

    @PostMapping("/cancelResevedFile/{fileName}")
    public FileModel cancelResevedFile(@PathVariable String fileName)
    {
        return fileService.cancelResevedFile(fileName);
    }


    @GetMapping("/readFile/{fileName}")
    public FileModel readFile(@PathVariable String fileName)
    {
        return fileService.readFile(fileName);
    }

    @PostMapping("/FilesBooked")
    public String FilesBooked(@RequestBody List<FileModel> filesName)
    {
        return fileService.FilesBooked(   filesName);
    }

    @GetMapping("/showUserNameReserv/{fileName}")
    public String showUserNameReserv(@PathVariable String fileName)
    {
        return fileService.showUserNameReserv(fileName);
    }

    @GetMapping("/getAllFilesForGroup/{groupName}")
    public List<FileModel> getAllFilesForGroup(@PathVariable String groupName)
    {
        return fileService.getAllFilesForGroup(groupName);
    }
    @GetMapping("/getAllFilesForUsr/{ownerName}")
    public List<FileModel> getAllFilesForUsr(@PathVariable String ownerName)
    {
        return fileService.getAllFilesForUsr(ownerName);
    }

}
