package Activities.Payroll;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Models.Payroll;
import Services.CreateConnection;
import Services.PlaceHolder;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPayroll extends AppCompatActivity {
    TextView title;
    RecyclerView recyclerView;
    Button btn;
    PlaceHolder placeHolder;
    ArrayList<Payroll> payrolls = new ArrayList<>();
    PayrollAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_check);
        init();
        getData();
    }

    void init(){
        findViewById();
        setUpAdapter();
        title.setText("Bảng lương của tôi");
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btn.setVisibility(View.GONE);
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();
    }

    void getData(){
        Call<List<Payroll>> call = placeHolder.getMyPayroll();
        call.enqueue(new Callback<List<Payroll>>() {
            @Override
            public void onResponse(Call<List<Payroll>> call, Response<List<Payroll>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MyPayroll.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<Payroll> temp = (ArrayList<Payroll>) response.body();
                payrolls.addAll(temp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Payroll>> call, Throwable t) {
                Toast.makeText(MyPayroll.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    void findViewById(){
        title = findViewById(R.id.payrollcheck_Title);
        recyclerView = findViewById(R.id.payrollcheck_RecyclerView);
        btn = findViewById(R.id.payrollcheck_btnConfirm);
    }

    void setUpAdapter(){
        adapter = new PayrollAdapter(MyPayroll.this, payrolls);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyPayroll.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public class PayrollAdapter extends RecyclerView.Adapter<PayrollAdapter.ViewHolder>{
        Context context;
        ArrayList<Payroll> payrolls;
        public PayrollAdapter(Context context, ArrayList<Payroll> payrolls) {
            this.context = context;
            this.payrolls = payrolls;
        }

        @NonNull
        @Override
        public PayrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            PayrollAdapter.ViewHolder holder = new PayrollAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull PayrollAdapter.ViewHolder holder, int position) {
            Payroll payroll = payrolls.get(position);
            holder.iv_avatar.setVisibility(View.GONE);
            holder.tv_time.setText(new SimpleDateFormat("dd/MM/yyyy").format(payroll.getCreate_at()));
            holder.tv_salary.setText(String.valueOf(payroll.getWage()));
            holder.tv_role.setText(payroll.getRole());
            holder.tv_dayoff.setText(String.valueOf(payroll.getDayoff_count()));
        }

        @Override
        public int getItemCount() {
            return payrolls.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_avatar;
            TextView tv_time, tv_salary, tv_role, tv_dayoff;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_time = itemView.findViewById(R.id.itemuser_fullname);
                tv_salary = itemView.findViewById(R.id.itemuser_salary);
                tv_role = itemView.findViewById(R.id.itemuser_role);
                tv_dayoff = itemView.findViewById(R.id.itemuser_dayoff);
                iv_avatar = itemView.findViewById(R.id.itemuser_avatar);
            }
        }
    }
}
