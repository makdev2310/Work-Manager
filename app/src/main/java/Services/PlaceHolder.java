package Services;

import java.util.List;

import Models.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolder {

    @GET("api/users")
    Call<List<User>> getUsers();
}
