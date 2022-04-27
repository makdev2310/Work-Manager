package Activities.Payroll;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Models.Payroll;
import Models.User;

public class PayrollDetail extends AppCompatActivity {

    TextView tvStaffName, tvWage, tvRole, tvCoefficient ,tvDayoffs, tvCreateAt, tvDetail;
    Button btnEdit;
    ImageView ivAvatar;
    Payroll payroll;
    User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payroll_detail);
        init();
    }

    void init(){
        findViewById();
        getDataThenSetView();
    }

    void findViewById(){
        tvStaffName = findViewById(R.id.payrolldetail_tvStaffName);
        tvWage = findViewById(R.id.payrolldetail_tvWage);
        tvRole = findViewById(R.id.payrolldetail_tvRole);
        tvCoefficient = findViewById(R.id.payrolldetail_tvCoefficient);
        tvDayoffs = findViewById(R.id.payrolldetail_tvDayoffs);
        tvCreateAt = findViewById(R.id.payrolldetail_tvCreateAt);
        tvDetail = findViewById(R.id.payrolldetail_tvDetail);
        btnEdit = findViewById(R.id.payrolldetail_btnEdit);
        ivAvatar = findViewById(R.id.payrolldetail_ivAvatar);
    }

    void getDataThenSetView(){
        payroll = (Payroll) this.getIntent().getParcelableExtra("payroll_detail_payroll");
        user = (User) this.getIntent().getParcelableExtra("payroll_detail_user");

        if(user == null || payroll == null){
            payroll = this.getIntent().getParcelableExtra("payroll_notification");
            tvStaffName.setVisibility(View.GONE);
            TextView role = findViewById(R.id.payrolldetail_tv2);
            role.setVisibility(View.GONE);
            tvRole.setVisibility(View.GONE);
            ivAvatar.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.GONE);
        }else{
            assert user != null;
            tvStaffName.setText(user.getFullname());
            tvRole.setText(user.getRole());
            //he so luong
            //there's another efficient way.
            Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + user.getAvatar())
                    .error(R.drawable.ic_error)
                    .into(ivAvatar);
        }
        tvWage.setText("$" + payroll.getWage());
        tvDayoffs.setText(String.valueOf(payroll.getDayoff_count()));
        tvCreateAt.setText(new SimpleDateFormat("dd/MM/yyyy").format(payroll.getCreate_at()));
        tvDetail.setText(payroll.getDetail());
    }

    void btnHandler(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
