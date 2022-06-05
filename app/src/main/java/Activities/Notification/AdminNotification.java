package Activities.Notification;

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
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminNotification extends AppCompatActivity {
    Button btn_lammoi;
    Button btn_xacnhan;  // btn_xac nhan bi showDialogExit() tai buton yes block
    Button btn_lichsu;
    EditText edit_text;
    EditText edit_text2;


    PlaceHolder placeHolder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbaoadmin);
        findViewById();
        onClickHandler();
        findViewById(R.id.thongbao_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit();
            }
        });

        findViewById(R.id.thongbao_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExit1();

            }

        });
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();

    }
    void postData(){
/*        Thongbaoadmin.notifications notifications =new notifications(edit_text2.getText().toString(),"high");*/
        AdminNotification.notifications noti =new notifications(edit_text2.getText().toString(),"high",edit_text.getText().toString());
        retrofit2.Call<Void> call = placeHolder.sendNotification(noti);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AdminNotification.this, "Something went wrong ", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(AdminNotification.this, "Gửi thành công!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Toast.makeText(AdminNotification.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void findViewById(){

        btn_lammoi = findViewById(R.id.thongbao_reload);
        btn_xacnhan = findViewById(R.id.thongbao_send);
        btn_lichsu = findViewById(R.id.xinnghi_xemxinnghi);

        edit_text = (EditText)findViewById(R.id.edit_text);
        edit_text2 = (EditText)findViewById(R.id.edit_text2);
    }

    private void onClickHandler() {
      /*  btn_lammoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                edit_text.setText("Chào bạn:"+edit_text2.getText().toString());
            }
        });*/
        btn_lammoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                edit_text.setText("");
                edit_text2.setText("");
            }
        });
     /*   btn_xacnhan.setOnClickListener(view -> {
           postData();
            edit_text.setText("");
            edit_text2.setText("");
        });*/
//        btn_lichsu.setOnClickListener(view -> {
//            Intent intent = new Intent(AdminNotification.this, Notification.class);
//            startActivity(intent);
//        });

    }
    private void showDialogExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminNotification.this, R.style.AlerDialog);

        View view = LayoutInflater.from(AdminNotification.this).inflate(
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
                Toast.makeText(AdminNotification.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(AdminNotification.this, "Làm mới thành công !",Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminNotification.this, R.style.AlerDialog);

        View view = LayoutInflater.from(AdminNotification.this).inflate(
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
                Toast.makeText(AdminNotification.this, "...",Toast.LENGTH_SHORT).show();

            }

        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
               /* Toast.makeText(Thongbaoadmin.this, "Gửi thành công !",Toast.LENGTH_SHORT).show();*/
                postData();
                edit_text.setText("");
                edit_text2.setText("");
            }
        });
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }



    public class notifications {

        String priority;
        String content ;
        String title ;
        public  notifications(String content,String priority ,String title){
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
    }



}