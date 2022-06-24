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

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

    private final List<Collections_Items> items; // creates a list of Collections_Items to store everything from the database in
    private final Context context;
    private ItemClickListner mItemListener;

    public CollectionAdapter(List<Collections_Items> items, Context context, ItemClickListner itemClickListner) {
        this.items = items;
        this.context = context;
        this.mItemListener = itemClickListner;
    }

    @NonNull
    @Override
    public CollectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout, null));
        //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.MyViewHolder holder, int position) {

        Collections_Items collections_items = items.get(position);

        String name = "Collection: " + collections_items.getName();
        String goal = "Goal: " + collections_items.getGoal();
        String total =  collections_items.getNumberOfItems()+"/"+collections_items.getGoal();

        holder.name.setText(name);
        holder.goal.setText(goal);
        holder.total.setText(total);

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(items.get(position));
            //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemClickListner {

        void onItemClick(Collections_Items details);

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, goal, total;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.CollectionNameTV);
            goal = itemView.findViewById(R.id.GoalAmountTV);
            total = itemView.findViewById(R.id.GoalTV);

            //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].

        }
    }
}