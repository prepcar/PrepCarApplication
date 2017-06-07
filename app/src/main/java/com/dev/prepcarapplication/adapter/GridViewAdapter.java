package com.dev.prepcarapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev.prepcarapplication.R;
import com.dev.prepcarapplication.fragment.FragmentCurrentCar;
import com.dev.prepcarapplication.fragment.FragmentDealerNewCar;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>  {
    private Context mContext;
    private final ArrayList<String> arrayList;
    boolean flag;
String type;
    String taskid, temp;
    private LayoutInflater inflator;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private  OnItemClickListener listener=null;
    public GridViewAdapter(Context c, ArrayList<String> Imageid, boolean flag, ArrayList<Uri> sendImages, String taskid, String temp,OnItemClickListener listener) {
        this.taskid = taskid;
        this.temp = temp;
        mContext = c;
        this.flag = flag;
        inflator = LayoutInflater.from(c);
        arrayList = null;
        this.listener = listener;

    }

    public GridViewAdapter(Context context, ArrayList<String> arrayList,boolean flag,String type,OnItemClickListener listener) {
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  inflator = LayoutInflater.from(context);
        this.type=type;
        mContext = context;
        this.arrayList = arrayList;
        this.flag=flag;
        this.listener = listener;
    }
    public GridViewAdapter(Context context, ArrayList<String> arrayList,boolean flag,String type) {
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  inflator = LayoutInflater.from(context);
        this.type=type;
        mContext = context;
        this.arrayList = arrayList;
        this.flag=flag;
    }

    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.grid_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(!flag){
        holder.grid_delete.setVisibility(View.GONE);
    }
       if(arrayList.get(position).contains("http")){
            Picasso.with(mContext).load(arrayList.get(position))
                    .into(holder.imageView);
        }else{
           try {
               Bitmap myBitmap = BitmapFactory.decodeFile(arrayList.get(position));
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
               holder.imageView.setImageBitmap(myBitmap);
           }catch (Exception e){
               e.printStackTrace();
           }

        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                listener.onItemClick(position);
            }
        });
        holder.grid_delete.setTag(position);
        holder.grid_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=(Integer) v.getTag();
                arrayList.remove(pos);
                if(type.equals("newcar")){
                    FragmentDealerNewCar.buton_dealer_browse.setVisibility(View.VISIBLE);
                    FragmentDealerNewCar.imagesnumber--;
                }
                else{
                FragmentCurrentCar.buton_browse.setVisibility(View.VISIBLE);
                FragmentCurrentCar.imagesnumber--;}
                notifyDataSetChanged();
            }
        });

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder  {
        ImageView imageView;
        ImageView grid_delete;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imagegrid);
            grid_delete = (ImageView) view.findViewById(R.id.grid_delete);

        }
    }

}