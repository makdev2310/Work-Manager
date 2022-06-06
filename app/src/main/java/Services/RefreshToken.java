package Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import SignIn_SignUp.LoginActivity;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefreshToken {
    String refreshToken;
    ResponseToken token;
    PlaceHolder placeHolder;
    Context ctx;
    public RefreshToken(Context ctx) {
        this.ctx = ctx;
        this.refreshToken = SaveSharedPreference.getPrefRefreshToken(ctx);
        CreateConnection createConnection = new CreateConnection();
        placeHolder = createConnection.CreatePlaceHolder();
    }
    public void RefreshingToken(){
        Call<ResponseToken> call = placeHolder.refreshToken(refreshToken);
        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ctx, "loi1", Toast.LENGTH_LONG).show();
                    if(response.code() == 403){
                        SaveSharedPreference.clearUser(ctx);
                        Intent intent = new Intent(ctx, LoginActivity.class);
                        ctx.startActivity(intent);
                    }
                }else{
                    token = (ResponseToken) response.body();
                    Log.d("token", token.getToken());
                    Log.d("before token", SaveSharedPreference.getPrefToken(ctx));
                    SaveSharedPreference.setPrefToken(ctx, token.getToken());
                    Log.d("after token", SaveSharedPreference.getPrefToken(ctx));

                }
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                Toast.makeText(ctx,t.getMessage()+": loi2", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static class ResponseToken{
        String token;

        public String getToken() {
            return token;
        }
    }
}
