package Models;

import java.util.Date;

public class Dayoff {

    Boolean isApproved ;
    String reason;
    Date create_at;



    public Dayoff(String reason, Boolean isApproved, Date  create_at){
        this.isApproved=isApproved;
        this.create_at=create_at;
        this.reason=reason;

    }
    Boolean getIsApproved(){
        return this.isApproved;
    }
    String getReason(){
        return this.reason;
    }
    Date getcreate_at(){
        return this.create_at;
    }

}
