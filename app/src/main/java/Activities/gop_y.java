package Activities;

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

import Services.CreateConnection;
import Services.PlaceHolder;

public class gop_y extends AppCompatActivity {
    Button btn_lammoi;

    EditText edit_text;
    EditText edit_text2;


    PlaceHolder placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gop_y);
        findViewById();
        onClickHandler();
        findViewById(R.id.gopy_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit();
            }
        });
        findViewById(R.id.gopy_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit1();
            }
        });
        CreateConnection conn = new CreateConnection("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyODYwNzZhZGQ1NzlmODRlZDY5NWNhMyIsImlhdCI6MTY1NDI3MTM3MiwiZXhwIjoxNjU2ODYzMzcyfQ.Lrw5Kcf5-74HJ8p7HH36y08cKmkFH7WxoeyzH9YKQsU");
        placeHolder = conn.CreatePlaceHolder();

    }
   /* void postData(){
        *//*        gop_y.notifications notifications =new notifications(edit_text2.getText().toString(),"high");*//*
        gop_y.feedbacks feedbacks =new gop_y.feedbacks(edit_text2.getText().toString(),"high",edit_text.getText().toString());
        retrofit2.Call<Void> call = placeHolder.sendFeedback(feedbacks);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(gop_y.this, "Something went wrong ", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(gop_y.this, "Gửi thành công!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Toast.makeText(gop_y.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }*/
    private void findViewById(){

        btn_lammoi = findViewById(R.id.gopy_reload);


        edit_text = (EditText)findViewById(R.id.edit_text);
        edit_text2 = (EditText)findViewById(R.id.edit_text2);
    }

    private void onClickHandler() {    //ham nay khong thuc hien duoc vi da co ham showDialogExit

        btn_lammoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                edit_text.setText("");
                edit_text2.setText("");
            }
        });


    }
    private void showDialogExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(gop_y.this, R.style.AlerDialog);

        View view = LayoutInflater.from(gop_y.this).inflate(
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
                Toast.makeText(gop_y.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(gop_y.this, "Làm mới thành công !",Toast.LENGTH_SHORT).show();
                edit_text.setText("");
                edit_text2.setText("");
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }
    private void showDialogExit1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(gop_y.this, R.style.AlerDialog);

        View view = LayoutInflater.from(gop_y.this).inflate(
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
                Toast.makeText(gop_y.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(gop_y.this, "Gửi thành công !",Toast.LENGTH_SHORT).show();

            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }
 /*   public class feedbacks {

        String priority;
        String content ;
        String title ;
        public  feedbacks(String content,String priority ,String title){
            this.content=content;
            this.priority=priority;
            this.title=title;
        }
        String getContent(){
            return this.content;
        }
        String getpriority(){
            return this.priority;
        }
        String getTitle(){
            return this.title;
        }
    }*/
}