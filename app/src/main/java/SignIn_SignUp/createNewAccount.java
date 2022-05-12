package SignIn_SignUp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
public class createNewAccount extends AppCompatActivity {

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
        CreateConnection conn = new CreateConnection("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyNTJmNmQyZTdiODM0NDMyZjBiZDkxNSIsImlhdCI6MTY0OTYwNDMwNiwiZXhwIjoxNjUyMTk2MzA2fQ.LNp_gNF4rn4N5qvX_MQVYWhHhSISCHhNRInSqLx0r3s");
        placeHolder = conn.CreatePlaceHolder();

        createAccountSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = createAccountUsername.getText().toString();
                String emailAddress = createAccountEmail.getText().toString();
                String password = createAccountPassword.getText().toString();
                String address = createAccountAddress.getText().toString();
                String id = createAccountID.getText().toString();
                String fullName = createAccountFullName.getText().toString();
                String phoneNumber = createAccountPhoneNumber.getText().toString();
                String role = createAccountRole.getText().toString();
                String dob = createAccountDob.getText().toString();
                String gender = "";
                switch (createAccountGender.getCheckedRadioButtonId()){
                    case R.id.radioF:
                        gender = "Female";
                        break;


                    case R.id.radioM:
                    gender = "Male";
                    break;

                    case R.id.radioO:
                    gender = "Other";
                    break;
                }

                if(!userName.isEmpty() && !emailAddress.isEmpty() && !password.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                    Call<User> call = placeHolder.register(userName, emailAddress, password, address, id, fullName, phoneNumber, role, dob, gender);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(!response.isSuccessful()) {
                                Toast.makeText(createNewAccount.this, response.code() + ": " + response.message(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(createNewAccount.this, "Success!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(createNewAccount.this, "\nSomething went wrong! 😢 \n Please try again! \n", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void findViewById(){
        createAccountUsername = findViewById(R.id.activity_create_account_username);
        createAccountEmail = findViewById(R.id.activity_create_account_emailAddress);
        createAccountPassword = findViewById(R.id.activity_create_account_password);
        createAccountSignUp = findViewById(R.id.activity_create_account_signup);
    }
}
