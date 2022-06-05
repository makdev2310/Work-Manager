package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Models.Notification;
import Services.CreateConnection;
import Services.PlaceHolder;
import SignIn_SignUp.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {
    PlaceHolder placeHolder;
    ArrayList <Models.Notification>noti=new ArrayList<>();
    notificationsadapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        CreateConnection conn = new CreateConnection(SaveSharedPreference.getPrefToken(getContext()));
        placeHolder = conn.CreatePlaceHolder();
        RecyclerView recyclerView=v.findViewById(R.id.notification_RecyclerView);
        adapter = new notificationsadapter(getContext(), noti);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        getData();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    void getData(){
        Call<List<Notification>> call = placeHolder.getmynotifications();
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Models.Notification>> call, Response<List<Notification>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Something went wrong ", Toast.LENGTH_LONG).show();
                    return;
                }
                ArrayList<Models.Notification> temp = (ArrayList<Models.Notification>) response.body();
                noti.addAll(temp);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(retrofit2.Call<List<Models.Notification>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public class notificationsadapter extends RecyclerView.Adapter<notificationsadapter.ViewHolder> {
        Context context;

        ArrayList<Notification> notifications;
        public notificationsadapter(Context context,  ArrayList<Models.Notification> notifications) {
            this.context = context;

            this.notifications = notifications;
        }

        @android.support.annotation.NonNull

        @Override
        public ViewHolder onCreateViewHolder(@android.support.annotation.NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notications_item, parent, false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(@android.support.annotation.NonNull ViewHolder holder, int position) {
            Models.Notification notification = notifications.get(position);
            holder.datetime.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(notification.create_at));
            /*holder.tieude.setText(new SimpleDateFormat("dd-MM-yyyy").format(notification.create_at));*/
            holder.noidung.setText(notification.content);
            holder.tieude.setText(notification.title);

        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tieude;
            TextView noidung;
            TextView datetime;
            public ViewHolder(@android.support.annotation.NonNull View itemView) {
                super(itemView);
                tieude = itemView.findViewById(R.id.tieude);
                noidung = itemView.findViewById(R.id.noidung);
                datetime = itemView.findViewById(R.id.datetime);
            }
        }
    }

}

