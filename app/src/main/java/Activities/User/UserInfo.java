package Activities.User;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.R;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfo extends AppCompatActivity {
    CircleImageView user_info_Avatar, checkIcon;
    TextView MaNV, Ten, GioiTinh, NgaySinh, CCCD, SDT, DiaChi, PhongBan, ChucVu;
    Button Luu;
    PlaceHolder placeHolder;
    User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        init();
        getData();
    }

    void findViewById() {
        user_info_Avatar = findViewById(R.id.user_info_ivAvatar);
        checkIcon = findViewById(R.id.user_info_checkIcon);
        MaNV = findViewById(R.id.user_info_manv);
        Ten = findViewById(R.id.user_info_ten);
        GioiTinh = findViewById(R.id.user_info_gioitinh);
        NgaySinh = findViewById(R.id.user_info_ngaysinh);
        CCCD = findViewById(R.id.user_info_cccd);
        SDT = findViewById(R.id.user_info_sdt);
        DiaChi = findViewById(R.id.user_info_diachi);
        PhongBan = findViewById(R.id.user_info_phongban);
        ChucVu = findViewById(R.id.user_info_chucvu);
        Luu = findViewById(R.id.user_info_Luu);
    }

    void buttonHandler(){
        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        checkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void init(){
        findViewById();
        buttonHandler();
        CreateConnection conn = new CreateConnection(getString(R.string.token));
        placeHolder = conn.CreatePlaceHolder();
    }

    void getData(){
        Call<User> call = placeHolder.getProfile();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UserInfo.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    return;
                }
                user = response.body();
                Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + user.getAvatar())
                        .error(R.drawable.ic_error)
                        .into(user_info_Avatar);
                MaNV.setText(user.get_id());
                Ten.setText(user.getFullname());
                GioiTinh.setText(user.getGender());
                NgaySinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(user.getDob()));
                CCCD.setText(user.getCccd());
                SDT.setText(String.valueOf(user.getPhoneNumber()));
                DiaChi.setText(user.getAddress());
                ChucVu.setText(user.getRole());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserInfo.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}
