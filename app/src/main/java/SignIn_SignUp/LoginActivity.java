package SignIn_SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.workmanager.MainActivity;
import com.example.workmanager.R;

import java.util.Date;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * DONE
 */
public class LoginActivity extends AppCompatActivity {

    Button btn_createNewAccount;
    Button btn_logIn;
    EditText emailLogIn;
    EditText passwordLogIn;
    TextView forgottenPassword;
    PlaceHolder placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();
        findViewById();

        btn_createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateNewAccount.class);
                startActivity(intent);
            }
        });

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLogIn.getText().toString();
                String password = passwordLogIn.getText().toString();
                if((email.isEmpty() || password.isEmpty()) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call<UserInfo> call = placeHolder.login(email, password);
                call.enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this,response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_SHORT).show();
                        UserInfo user = (UserInfo) response.body();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        SaveSharedPreference.setUser(getApplicationContext(), user.getFullname(), user.getEmail(), user.getAvatar(), user.get_id(), user.getRole(), user.getToken());
                        ConstraintLayout loading = findViewById(R.id.loading);
                        loading.setVisibility(View.VISIBLE);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Something went wrong! 😢 \n Please try again! \n"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public class UserInfo extends User{
        String token;
        public UserInfo(String username, String password, String email, String address, String cccd, String fullname, long phoneNumber, String role, int maxDateOff, Date dob, String gender, boolean isBoss, String avatar, Date create_at, String token) {
            super(username, password, email, address, cccd, fullname, phoneNumber, role, maxDateOff, dob, gender, isBoss, avatar, create_at);
            this.token = token;
        }
        public String getToken() {
            return token;
        }
    }
    private void findViewById(){
        btn_createNewAccount = findViewById(R.id.activity_login_create_account);
        btn_logIn = findViewById(R.id.activity_login_log_in);
        emailLogIn = findViewById(R.id.activity_login_email);
        passwordLogIn = findViewById(R.id.activity_login_password);
        forgottenPassword = findViewById(R.id.activity_login_forgotten_password);
    }
}