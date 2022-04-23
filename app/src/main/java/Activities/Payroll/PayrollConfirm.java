package Activities.Payroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.MainActivity;
import com.example.workmanager.R;

public class PayrollConfirm extends AppCompatActivity {

    private Button btnDetail, btnBackHome;
    private TextView tvMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payroll_confirm);

        init();
        btnHandler();
    }

    void findViewById(){
        tvMessage = findViewById(R.id.payrollconfirm_tvMessage);
        btnBackHome = findViewById(R.id.payrollconfirm_btnBackHome);
        btnDetail = findViewById(R.id.payrollconfirm_btnDetail);
    }

    void init(){
        findViewById();
        setData();
    }

    void setData(){
        int payrollsCount = (int) this.getIntent().getIntExtra("payrolls_count", 0);
        tvMessage.setText("Đã duyệt " + payrollsCount + " bảng lương.");
    }

    void btnHandler(){
        btnBackHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayrollConfirm.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDetail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }
}
