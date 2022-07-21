package com.example.ebite;

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

public class Adapter extends RecyclerView.Adapter <Adapter.ImageViewHolder> {

   private Context context;
   private List<Upload> list;

    public Adapter(Context context, List<Upload> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_uploads,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
      Upload upload = list.get(position);
      holder.Description.setText(upload.getDescription());
        Picasso.with(context)
                .load(upload.getImageUri())
                .fit()
                .centerInside()
                .into(holder.ShowImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends  RecyclerView.ViewHolder{
          TextView Description;
          ImageView ShowImage;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Description = itemView.findViewById(R.id.description_view);
            ShowImage = itemView.findViewById(R.id.show_uploads);
        }
    }
}
