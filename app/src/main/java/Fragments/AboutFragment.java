package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.workmanager.R;
import com.squareup.picasso.Picasso;

import Activities.Payroll.MyPayroll;
import Activities.User.UserInfo;
import SignIn_SignUp.LoginActivity;
import SignIn_SignUp.SaveSharedPreference;
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutFragment extends Fragment {
    ConstraintLayout constraintLUserInfo, constraintMyPayrolls, constraintDayOffs;
    Button logOut;
    TextView name, role, email;
    CircleImageView avatar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewById(view);
        btnHandler();
        email.setSelected(true);
        name.setText(SaveSharedPreference.getUserName(getContext()));
        role.setText(SaveSharedPreference.getRole(getContext()));
        email.setText(SaveSharedPreference.getEmailAddress(getContext()));
        Picasso.get().load("https://dkhoa-work-lovers-2.herokuapp.com/" + SaveSharedPreference.getAvatar(getContext()))
                .error(R.drawable.ic_error)
                .into(avatar);
    }

    void findViewById(View view){
        constraintLUserInfo = view.findViewById(R.id.about_UserInfo);
        constraintMyPayrolls = view.findViewById(R.id.about_Payrolls);
        constraintDayOffs = view.findViewById(R.id.about_DayOffs);
        logOut = view.findViewById(R.id.fragmentabout_logout);
        name = view.findViewById(R.id.about_name);
        role = view.findViewById(R.id.about_role);
        email = view.findViewById(R.id.about_email);
        avatar = view.findViewById(R.id.about_avatar);
    }

    void btnHandler(){
        constraintLUserInfo.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), UserInfo.class);
            startActivity(intent);
        });

        constraintMyPayrolls.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MyPayroll.class);
            startActivity(intent);
        });

        logOut.setOnClickListener(view -> {
            SaveSharedPreference.clearUser(getContext());
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}
