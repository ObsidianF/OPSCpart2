package com.example.opscpart2;

import android.content.Context;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder>{

    private final List<Collections_Items> items;
    private final Context context;
    //private ItemClickListner mItemListener;

    public CollectionAdapter(List<Collections_Items> items, Context context) {
        this.items = items;
        this.context = context;
        //this.
    }





    @NonNull
    @Override
    public CollectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.MyViewHolder holder, int position) {

        Collections_Items collections_items = items.get(position);

        holder.name.setText(collections_items.getName());
        holder.goal.setText(collections_items.getGoal());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

   // public interface ItemClickListner{

   //     void onItemClick(Collections_Items details);

   // }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name,goal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.CollectionNameTV);
            goal =itemView.findViewById(R.id.GoalAmountTV);

        }
    }
}