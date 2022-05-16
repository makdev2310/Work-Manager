package Services;

import java.util.List;

import Activities.Payroll.PayrollCheck;
import Models.Payroll;
import Models.User;
import SignIn_SignUp.CreateNewAccount;
import SignIn_SignUp.LoginActivity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlaceHolder {

    @GET("api/users")
    Call<List<User>> getUsers();

    @POST("api/payrolls/create")
    Call<List<Payroll>> createPayrolls(@Body PayrollCheck.PayrollInfoNeeded data);

    @POST("api/notifications/firebase/notification")
    Call<Void> sendNotification(@Body PayrollCheck.FirebaseNotification notification);

    @GET("api/users/profile")
    Call<User> getProfile();

    @POST("api/users")
    Call<User> register(@Body CreateNewAccount.UserInfo data);

    @FormUrlEncoded
    @POST("api/users/login")
    Call<LoginActivity.UserInfo> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("api/dayoffs/{id}/approve")
    Call<Void> approveDayOff(
      @Field("isApproved") Boolean isApproved, //true = approve, false = disapprove
      @Path("id") String id // id of the day off boss want to approve.
    );

    @FormUrlEncoded
    @PUT("api/users/profile")
    Call<Void> updateProfile(
            @Field("fullname") String fullname,
            @Field("phoneNumber") String phoneNumber,
            @Field("dob") String dob
    );
}
