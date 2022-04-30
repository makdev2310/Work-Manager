package Activities.Payroll;

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

import java.util.ArrayList;
import java.util.List;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollCal extends AppCompatActivity {

    private Button btnUnselect, btnPayrollCal, btnSelectALl;
    private RecyclerView recyclerView;
    private StaffListAdapter adapter;
    public PlaceHolder placeHolder;
    private TextView tvAlertNoStaff;
    ArrayList<Staff> staffs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_cal);

        init();
        btnClickHandler();
        getData();
    }

    void getData() {
        //Loading Staffs
        Call<List<User>> call = placeHolder.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(PayrollCal.this, "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<User> users = (ArrayList<User>) response.body();
                assert users != null;
                for (User user: users) {
                    staffs.add(new Staff(user));
                }
                if(staffs.isEmpty()){
                    tvAlertNoStaff.setVisibility(View.VISIBLE);
                }else{
                    tvAlertNoStaff.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<User>> call, Throwable t) {
                Toast.makeText(PayrollCal.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                tvAlertNoStaff.setVisibility(View.VISIBLE);
            }
        });
    }

    void init(){
        findViewById();
        setUpAdapter();
        CreateConnection conn = new CreateConnection("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyNTJmNmQyZTdiODM0NDMyZjBiZDkxNSIsImlhdCI6MTY0OTYwNDMwNiwiZXhwIjoxNjUyMTk2MzA2fQ.LNp_gNF4rn4N5qvX_MQVYWhHhSISCHhNRInSqLx0r3s");
        placeHolder = conn.CreatePlaceHolder();
    }

    void findViewById(){
        btnPayrollCal = findViewById(R.id.payrollcal_btnPayrollCal);
        btnUnselect = findViewById(R.id.payrollcal_btnUnselect);
        btnSelectALl = findViewById(R.id.payrollcal_btnSelectAll);
        recyclerView = findViewById(R.id.payrollcal_RecyclerView);
        tvAlertNoStaff = findViewById(R.id.payroll_tvAlertNoStaff);
    }

    void setUpAdapter(){
        adapter = new StaffListAdapter(this, staffs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
    }

    void btnClickHandler(){
        btnPayrollCal();
        btnUnselect();
        btnSelectAll();
    }

    void btnUnselect(){
        btnUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Staff staff: staffs) {
                    staff.setCheck(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    void btnPayrollCal(){
        btnPayrollCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> users = new ArrayList<>();
                for (Staff staff: staffs) {
                    if(staff.isCheck){
                        users.add(staff);
                    }
                }
                if(users.isEmpty()){
                    Toast.makeText(PayrollCal.this, "Chưa chọn nhân viên nào!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(PayrollCal.this, PayrollCheck.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("staff_list", users);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    void btnSelectAll(){
        btnSelectALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Staff staff: staffs) {
                    staff.setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.ViewHolder>{
        Context context;
        ArrayList<Staff> staffs;
        public StaffListAdapter(Context context, ArrayList<Staff> staffs) {
            this.context = context;
            this.staffs = staffs;
        }

        @NonNull
        @Override
        public StaffListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.round_item_staff, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull StaffListAdapter.ViewHolder holder, int position) {
            Staff staff = staffs.get(position);
            holder.tv_fullName.setText(staff.getFullname());
            Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + staff.getAvatar())
                    .error(R.drawable.ic_error)
                    .into(holder.iv_avatar);
            if(staff.isCheck){
                holder.iv_iconCheck.setVisibility(View.VISIBLE);
            }else{
                holder.iv_iconCheck.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(view -> {
                staff.setCheck(!staff.isCheck);
                notifyItemChanged(position);
            });
        }

        @Override
        public int getItemCount() {
            return staffs.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_avatar, iv_iconCheck;
            TextView tv_fullName;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_avatar = itemView.findViewById(R.id.round_item_staff_avatar);
                tv_fullName = itemView.findViewById(R.id.round_item_staff_fullname);
                iv_iconCheck = itemView.findViewById(R.id.user_info_checkIcon);
            }
        }
    }

    public class Staff extends User{
        boolean isCheck;
        public Staff(User user) {
            super(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getAddress(),
                    user.getFullname(),
                    user.getPhoneNumber(),
                    user.getRole(),
                    user.getMaxDateOff(),
                    user.getDob(),
                    user.getGender(),
                    user.isBoss(),
                    user.getAvatar(),
                    user.getCreate_at()
            );
            this.set_id(user.get_id());
            this.isCheck = false;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
