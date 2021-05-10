package com.cse.mist.audio_upload_download;

/**
 * Created by Tonni on 17/12/2017.
 */

public class Downloads {
    String audiotitle,uid;

    public Downloads(){

    }

    public Downloads(String audiotitle) {
        this.audiotitle = audiotitle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAudiotitle() {
        return audiotitle;
    }

    public void setAudiotitle(String audiotitle) {
        this.audiotitle = audiotitle;
    }
}
