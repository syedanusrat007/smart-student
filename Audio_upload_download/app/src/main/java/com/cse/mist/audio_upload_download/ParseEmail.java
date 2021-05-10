package com.cse.mist.audio_upload_download;

/**
 * Created by Tonni on 17/12/2017.
 */

class ParseEmail {

    String s;

    public String Parse(String email) {
        s = "";
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '.') {
                s = s + ":";
            } else s = s + email.charAt(i);
        }
        return s;
    }
}
