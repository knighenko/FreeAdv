package com.knighenko.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knighenko.myapplication.R;
import com.knighenko.myapplication.entity.Advertisement;
import com.squareup.picasso.Picasso;

import java.security.KeyManagementException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.SSLContext;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;


public class AdvAdapter extends RecyclerView.Adapter<AdvAdapter.AdvListHolder> {
    private ArrayList<Advertisement> advertisements = new ArrayList<>();


    @NonNull
    @Override
    public AdvListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertisement, parent, false);
        return new AdvListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvListHolder holder, int position) {
        System.err.println("Advertisement on position " + position + " " + advertisements.get(position).getTitle());
        holder.bind(advertisements.get(position));

    }

    @Override
    public int getItemCount() {
        return advertisements.size();
    }


    public void clearListAdv() {
        advertisements.clear();
        notifyDataSetChanged();
    }

    public void setListAdv(Collection<Advertisement> advertisements) {
        System.err.println("Arrays are " + advertisements.size());
        this.advertisements.addAll(advertisements);
        notifyDataSetChanged();

    }

    class AdvListHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView url;
        private TextView imageSrc;
        private ImageView imageView;

        public AdvListHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleAdv);
            url = itemView.findViewById(R.id.urlAdv);
            imageSrc = itemView.findViewById(R.id.imageSrcTextView);
            imageView = itemView.findViewById(R.id.imageViewAdv);


        }

        public void bind(Advertisement adv) {
            title.setText(adv.getTitle());
            url.setText(adv.getUrl());
            imageSrc.setText(adv.getImageSrc());

            String urlImg = adv.getImageSrc();

            imageSrc.setVisibility(!urlImg.equals("No Img") ? View.VISIBLE : View.GONE);
         paintImg(urlImg);
        }
        private void paintImg(String urlImg)  {
            if (urlImg.equals("No Img")){
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                Picasso.get().load(urlImg).resize(200,200).centerCrop().into(imageView);
            }


        }
    }
}
