package Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Models.Dayoff;
import Services.CreateConnection;
import Services.PlaceHolder;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Callback;
import retrofit2.Response;

public class xacnhan_xinnghi extends AppCompatActivity {
    PlaceHolder placeHolder;
    ArrayList <Dayoff>dayoffs=new ArrayList<>();
    dayoffsadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhan_xinnghi);
        findViewById();
        onClickHandler();
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(this));
        placeHolder = conn.CreatePlaceHolder();
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        adapter = new dayoffsadapter(this, dayoffs);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        getData();

    }

    void getData(){
        retrofit2.Call<List<Dayoff>> call = placeHolder.getmydayoff();
        call.enqueue(new Callback<List<Dayoff>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Dayoff>> call, Response<List<Dayoff>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(xacnhan_xinnghi.this, "Something went wrong ", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<Dayoff> temp = (ArrayList<Dayoff>) response.body();
                dayoffs.addAll(temp);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(retrofit2.Call<List<Dayoff>> call, Throwable t) {
                Toast.makeText(xacnhan_xinnghi.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }

    void approveDayOff(Dayoff dayoff){
        retrofit2.Call<Void> call = placeHolder.approveDayOff(true, dayoff.get_id());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(xacnhan_xinnghi.this, "Something went wrong ", Toast.LENGTH_LONG).show();
                    return;
                }
                dayoffs.remove(dayoff);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Toast.makeText(xacnhan_xinnghi.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void findViewById(){


    }
    private void onClickHandler() {


    }
    public class dayoffsadapter extends RecyclerView.Adapter<dayoffsadapter.ViewHolder> {
        Context context;

        ArrayList<Dayoff> dayoffs;
        public dayoffsadapter(Context context,  ArrayList<Dayoff> dayoffs) {
            this.context = context;

            this.dayoffs = dayoffs;
        }

        @NonNull

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dayoff_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Dayoff dayoff  = dayoffs.get(position);
            holder.id.setText(dayoff.get_id());
            holder.reason.setText(dayoff.getReason());
            holder.from.setText(new SimpleDateFormat("dd-MM-yyyy").format(dayoff.getDateFrom()));
            holder.to.setText(new SimpleDateFormat("dd-MM-yyyy").format(dayoff.getDateTo()));
            holder.btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    approveDayOff(dayoff);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dayoffs.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView from;
            TextView to;
            TextView reason;
            TextView id;
            ImageButton btnApprove;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.tvsourceAnswer1);
                from = itemView.findViewById(R.id.tvabsencedateAnswer1);
                to = itemView.findViewById(R.id.datetimeAnswer);
                reason = itemView.findViewById(R.id.reason);
                btnApprove = itemView.findViewById(R.id.dayoff_item_Approve);
            }
        }
    }

}
