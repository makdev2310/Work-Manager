package Activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.R;

import Models.Notification;

public class xinnghi extends AppCompatActivity {

    Button btn_lammoi;
    Button btn_xemxinnghi;

    EditText edit_text1;
    EditText edit_text2;
    EditText edit_text3;
    EditText edit_text4;
    EditText edit_text5;
    EditText edit_text6;
    EditText edit_text7;
    EditText edit_text8;
    EditText edit_text9;
    EditText edit_text10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xinnghi);
        findViewById();
        onClickHandler();
        findViewById(R.id.xinnghi_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit();
            }
        });
        findViewById(R.id.xinnghi_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit1();
            }
        });

    }
    private void findViewById(){

        btn_lammoi = findViewById(R.id.xinnghi_reload);
        btn_xemxinnghi = findViewById(R.id.xinnghi_xemxinnghi);

        edit_text1 = (EditText)findViewById(R.id.etid );
        edit_text2 = (EditText)findViewById(R.id.etname);
        edit_text3 = (EditText)findViewById(R.id.etdepartment );
        edit_text4 = (EditText)findViewById(R.id.etposition);
        edit_text5 = (EditText)findViewById(R.id.etabsencetype );
        edit_text6 = (EditText)findViewById(R.id.etdayoff);
        edit_text7 = (EditText)findViewById(R.id.ettime );
        edit_text8 = (EditText)findViewById(R.id.etnumberofdayoff);
        edit_text9 = (EditText)findViewById(R.id.etshift );
        edit_text10 = (EditText)findViewById(R.id.etreason);
    }

    private void onClickHandler() {

        btn_lammoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                edit_text1.setText("");
                edit_text2.setText("");
                edit_text3.setText("");
                edit_text4.setText("");
                edit_text5.setText("");
                edit_text6.setText("");
                edit_text7.setText("");
                edit_text8.setText("");
                edit_text9.setText("");
                edit_text10.setText("");


            }
        });

        btn_xemxinnghi.setOnClickListener(view -> {
            Intent intent = new Intent(xinnghi.this, Notification.class);
            startActivity(intent);
        });
    }
    private void showDialogExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(xinnghi.this, R.style.AlerDialog);

        View view = LayoutInflater.from(xinnghi.this).inflate(
                R.layout.activity_hoilaiyeucau,findViewById(R.id.layoutDialog)

        );
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView texViewMessage = view.findViewById(R.id.textMessage);
        Button buttonYes =view.findViewById(R.id.buttonYES);
        Button buttonNo =view.findViewById(R.id.buttonNO);
        ImageView imageviewIcon =view.findViewById(R.id.imageViewIcon);

        textViewTitle.setText("Exit");
        texViewMessage.setText("Làm mới ngay bây giờ ?");
        buttonNo.setText("NO");
        buttonYes.setText("YES");
        imageviewIcon.setImageResource(R.drawable.ic_baseline_exit_to_app_24);

        AlertDialog alertDialog = builder.create();

        buttonNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(xinnghi.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(xinnghi.this, "Làm mới thành công !",Toast.LENGTH_SHORT).show();
                edit_text1.setText("");
                edit_text2.setText("");
                edit_text3.setText("");
                edit_text4.setText("");
                edit_text5.setText("");
                edit_text6.setText("");
                edit_text7.setText("");
                edit_text8.setText("");
                edit_text9.setText("");
                edit_text10.setText("");
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }
    private void showDialogExit1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(xinnghi.this, R.style.AlerDialog);

        View view = LayoutInflater.from(xinnghi.this).inflate(
                R.layout.activity_hoilaiyeucau,findViewById(R.id.layoutDialog)

        );
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView texViewMessage = view.findViewById(R.id.textMessage);
        Button buttonYes =view.findViewById(R.id.buttonYES);
        Button buttonNo =view.findViewById(R.id.buttonNO);
        ImageView imageviewIcon =view.findViewById(R.id.imageViewIcon);

        textViewTitle.setText("Exit");
        texViewMessage.setText("Gửi ngay bây giờ ?");
        buttonNo.setText("NO");
        buttonYes.setText("YES");
        imageviewIcon.setImageResource(R.drawable.ic_baseline_exit_to_app_24);

        AlertDialog alertDialog = builder.create();

        buttonNo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(xinnghi.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(xinnghi.this, "Gửi thành công !",Toast.LENGTH_SHORT).show();

            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }
}