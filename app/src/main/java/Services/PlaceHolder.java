package Services;

import java.util.List;

import Activities.Payroll.PayrollCheck;
import Models.Payroll;
import Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PlaceHolder {

    @GET("api/users")
    Call<List<User>> getUsers();

    @POST("api/payrolls/create")
    Call<List<Payroll>> createPayrolls(@Body PayrollCheck.PayrollInfoNeeded data);

    @POST("api/notifications/firebase/notification")
    Call<Void> sendNotification(@Body PayrollCheck.FirebaseNotification notification);
}
