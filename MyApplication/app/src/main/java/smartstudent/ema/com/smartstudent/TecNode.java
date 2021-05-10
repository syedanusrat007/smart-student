package smartstudent.ema.com.smartstudent;

/**
 * Created by mariumbinteibrahim on 11/30/17.
 */

public class TecNode {

    public
    String nm, dep, phn, pass, status,email;

    TecNode(){

    }
    TecNode(String nm, String dep, String phn, String pass,String status,String email) {
        this.nm=nm;
        this.dep=dep;
        this.phn=phn;
        this.pass=pass;
        this.status=status;
        this.email=email;

    }

    public String getNm() {
        return nm;
    }

    public String getDep() {
        return dep;
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
