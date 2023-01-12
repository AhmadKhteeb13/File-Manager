package com.example.filesmanegar.controller;
import com.example.filesmanegar.model.FileModel;
import com.example.filesmanegar.model.UserModel;
import com.example.filesmanegar.services.FileService;
import com.example.filesmanegar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.filesmanegar.model.GroupModel;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService  fileService;


    @PostMapping(path = "/register",consumes = "application/json",produces ="application/json")
    UserModel register(@RequestBody UserModel account){

        return userService.register(account);


    }

    @PostMapping(path = "login",consumes = "application/json",produces ="application/json")
    UserModel login(@RequestBody UserModel account){

        return userService.login(account);
    }

    @PostMapping(path = "/addUserToGroup")
    GroupModel addUserToGroup(@RequestBody UserModel userModel, @RequestParam(name = "groupName") String groupName){

        return userService.addUserToGroup(userModel, groupName);
    }
    @DeleteMapping(path = "/deleteUserFromGroup")
    GroupModel deleteUserFromGroup(@RequestBody GroupModel groupModel, @RequestParam(name = "groupName") String userName)
    {
        return userService.deleteUserFromGroup(groupModel,userName);
    }

    @GetMapping(path = "/groupFilesIsnotBooked")
    List<FileModel> groupFilesIsnotBooked(@RequestBody GroupModel groupmodel)
    {
        return fileService.groupFilesIsnotBooked(groupmodel);
    }

//    @GetMapping(path = "logout")
//    void logout(@RequestParam(name = "email") String email){
//
//        new Thread(){
//        };
//        userService.logout(email);
//    }
//
//    @GetMapping(path ="all")
//    List<UserModel> getAllAccounts(){
//
//        return userService.getAllAccounts();
//
//    }
//
//
//    @GetMapping(path = "getOne")
//    UserModel getOneAccount(@RequestParam(name = "email") String email){
//
//        return userService.getOneAccount(email);
//
//    }

//    @GetMapping(path = "userGroups")
//    Set<GroupModel> userGroups(@RequestBody UserModel userModel)
//    {
//        return userService.userGroups(userModel);
//    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/addUserToGroup")
//    GroupModel addUserToGroup(@RequestParam UserModel userModel,
//                              @RequestParam String groupName)
//    {
//        return userService.addUserToGroup(userModel,groupName);
//    }
//    private final UserService userService;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping("/index")
//    public String home(){
//        return "index";
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestBody User user){
//        return userService.register(user);
//    }
//
////    @GetMapping("/forgot-password")
////    public String forgotPassword(){
////        return "forgot-password";
////    }
//
//    @DeleteMapping("/deleteUser/{userName}")
//    public String deletUser(@PathVariable String userName)
//    {
//        return userService.deleteUser(userName);
//    }
//
//    @DeleteMapping("deleteUserFromGroup")
//    public String deleteUserFromGroup(@RequestBody Group request)
//    {
//        return userService.deletUserFromGroup(request);
//    }
//
//    @GetMapping("/login")
//    public String loginForm(@RequestBody User user){
//        return userService.logIn(user);
//    }
}
