package Services;

import java.util.List;

import Activities.Payroll.PayrollCheck;
import Models.Payroll;
import Models.User;
import SignIn_SignUp.createNewAccount;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceHolder {

    @GET("api/users")
    Call<List<User>> getUsers();

    @POST("api/payrolls/create")
    Call<List<Payroll>> createPayrolls(@Body PayrollCheck.PayrollInfoNeeded data);

    @POST("api/notifications/firebase/notification")
    Call<Void> sendNotification(@Body PayrollCheck.FirebaseNotification notification);

    @GET("api/users/profile")
    Call<User> getProfile();

    @FormUrlEncoded
    @POST("api/users/")
    Call<User> register(
            @Body createNewAccount.UserInfo data
            );

    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
