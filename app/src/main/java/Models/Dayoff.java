package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Dayoff {
    String _id;
    Boolean isApproved ;
    String reason;
    static class Period{
        Date from;
        Date to;

        public Period(String from, String to) throws ParseException {
            this.from = new SimpleDateFormat("dd-MM-yyyy").parse(from);
            this.to = new SimpleDateFormat("dd-MM-yyyy").parse(to);
        }
    }
    Period period;



    public Dayoff(String reason, Boolean isApproved, Period period){
        this.isApproved=isApproved;
        this.period=period;
        this.reason=reason;

    }

    public String get_id() {
        return _id;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public String getReason() {
        return reason;
    }

    public Date getDateFrom() {
        return period.from;
    }

    public Date getDateTo() { return period.to;}
}
