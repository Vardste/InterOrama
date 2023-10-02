package com;
 
public class UserForm {
 
    private User user = new User();
    private String retypedPassword;
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public String getRetypedPassword() {
        return retypedPassword;
    }
 
    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }

}