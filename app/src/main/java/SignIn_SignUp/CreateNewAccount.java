package SignIn_SignUp;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.workmanager.MainActivity;
import com.example.workmanager.R;

import Models.User;
import Services.CreateConnection;
import Services.PlaceHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * DONE
 */
public class CreateNewAccount extends AppCompatActivity {

    EditText createAccountUsername;
    EditText createAccountEmail;
    EditText createAccountPassword;
    EditText createAccountAddress;
    EditText createAccountID;
    EditText createAccountFullName;
    EditText createAccountPhoneNumber;
    EditText createAccountRole;
    EditText createAccountDob;
    RadioGroup createAccountGender;

    Button createAccountSignUp;
    PlaceHolder placeHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        findViewById();
        CreateConnection conn = new CreateConnection(getString(R.string.token));
        placeHolder = conn.CreatePlaceHolder();

        createAccountSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = createAccountUsername.getText().toString();
                String emailAddress = createAccountEmail.getText().toString();
                String password = createAccountPassword.getText().toString();
                String address = createAccountAddress.getText().toString();
                String cccd = createAccountID.getText().toString();
                String fullName = createAccountFullName.getText().toString();
                String phoneNumber = createAccountPhoneNumber.getText().toString();
                String role = createAccountRole.getText().toString();
                String dob = createAccountDob.getText().toString();
                String gender = "male";
                switch (createAccountGender.getCheckedRadioButtonId()){
                    case R.id.radioF:
                        gender = "female";
                        break;
                    case R.id.radioM:
                    gender = "male";
                    break;

                    case R.id.radioO:
                    gender = "Other";
                    break;
                }
                UserInfo userInfo = new UserInfo(
                        userName,
                        password,
                        emailAddress,
                        address,
                        cccd,
                        fullName,
                        phoneNumber,
                        role,
                        dob,
                        gender
                );
                Log.d("username", String.valueOf(!userName.isEmpty()));
                Log.d("email", String.valueOf(!emailAddress.isEmpty()));
                Log.d("password", String.valueOf(!password.isEmpty()));
                Log.d("email_again", String.valueOf(Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()));

//                if(!userName.isEmpty() && !emailAddress.isEmpty() && !password.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                    Call<User> call = placeHolder.register(userInfo);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {

                            if(!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(getApplicationContext(), "Success!!!", Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "\nSomething went wrong! 😢 \n Please try again! \n", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            //}
        });
    }

    private void findViewById(){
        createAccountUsername = findViewById(R.id.activity_create_account_username);
        createAccountEmail = findViewById(R.id.activity_create_account_emailAddress);
        createAccountPassword = findViewById(R.id.activity_create_account_password);
        createAccountSignUp = findViewById(R.id.activity_create_account_signup);
        createAccountAddress = findViewById(R.id.activity_create_account_address);
        createAccountID = findViewById(R.id.activity_create_account_cccd);
         createAccountFullName = findViewById(R.id.activity_create_account_fullname);
         createAccountPhoneNumber = findViewById(R.id.activity_create_account_phoneNumber);
         createAccountRole = findViewById(R.id.activity_create_account_role);
         createAccountDob = findViewById(R.id.activity_create_account_dob);
         createAccountGender = findViewById(R.id.activity_create_account_gender);
    }

    public class UserInfo{
        String username;
        String password;
        String email;
        String address;
        String cccd;
        String fullname;
        String phoneNumber;
        String role;
        String dob;
        String gender;

        public UserInfo(String username, String password, String email, String address, String cccd, String fullname, String phoneNumber, String role, String dob, String gender) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.address = address;
            this.cccd = cccd;
            this.fullname = fullname;
            this.phoneNumber = phoneNumber;
            this.role = role;
            this.dob = dob;
            this.gender = gender;
        }
    }
}
