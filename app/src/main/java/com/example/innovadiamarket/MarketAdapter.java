package com.example.innovadiamarket;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;


public class MarketAdapter extends RecyclerView.Adapter<MarketViewHolder> {

    private Context context;
    List<String> cost;
    List<String> phone;
    List<String> name;
    List<String> imageurl;




    public MarketAdapter(Context context, List<String> cost,List<String> name,List<String> phone,List<String> imageurl) {
        this.context = context;
        this.cost = cost;
        this.phone=phone;
        this.name=name;
        this.imageurl=imageurl;
    }

    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.market_item,parent,false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
       // Glide.with(context).load(marketinfo.get(position).getItemImage()).into(holder.imageView);
        Picasso.get().load(imageurl.get(position)).into(holder.imageView);
        holder.title.setText(name.get(position));
        holder.phone.setText(phone.get(position));
        holder.cost.setText(cost.get(position));
    }

    @Override
    public int getItemCount() {
        return cost.size();
    }
}
class MarketViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView title,phone,cost;


    public MarketViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageview);
        title=itemView.findViewById(R.id.textview2);
        phone=itemView.findViewById(R.id.textview4);
        cost=itemView.findViewById(R.id.textview6);
    }
}