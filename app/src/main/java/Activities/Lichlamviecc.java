package Activities;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.R;

public class Lichlamviecc extends AppCompatActivity {
    android.widget.CalendarView CalendarView;
    android.widget.TextView TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        CalendarView=findViewById(R.id.CalendarView);
        TextView=findViewById(R.id.TextView);


        CalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year , int month, int dayOfmonth) {
                String date=dayOfmonth+"/"+month+"/"+year;
                TextView.setText(date);
            }
        });
    }
}