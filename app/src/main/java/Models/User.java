package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User implements Parcelable {
    String _id;
    String username;
    String password;
    String email;
    String address;
    String cccd;
    String fullname;
    long phoneNumber;
    String role;
    int maxDateOff;
    Date dob;
    String gender;
    boolean isBoss;
    String avatar;
    Date create_at;

    public User(String username, String password, String email, String address, String cccd, String fullname, long phoneNumber, String role, int maxDateOff, Date dob, String gender, boolean isBoss, String avatar, Date create_at) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.cccd = cccd;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.maxDateOff = maxDateOff;
        this.dob = dob;
        this.gender = gender;
        this.isBoss = isBoss;
        this.avatar = avatar;
        this.create_at = create_at;
    }

    protected User(Parcel in) {
        _id = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
        address = in.readString();
        cccd = in.readString();
        fullname = in.readString();
        phoneNumber = in.readLong();
        role = in.readString();
        maxDateOff = in.readInt();
        gender = in.readString();
        isBoss = in.readByte() != 0;
        avatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMaxDateOff(int maxDateOff) {
        this.maxDateOff = maxDateOff;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCccd() {
        return cccd;
    }

    public String getFullname() {
        return fullname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public int getMaxDateOff() {
        return maxDateOff;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getCreate_at() {
        return create_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(cccd);
        parcel.writeString(fullname);
        parcel.writeLong(phoneNumber);
        parcel.writeString(role);
        parcel.writeInt(maxDateOff);
        parcel.writeString(gender);
        parcel.writeByte((byte) (isBoss ? 1 : 0));
        parcel.writeString(avatar);
    }
}

