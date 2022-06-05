package Models;

import java.util.Date;

public class Dayoff {
    String _id;

    public String get_id() {
        return _id;
    }

    Boolean isApproved ;
    String reason;
    Date create_at;



    public Dayoff(String reason, Boolean isApproved, Date  create_at){
        this.isApproved=isApproved;
        this.create_at=create_at;
        this.reason=reason;

    }

    public Boolean getApproved() {
        return isApproved;
    }

    public String getReason() {
        return reason;
    }

    public Date getCreate_at() {
        return create_at;
    }
}
