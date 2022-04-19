package Models;

import java.util.Date;

public class Payroll {
    String _id;
    String staff_id;
    float wage;
    String role;
    int dayoff_count;
    String detail;
    Date create_at;

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setWage(long wage) {
        this.wage = wage;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDayoff_count(int dayoff_count) {
        this.dayoff_count = dayoff_count;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public double getWage() {
        return wage;
    }

    public String getRole() {
        return role;
    }

    public int getDayoff_count() {
        return dayoff_count;
    }

    public String getDetail() {
        return detail;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public Payroll(String staff_id, long wage, String role, int dayoff_count, String detail, Date create_at) {
        this.staff_id = staff_id;
        this.wage = wage;
        this.role = role;
        this.dayoff_count = dayoff_count;
        this.detail = detail;
        this.create_at = create_at;
    }
}