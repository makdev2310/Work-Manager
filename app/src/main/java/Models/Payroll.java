package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Payroll implements Parcelable {
    String _id;
    String staff_id;
    float wage;
    String role;
    int dayoff_count;
    String detail;
    Date create_at;

    protected Payroll(Parcel in) {
        _id = in.readString();
        staff_id = in.readString();
        wage = in.readFloat();
        role = in.readString();
        dayoff_count = in.readInt();
        detail = in.readString();
        create_at = (java.util.Date) in.readSerializable();
    }

    public static final Creator<Payroll> CREATOR = new Creator<Payroll>() {
        @Override
        public Payroll createFromParcel(Parcel in) {
            return new Payroll(in);
        }

        @Override
        public Payroll[] newArray(int size) {
            return new Payroll[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(staff_id);
        parcel.writeFloat(wage);
        parcel.writeString(role);
        parcel.writeInt(dayoff_count);
        parcel.writeString(detail);
        parcel.writeSerializable(create_at);
    }
}