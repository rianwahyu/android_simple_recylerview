package com.bibit.project.simplerecylerview;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public List<ModelItem> mItemList;
    private Context context;

    private ItemClickListener clickListener;

    public AdapterItem(Context context, List<ModelItem> itemList) {
        this.context=context;
        this.mItemList = itemList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img_android ;
        TextView imgname;
        TextView description ;
        TextView hargacoret;
        TextView lastprice ;
        TextView hargacoret2;
        TextView lastprice2;
        TextView discount;
        TextView itemno;
        CardView cv;
        LinearLayout llcv;
        ImageView imgEven;
        TextView size;
        TextView endPeriode;
        ImageView imgBrand;
        ImageView imgnolPersen;

        public ItemViewHolder(@NonNull View convertView) {
            super(convertView);

            cv = (CardView) convertView.findViewById(R.id.cv);
            img_android = (ImageView) convertView.findViewById(R.id.img);
            imgname = (TextView) convertView.findViewById(R.id.imgname) ;
            description = (TextView) convertView.findViewById(R.id.deskription) ;
            hargacoret = (TextView) convertView.findViewById(R.id.hargacoret) ;
            lastprice = (TextView) convertView.findViewById(R.id.lastprice) ;
            hargacoret2 = (TextView) convertView.findViewById(R.id.hargacoret2) ;
            lastprice2 = (TextView) convertView.findViewById(R.id.lastprice2) ;
            itemno = (TextView) convertView.findViewById(R.id.itemno) ;
            llcv = (LinearLayout) convertView.findViewById(R.id.llcv) ;
            size = (TextView) convertView.findViewById(R.id.textSize);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {



        viewHolder.imgname.setText(mItemList.get(position).getDisplayname());
        viewHolder.hargacoret.setPaintFlags(viewHolder.hargacoret.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.description.setText(mItemList.get(position).getItemDescription());

        viewHolder.itemno.setText(mItemList.get(position).getItemNumber());


        String kategori = mItemList.get(position).getItemcategories();
        String rp = mItemList.get(position).getRealprice();
        String ep = mItemList.get(position).getEndprice();



        int jumDis=0;


        if (rp.equals("0")){
            viewHolder.hargacoret.setVisibility(View.GONE);
        }else{
            viewHolder.hargacoret.setVisibility(View.VISIBLE);
        }

        if (ep.equals("0")){
            viewHolder.lastprice.setVisibility(View.GONE);
        }else{
            viewHolder.lastprice.setVisibility(View.VISIBLE);
        }

        viewHolder.hargacoret.setText("Rp "+ MyConfig.konversi(mItemList.get(position).getRealprice()));;
        viewHolder.lastprice.setText("Rp "+ MyConfig.konversi(mItemList.get(position).getEndprice()) +" / "+ mItemList.get(position).getUm());
        viewHolder.size.setText(mItemList.get(position).getSizeandcolor());


        Glide.with(context)
                .load(mItemList.get(position).getSource())
                .into(viewHolder.img_android);


        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) clickListener.onClick(view, position);
            }
        });

        /*String item = mItemList.get(position);
        viewHolder.tvItem.setText(item);*/

    }

    public String konvertTanggal(String dates){
        String finalDateString="";
        String strDate = "2017-05-23T06:25:50";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dates);
            SimpleDateFormat sdfnewformat = new SimpleDateFormat("dd MMMM yyyy");
            finalDateString = sdfnewformat.format(convertedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finalDateString;
    }
}