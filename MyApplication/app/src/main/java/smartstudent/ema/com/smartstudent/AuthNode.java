package smartstudent.ema.com.smartstudent;

/**
 * Created by mariumbinteibrahim on 11/29/17.
 */

public class AuthNode {

    public
    String nm, eid, phn, pass, status,email;

    AuthNode(){

    }
    AuthNode(String nm, String eid, String phn, String pass,String status,String email) {
        this.nm=nm;
        this.eid=eid;
        this.phn=phn;
        this.pass=pass;
        this.status=status;
        this.email=email;

    }

    public String getNm() {
        return nm;
    }

    public String getEid() {
        return eid;
    }

    public String getPhn() {
        return phn;
    }

    public String getPass() {
        return pass;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
}
