package DayOff;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Activities.Payroll.PayrollCheck;
import Models.Payroll;
import Services.CreateConnection;
import Services.PlaceHolder;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendDayoff extends AppCompatActivity {
    EditText from, to, reason;
    TextView send;
    PlaceHolder placeHolder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_off_request);
        from = findViewById(R.id.etdayoff);
        to = findViewById(R.id.ettime);
        reason = findViewById(R.id.etreason);
        send = findViewById(R.id.tvSend);
        send.setClickable(true);
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = from.getText().toString();
                String b= to.getText().toString();
                String c = reason.getText().toString();
                try {
                    Dayoff dayoff = new Dayoff(c, new Dayoff.period(a, b));
                    Call<Void> call = placeHolder.sendDayoff(dayoff);
                    call.enqueue(new Callback<Void>(){
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(SendDayoff.this, "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                                Log.d("ngu", response.message());
                                return;
                            }
                            Toast.makeText(SendDayoff.this, "Success "+ response.code(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(@NonNull Call<Void> call, Throwable t) {
                            Toast.makeText(SendDayoff.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public static class Dayoff{
        String reason;
        static class period{
            Date from;
            Date to;

            public period(String from, String to) throws ParseException {
                this.from = new SimpleDateFormat("dd-MM-yyyy").parse(from);
                this.to = new SimpleDateFormat("dd-MM-yyyy").parse(to);
            }
        }
        period period;
        public Dayoff(String reason, period period) {
            this.reason = reason;
            this.period = period;
        }
    }
}
