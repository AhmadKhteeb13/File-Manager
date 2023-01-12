package com.example.filesmanegar.controller;
import com.example.filesmanegar.model.GroupModel;
import com.example.filesmanegar.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class GroupController {
    @Autowired
    private  GroupService groupService;


    @PostMapping("/creatgroup")
    public String creatGroup(@RequestBody GroupModel request)
    {
        return groupService.createGroup(request);
    }

    @DeleteMapping ("/deletgroup")
    public String deletGroup(@RequestBody GroupModel groupModel)
    {
        return groupService.deleteGroup(groupModel);
    }

    @GetMapping("/showMyGroups")
    public String showUserGroups(@RequestBody String request)
    {
        return groupService.showUserGroups(request);
    }

//    @PostMapping("/addFileToGroup")
//    public String addFileToGroup(@RequestBody GroupModel groupModel)
//    {
//        return groupService.addFileToGroup(groupModel);
//    }

//    @PostMapping("/addUserToGroup")
//    public String addUserToGroup(@RequestBody GroupModel groupModel)
//    {
//        return groupService.addUserToGroup(groupModel);
//    }

//    @DeleteMapping("/deletFileFromGroup/")
//    public String deletFileFromGroup(@RequestBody GroupModel request)
//    {
//        return groupService.deletFileFromGroup(request);
//    }

}
