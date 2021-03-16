package com.bibit.project.simplerecylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import com.bibit.project.simplerecylerview.databinding.ActivityDetailBinding;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    Context context = this;
    ActivityDetailBinding binding;


    String itemnumber, itemdescription, sizeandcolor, um, um2, itemkategori, displayname, descriptionname,
            realprice, endprice, source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        binding.toolbar.setTitle("Detail Produk");
        binding.toolbar.setTitleTextColor(Color.WHITE);
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        setSupportActionBar(binding.toolbar);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        itemnumber = getIntent().getStringExtra("itemnumber");
        itemdescription = getIntent().getStringExtra("itemdescription");
        sizeandcolor = getIntent().getStringExtra("sizeandcolor");
        um = getIntent().getStringExtra("um");
        um2 = getIntent().getStringExtra("um2");
        itemkategori = getIntent().getStringExtra("itemkategori");
        displayname = getIntent().getStringExtra("displayname");
        descriptionname = getIntent().getStringExtra("descriptionname");
        realprice = getIntent().getStringExtra("realprice");
        endprice = getIntent().getStringExtra("endprice");
        source = getIntent().getStringExtra("source");


        binding.txtItemNumber.setText(itemnumber);
        binding.textNamaProduk.setText(displayname);
        binding.textSizeProduk.setText(sizeandcolor);
        binding.txtDesName.setText(displayname +" " + itemdescription);

        binding.txtRealPrice.setPaintFlags(binding.txtRealPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.txtRealPrice.setText("Rp " + MyConfig.konversi(realprice) + ",-");

        binding.txtNetPrice.setText("Rp " + MyConfig.konversi(endprice) + ",-");

        Spanned htmlAsSpanned = Html.fromHtml(descriptionname);
        binding.txtDeskripsiProduk.setText(htmlAsSpanned);

        Glide.with(context)
                .load(source)
                .into(binding.imgProduk);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}