package com.example.opscpart2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdpater extends RecyclerView.Adapter<MyAdpater.MyViewHolder> {
    private final List<ItemGetSet> items;
    private final Context context;


    public MyAdpater(List<ItemGetSet> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout_items, null));
        //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdpater.MyViewHolder holder, int position) {
        ItemGetSet itemGetSet = items.get(position);


        holder.Details.setText(itemGetSet.getDetails());
        holder.Date.setText(itemGetSet.getDate()); // sets the data in the views using the data
        //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].


        Picasso.get().load(itemGetSet.getImagename()).resize(100,100).centerCrop().into(holder.imageView); // sets the image to the views from firebase
        //Oliveira, A., 2022. how to save my image loaded from firebase storage through picasso in cache of my app. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/42227935/how-to-save-my-image-loaded-from-firebase-storage-through-picasso-in-cache-of-my> [Accessed 29 June 2022].


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView Details, Date;
        private final ImageView imageView; // makes variables to hold the views

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Details = itemView.findViewById(R.id.DetailsTV);
            Date = itemView.findViewById(R.id.DateTV); // sets the views to local variables
            imageView = itemView.findViewById(R.id.imageView2);
            //Youtube.com. 2021. Recyclerview Item Click Listener Android Example | OnItemClickListener on RecyclerView + CardView. [online] Available at: <https://www.youtube.com/watch?v=90khNCjWUEI> [Accessed 2 June 2022].
        }
    }
}
