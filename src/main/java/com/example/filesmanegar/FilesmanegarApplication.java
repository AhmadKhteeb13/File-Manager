package com.example.filesmanegar;

import com.example.filesmanegar.model.GroupModel;
import com.example.filesmanegar.model.UserModel;
import com.example.filesmanegar.services.GroupService;
import com.example.filesmanegar.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@CrossOrigin
//@EnableConfigurationProperties
public class FilesmanegarApplication implements CommandLineRunner{
    @Resource
    UserService userService = new UserService();
    @Resource
    GroupService groupService = new GroupService();
	public static void main(String[] args)  {SpringApplication.run(FilesmanegarApplication.class, args);}

    @Override
    public void run(String... args) throws Exception {
        UserModel userModel = new UserModel("Admin@gmial.com","admin", "Admin");
        //groupService.init();
        GroupModel groupModel = new GroupModel("public", "everyOne", "C:\\Users\\ahmad\\Desktop\\IA\\filesmanegar\\groups\\public");
        groupService.createGroup(groupModel);
        userService.register(userModel);
//        System.out.println("*/**/*/*/*/*/*//"+userService.register(userModel));
    }
}
