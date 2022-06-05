package Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.workmanager.R;

import Activities.Lichlamviecc;
import Activities.Payroll.PayrollCal;
import Activities.gop_y;
import DayOff.SendDayoff;
import SignIn_SignUp.SaveSharedPreference;

public class HomeFragment extends Fragment {
    ConstraintLayout constraintPayroll, constraintFeedback, constraintDayOff, constraintCalendar;
    Button btnTimeSet;
    TextView username;
    Chronometer chronometerCustomFormat;
    boolean isSetTime = false;
    long time = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewById(view);
        btnHandler();
        init();
    }

    void init(){
        username.setText(SaveSharedPreference.getUserName(getContext()));
        chronometerCustomFormat.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                onChronometerTickHandler();
            }
        });
    }
    void findViewById(View view){
        constraintPayroll = view.findViewById(R.id.home_Payroll);
        constraintCalendar = view.findViewById(R.id.home_Calendar);
        constraintDayOff = view.findViewById(R.id.home_DayOff);
        constraintFeedback = view.findViewById(R.id.home_Feedback);
        btnTimeSet = view.findViewById(R.id.home_TimeSet);
        username = view.findViewById(R.id.home_username);
        chronometerCustomFormat = (Chronometer) view.findViewById(R.id.Chronometer);
    }

    void btnHandler(){
        constraintPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PayrollCal.class);
                startActivity(intent);
            }
        });

        constraintFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), gop_y.class);
                startActivity(intent);
            }
        });

        constraintDayOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SendDayoff.class);
                startActivity(intent);
            }
        });

        constraintCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Lichlamviecc.class);
                startActivity(intent);
            }
        });

        btnTimeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSetTime){
                    btnTimeSet.setText("RA CA");
                    btnTimeSet.setBackgroundColor(Color.RED);
                    doStart();
                    isSetTime = true;
                }else {
                    showDialogExit(view);
                }
            }
        });
    }

    private void showDialogExit(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlerDialog);

        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.activity_hoilaiyeucau,v.findViewById(R.id.layoutDialog)

        );
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView texViewMessage = view.findViewById(R.id.textMessage);
        Button buttonYes =view.findViewById(R.id.buttonYES);
        Button buttonNo =view.findViewById(R.id.buttonNO);
        ImageView imageviewIcon =view.findViewById(R.id.imageViewIcon);

        textViewTitle.setText("Exit");
        texViewMessage.setText("Ra ca ngay bây giờ ?");
        buttonNo.setText("NO");
        buttonYes.setText("YES");
        imageviewIcon.setImageResource(R.drawable.ic_baseline_exit_to_app_24);

        AlertDialog alertDialog = builder.create();

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(getContext(), "...",Toast.LENGTH_SHORT).show();
            }
        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
                Toast.makeText(getContext(), "Ra ca thành công !",Toast.LENGTH_SHORT).show();
                btnTimeSet.setText("START");
                btnTimeSet.setBackgroundColor(Color.GREEN);
                chronometerCustomFormat.stop();

                if(time/(60*60*1000) < 8){
                    Toast.makeText(getContext(), "Ra ca som: " + (8 - (double)time/(60*60*1000)) + " tieng", Toast.LENGTH_LONG).show();
                }
                chronometerCustomFormat.setText("00:00:00");
                isSetTime = false;
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }
    public void doStart()  {
        long base = SystemClock.elapsedRealtime();
        chronometerCustomFormat.setBase(base);
        chronometerCustomFormat.start();
    }
    public void onChronometerTickHandler()  {
        time = SystemClock.elapsedRealtime() - chronometerCustomFormat.getBase();

        int h = (int) ((time / 1000) / 3600);
        int m = (int) (((time / 1000) / 60) % 60);
        int s = (int) ((time / 1000) % 60);

        if(s<10&&m<10&&h<10){
            String customText ="0"+h +":" + "0"+m +":" + "0"+s  ;
            this.chronometerCustomFormat.setText(customText);
        }
        else if(s<10&&m>10&&h<10)
        {
            String customText ="0"+h +":"  +m +":" + "0"+s  ;
            this.chronometerCustomFormat.setText(customText);
        }
        else if(s>10&&m<10&&h<10)
        {
            String customText ="0"+h +":" +"0" +m +":" +s  ;
            this.chronometerCustomFormat.setText(customText);
        }
        else if(s>10&&m>10&&h<10)
        {
            String customText ="0"+h +":"   +m +":" +s  ;
            this.chronometerCustomFormat.setText(customText);
        }
    }
}
