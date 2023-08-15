package com.example.despensa.managers;

import com.example.despensa.objects.User;
import com.example.despensa.utils.Constants;
import com.example.despensa.utils.FileOperations;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private List<User> listUsers;
    private User logedUser;
    public UserManager() {
       listUsers = new ArrayList<User>();
       logedUser = null;
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }
    public void readUsers() {
        List<Object> readedUsers = FileOperations.readObjectsFromFile(Constants.c_usersFile);
        for(Object obj : readedUsers){
            if (obj instanceof  User){
                User readedUser = (User) obj;
                listUsers.add(readedUser);
            }
        }
    }

    public void mockUsers() {
        listUsers.add(new User("Luis", "luis@gmail.com", "senha123"));
    }
    public void saveUsers(){
        for (User user : listUsers) {
            FileOperations.saveObjectToFile(user, Constants.c_usersFile);
        }
    }
    public void addUser(User user) {
        listUsers.add(user);
    }

    public User searchUserByEmail(String email){
        for (User user : listUsers){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    public void removeUser(String email) {

    }

    public User getLogedUser() {
        return logedUser;
    }

    public void setLogedUser(User logedUser) {
        this.logedUser = logedUser;
    }
}
