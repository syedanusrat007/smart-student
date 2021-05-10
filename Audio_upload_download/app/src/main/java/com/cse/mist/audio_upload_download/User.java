package com.cse.mist.audio_upload_download;

/**
 * Created by Tonni on 17/12/2017.
 */

class User {


    public String email;
    public String password;


    public User() {

    }

    public User( String email, String password) {

        this.email = email;
        this.password = password;


    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
