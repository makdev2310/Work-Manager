package Activities.Payroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.Payroll;
import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayrollCheck extends AppCompatActivity {

    Button btnConfirm;
    RecyclerView recyclerView;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Payroll> payrolls = new ArrayList<>();
    PlaceHolder placeHolder;
    PayrollAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_check);

        try {
            init();
            setUpAdapter();
            getData();
            btnHandler();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void init(){
        findViewById();
        users = this.getIntent().getExtras().getParcelableArrayList("staff_list");
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();
    }

    void findViewById(){
        btnConfirm = findViewById(R.id.payrollcheck_btnConfirm);
        recyclerView = findViewById(R.id.payrollcheck_RecyclerView);
    }

    void btnHandler(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //confirm + send alarm
                sendNotification("Bang luong", payrolls.get(0));
                Intent intent = new Intent(PayrollCheck.this, PayrollConfirm.class);
                intent.putExtra("payrolls_count", payrolls.size());
                startActivity(intent);
            }
        });
    }

    void getData() throws ParseException {
        ArrayList<String> IDs = new ArrayList<>();
        for(User user : users){
            IDs.add(user.get_id());
        }
        //get payroll by userID
        PayrollInfoNeeded data = new PayrollInfoNeeded(IDs, new Time("2022-04-01", "2022-05-01"));
        Call<List<Payroll>> call = placeHolder.createPayrolls(data);
        call.enqueue(new Callback<List<Payroll>>(){
            @Override
            public void onResponse(@NonNull Call<List<Payroll>> call, @NonNull Response<List<Payroll>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(PayrollCheck.this, "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Payroll> temp = (ArrayList<Payroll>) response.body();
                payrolls.addAll(temp);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@NonNull Call<List<Payroll>> call, Throwable t) {
                Toast.makeText(PayrollCheck.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setUpAdapter(){
        adapter = new PayrollAdapter(this, users, payrolls);
        recyclerView.setLayoutManager(new LinearLayoutManager(PayrollCheck.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void sendNotification(String Title, Payroll payroll) {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            // Get new FCM registration token
            String token = task.getResult();
            CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
            PlaceHolder placeHolder = conn.CreatePlaceHolder();
            FirebaseNotification notification = new FirebaseNotification(new NotificationData(Title, payroll), token);
            Call<Void> call = placeHolder.sendNotification(notification);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Gửi thành công!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("response", t.getMessage());
                }
            });
        });
    }

    public static class FirebaseNotification {
        NotificationData notificationData;
        String tokenDevice;

        public FirebaseNotification(NotificationData notificationData, String tokenDevice) {
            this.notificationData = notificationData;
            this.tokenDevice = tokenDevice;
        }
    }

    static class NotificationData {
        String title;
        Payroll content;

        public NotificationData(String title, Payroll content) {
            this.title = title;
            this.content = content;
        }
    }

    public class PayrollInfoNeeded {
        ArrayList<String> staff_id;
        Time time;

        public PayrollInfoNeeded(ArrayList<String> staff_id, Time time) {
            this.staff_id = staff_id;
            this.time = time;
        }
    }
    class Time{
        Date from;
        Date to;
        public Time(String from, String to) throws ParseException {
            this.from = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            this.to = new SimpleDateFormat("yyyy-MM-dd").parse(to);
        }
    }

    public class PayrollAdapter extends RecyclerView.Adapter<PayrollAdapter.ViewHolder> {
        Context context;
        ArrayList<User> users;
        ArrayList<Payroll> payrolls;
        public PayrollAdapter(Context context, ArrayList<User> users, ArrayList<Payroll> payrolls) {
            this.context = context;
            this.users = users;
            this.payrolls = payrolls;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull PayrollAdapter.ViewHolder holder, int position) {
            Payroll payroll = payrolls.get(position);
            User user = users.get(position);
            if(payroll.getStaff_id().equals(user.get_id())){
                holder.tv_fullname.setText(user.getFullname());
                holder.tv_dayoff.setText(String.valueOf(payroll.getDayoff_count()));
                holder.tv_role.setText(user.getRole());
                //there's another efficient way.
                Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + user.getAvatar())
                        .error(R.drawable.ic_error)
                        .into(holder.iv_avatar);
                holder.tv_salary.setText("$" + payroll.getWage());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PayrollDetail.class);
                    intent.putExtra("payroll_detail_payroll", (Parcelable) payroll);
                    intent.putExtra("payroll_detail_user", (Parcelable) user);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return payrolls.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_avatar;
            TextView tv_fullname, tv_salary, tv_role, tv_dayoff;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_fullname = itemView.findViewById(R.id.itemuser_fullname);
                tv_salary = itemView.findViewById(R.id.itemuser_salary);
                tv_role = itemView.findViewById(R.id.itemuser_role);
                tv_dayoff = itemView.findViewById(R.id.itemuser_dayoff);
                iv_avatar = itemView.findViewById(R.id.itemuser_avatar);
            }
        }
    }
}

