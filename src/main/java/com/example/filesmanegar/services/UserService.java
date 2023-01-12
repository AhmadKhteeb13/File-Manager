package com.example.filesmanegar.services;
import com.example.filesmanegar.model.*;
import com.example.filesmanegar.repository.GroupRepository;
import com.example.filesmanegar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {
    private UserRepository userRepository;
    private  GroupRepository groupRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public UserService() {
    }

    public UserModel register(UserModel account) {
        boolean isExists = userRepository.findUserByEmail(account.getEmail()).isPresent();
        if(isExists){
            throw new IllegalStateException("account exist");
        }
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        boolean isPublicExiste = groupRepository.findGroupByName("public").isPresent();
        if(isPublicExiste)
        {
            account.getUserGrops().add(groupRepository.findGroupByName("public").get().getId());
        }
        return userRepository.save(account);
    }

    @Transactional
    public UserModel login(UserModel account){
        boolean isExists = userRepository.findUserByEmail(account.getEmail()).isPresent();
        boolean isExists2 = userRepository.findUserByName(account.getUserName()).isPresent();
        if(isExists) {
            UserModel storedAccount = userRepository.findUserByEmail(account.getEmail()).get();
            PasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
            if(storedAccount.getEmail().equals(account.getEmail())&&bCryptPasswordEncoder.matches(account.getPassword(),storedAccount.getPassword())){
                storedAccount.setIs_login(true);
                return storedAccount;
            }else{
                throw new IllegalStateException("not match email");
            }
        }
        else if(isExists2)
        {
            UserModel storedAccount = userRepository.findUserByName(account.getUserName()).get();
            PasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
            if(storedAccount.getUserName().equals(account.getUserName())&&bCryptPasswordEncoder.matches(account.getPassword(),storedAccount.getPassword())){
                storedAccount.setIs_login(true);
                return storedAccount;
            }else{
                throw new IllegalStateException("not match name");
            }
        }
        throw new IllegalStateException("account is not exist");
    }


    public GroupModel addUserToGroup(UserModel userModel, String groupName)
    {
        boolean isUserExist = userRepository.findUserByName(userModel.getUserName()).isPresent();
        if(isUserExist)
        {

            groupRepository.findGroupByName(groupName).get().getGroupUsers().add(userRepository.findUserByName(userModel.getUserName()).get().getId());
            return groupRepository.findGroupByName(groupName).get();
        }
        else
        {
            throw new IllegalStateException("User Not Exists !");
        }
    }
    public GroupModel deleteUserFromGroup(GroupModel groupModel, String userName){
        boolean isUserExist = userRepository.findUserByName(userName).isPresent();
        boolean isGroupExist = groupRepository.findGroupByName(groupModel.getGroupName()).isPresent();
        List<Long> Temp = groupRepository.findGroupByName(groupModel.getGroupName()).get().getGroupUsers();
        List<Long> Temp2 = new ArrayList<>();
        if(isUserExist)
        {
            if(isGroupExist)
            {
                for (Long userid:Temp
                     ) {
                    if(userid==userRepository.findUserByName(userName).get().getId())
                    {
                        if(userRepository.findUserByName(userName).get().getUserFiles().isEmpty())
                        {

                        }
                        else {
                            Temp2.add(userid);
                            throw new IllegalStateException("Sorry User Have Files !");
                        }
                    }
                    else {
                        Temp2.add(userid);
                    }
                }
            }
            else {
                throw new IllegalStateException("Group Not Exists !");
            }
        }
        else {
            throw new IllegalStateException("User Not Exists !");
        }
        return groupRepository.findGroupByName(groupModel.getGroupName()).get();
    }
//    @Transactional
//    public void logout(String email){
//        UserModel storedAccount = userRepository.findUserByEmail(email).get();
//        storedAccount.setIs_login(false);
//    }
//
//    public List<UserModel> getAllAccounts(){
//        return  userRepository.findAll();
//    }
//
//    public UserModel getOneAccount(String email){
//        boolean isExists = userRepository.findUserByEmail(email).isPresent();
//        if(!isExists){
//
//            throw new IllegalStateException("user not exits");
//
//        }
//        return userRepository.findUserByEmail(email).get();
//    }
//
//    public GroupModel addUserToGroup(UserModel userModel, String groupName){
//        boolean isUserExists = userRepository.findUserByEmail(userModel.getEmail()).isPresent();
//        boolean isGroupExists = groupRepository.findGroupByName(groupName).isPresent();
//        if(!isUserExists)
//        {
//            throw new IllegalStateException("user not exits");
//        }
//        if(!isGroupExists)
//        {
//            throw new IllegalStateException("group not exits");
//        }
//        //groupRepository.findGroupByName(groupName).get().getGroupUserModels().add(userModel);
//        return groupRepository.findGroupByName(groupName).get();
//    }
//
//    public String deletUserFromGroup(UserModel userModel, String groupName)
//    {
//        boolean isUserExists = userRepository.findUserByEmail(userModel.getEmail()).isPresent();
//        boolean isGroupExists = groupRepository.findGroupByName(groupName).isPresent();
//        if(!isUserExists)
//        {
//            throw new IllegalStateException("user not exits");
//        }
//        if(!isGroupExists)
//        {
//            throw new IllegalStateException("group not exits");
//        }
//        //Set<UserModel> Temp1 = groupRepository.findGroupByName(groupName).get().getGroupUserModels();
////        Set<UserModel> Temp2 = null;
////        for (UserModel usery:Temp1
////             ) {
////            if (usery.getUserName().equals(userModel.getUserName()))
////            {
////                continue;
////            }
////            else {
////                Temp2.add(usery);
////            }
////        }
////        groupRepository.findGroupByName(groupName).get().setGroupUserModels(Temp2);
//        return "Deleted";
//    }

//    public Set<GroupModel> userGroups(UserModel userModel)
//    {
//        boolean isUserExists = userRepository.findUserByEmail(userModel.getEmail()).isPresent();
//        if(!isUserExists)
//        {
//            throw new IllegalStateException("user not exits");
//        }
//        //Set<GroupModel> Temp = userRepository.findUserByEmail(userModel.getEmail()).get().getUserGrops();
//        return "Temp";
//    }
//    private UserRepository userRepository;
//
//    private final static String USER_NOT_FOUND_MSG =
//            "user with email %s not found";
//
//    private final UserRepository appUserRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final GroupRepository groupRepository;
//
//    public String register(User user) {
//        List<File> userFiles ;
//        List<Group> userGrops;
//        boolean userExists = appUserRepository
//                .findUserByUserEmail(user.getEmail())
//                .isPresent();
//        boolean userNameExists = appUserRepository
//                .findUserByUserName(user.getUserName()).isPresent();
//        if (userExists) {
//            throw new IllegalStateException("Email Already Exists");
//        }
//        if (userNameExists) {
//            throw new IllegalStateException("Name Already Exists");
//        }
//
//        String token = this.signUpUser(
//                new User(
//                        user.getUserName(),
//                        user.getEmail(),
//                        user.getPassword(),
//                        user.getUserFiles(),
//                        user.getUserGrops()
//                        //UserRole.USER
//                )
//        );
//        return token;
//    }
////    @Override
////    public UserDetails loadUserByUsername(String email)
////            throws UsernameNotFoundException {
////        return appUserRepository.findUserByUserEmail(email)
////                .orElseThrow(() ->
////                        new UsernameNotFoundException(
////                                String.format(USER_NOT_FOUND_MSG, email)));
////    }
//
//    public String signUpUser(User appUser) {
////        boolean userExists = appUserRepository
////                .findUserByUserEmail(appUser.getEmail())
////                .isPresent();
////        boolean userNameExists = appUserRepository
////                .findUserByUserName(appUser.getUserName()).isPresent();
////        if (userExists) {
////            throw new IllegalStateException("Email Already Exists");
////        }
////        if (userNameExists) {
////            throw new IllegalStateException("Name Already Exists");
////        }
//
//        String encodedPassword = bCryptPasswordEncoder
//                .encode(appUser.getPassword());
//
//        appUser.setPassword(encodedPassword);
//
//        appUserRepository.save(appUser);
//
//        return "token";
//    }
//    public String deleteUser(String username)
//    {
//        boolean isuserExistes = userRepository
//                .findUserByUserName(username).isPresent();
//        if (!isuserExistes) {
//            throw new IllegalStateException(" User Dose Not Exists ! ");
//        }
//        else {
//
//            User usery = userRepository.findUserByUserName(username).get();
//            if(usery.getUserFiles().isEmpty())
//            {
//                userRepository.delete(usery);
//                return "true";
//            }
//            else
//            {
//                throw new IllegalStateException(" Please Make Check-out ! ");
//            }
//        }
//    }
//    public String deletUserFromGroup(Group group)
//    {
//        boolean isuserExistes = userRepository
//                .findUserByUserName(group.getGroupName()).isPresent();
//        if (!isuserExistes) {
//            throw new IllegalStateException(" User Dose Not Exists ! ");
//        }
//        else
//        {
//            List<User> groupUsers = groupRepository.findGroupByName(group.getGroupName()).get().getGroupUsers();
//            for (User user:groupUsers
//                 ) {
//                if(user.getUserName().equals(group.getGroupOwnerName()))
//                {
//                    List<File> userFiles = user.getUserFiles();
//                    List<File> groupFile = groupRepository.findGroupByName(group.getGroupName()).get().getGroupFiles();
//                    for (File file: userFiles
//                         ) {
//                        for (File file2: groupFile
//                             ) {
//                            if(file.getFileName().equals(file2.getFileName()))
//                            {
//                                userRepository.delete(user);
//                                return "Deleted";
//                            }
//                        }
//
//                    }
//                }
//            }
//            return "This User Booked File in This Group";
//        }
//    }
////    public String addFiilesToUser(File file)
////    {
////
////    }
//public String logIn(User user)
//{
//    boolean isExistes = userRepository.findUserByUserEmail(user.getUserName()).isPresent();
//    boolean isExistes2 = userRepository.findUserByUserName(user.getUserName()).isPresent();
//    if (isExistes || isExistes2)
//    {
//        return "true";
//    }
//    else
//    {
//        throw new IllegalStateException("error");
//    }
//}
}
