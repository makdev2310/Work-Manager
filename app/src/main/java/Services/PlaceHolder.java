package Services;

import java.util.List;

import Activities.Payroll.PayrollCheck;
import Models.Payroll;
import Models.User;
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
    @POST("users/")
    Call<User> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("address") String address,
            @Field("cccd") String cccd,
            @Field("fullname") String fullname,
            @Field("phoneNumber") String phoneNumber,
            @Field("role") String role,
            @Field("dob") String dob,
            @Field("gender") String gender
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
