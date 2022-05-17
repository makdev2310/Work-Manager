package Activities.User;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.workmanager.R;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import de.hdodenhof.circleimageview.CircleImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfo extends AppCompatActivity {
    CircleImageView user_info_Avatar;
    TextView MaNV, Ten, GioiTinh, NgaySinh, CCCD, SDT, DiaChi, PhongBan, ChucVu;
    Button Edit;
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
        MaNV = findViewById(R.id.user_info_manv);
        Ten = findViewById(R.id.user_info_ten);
        GioiTinh = findViewById(R.id.user_info_gioitinh);
        NgaySinh = findViewById(R.id.user_info_ngaysinh);
        CCCD = findViewById(R.id.user_info_cccd);
        SDT = findViewById(R.id.user_info_sdt);
        DiaChi = findViewById(R.id.user_info_diachi);
        PhongBan = findViewById(R.id.user_info_phongban);
        ChucVu = findViewById(R.id.user_info_chucvu);
        Edit = findViewById(R.id.user_info_Edit);
    }

    void buttonHandler(){
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(UserInfo.this);
                dialog.setContentView(R.layout.edit_info);
                TextView id = dialog.findViewById(R.id.user_info_manv);
                EditText editTen = dialog.findViewById(R.id.edit_user_info_ten);
                EditText editNgaysinh = dialog.findViewById(R.id.edit_user_info_ngaysinh);
                EditText editCccd = dialog.findViewById(R.id.edit_user_info_cccd);
                EditText editSdt = dialog.findViewById(R.id.edit_user_info_sdt);
                EditText editDiaChi = dialog.findViewById(R.id.user_info_diachi);
                Button btnLuu = dialog.findViewById(R.id.edit_user_info_Luu);
                Button btnCancel = dialog.findViewById(R.id.edit_user_info_Cancel);
                btnCancel.setOnClickListener(view1 -> dialog.dismiss());
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UserInfo.this, R.array.gender_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner Gender = dialog.findViewById(R.id.edit_user_info_gioitinh);
                CircleImageView avatar = dialog.findViewById(R.id.edit_user_info_ivAvatar);
                Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + user.getAvatar())
                        .error(R.drawable.ic_error)
                        .into(avatar);
                id.setText(user.get_id());
                editTen.setText(user.getFullname());
                editNgaysinh.setText(new SimpleDateFormat("dd/MM/yyyy").format(user.getDob()));
                editCccd.setText(user.getCccd());
                editSdt.setText(String.valueOf(user.getPhoneNumber()));
                editDiaChi.setText(user.getAddress());
                Gender.setAdapter(adapter);
                btnLuu.setOnClickListener(view2 -> {

                    String fullname = editTen.getText().toString();
                    if(fullname.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_LONG).show();
                        return;
                    }

                    String phoneNumber = editSdt.getText().toString();
                    if(phoneNumber.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_LONG).show();
                        return;
                    }

                    String dob = editNgaysinh.getText().toString();
                    if(dob.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_LONG).show();
                        return;
                    }

                    final String[] gender_selected = new String[1];
                    Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Adapter adapter1 = adapterView.getAdapter();
                            gender_selected[0] = (String) adapter1.getItem(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });

                    Call<Void> call = placeHolder.updateProfile(fullname, phoneNumber, dob);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful())
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.dismiss();
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
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
                MaNV.setSelected(true);
                SDT.setSelected(true);
                DiaChi.setSelected(true);
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
