package com.bibit.project.simplerecylerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bibit.project.simplerecylerview.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    ActivityMainBinding binding;
    Context context = this;

    List<ModelItem> listItem = new ArrayList<ModelItem>();
    AdapterItem adapterItem;
    LinearLayoutManager linearLayoutManager;

    ProgressDialog progressDialog;
    int offset=0;
    int no=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.toolbar.setTitle("Simple RecyclerView");
        binding.toolbar.setTitleTextColor(Color.WHITE);

        listItem = new ArrayList<>();
        progressDialog = new ProgressDialog(context);

        binding.rcItem.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rcItem.setLayoutManager(linearLayoutManager);
        adapterItem = new AdapterItem(context,listItem);
        binding.rcItem.setAdapter(adapterItem);
        adapterItem.setClickListener(this);

        callItem("201", "1", 0);

        binding.buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AboutActivity.class));
            }
        });
    }

    public void callItem(String warehouse, String promoCategory, int page) {
        listItem.clear();
        progressDialog.setMessage("Loading Data");
        progressDialog.setCancelable(false);
        progressDialog.show();
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        final String url = NetworkState.getURL()+"api/eventprice.php?offset="+page+"&warehouse="
                +warehouse+"&promoCategory="+promoCategory;
        final String urlgambar = NetworkState.getURL()+"storage/masterItem/image/android/" ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                progressDialog.dismiss();
                String res = response.trim();
                if(!res.equals("0")){

                    try {
                        JSONArray jsonarray=new JSONArray(response);
                        for(int i=0;i<jsonarray.length();i++){
                            JSONObject users = jsonarray.getJSONObject(i);

                            no = users.getInt("no");

                            String itemnumber = users.getString("ItemNumber");
                            String itemdescription = users.getString("ItemDescription");
                            String sizeandcolor = users.getString("SizeAndColor");
                            String um = users.getString("UM");
                            String um2 = users.getString("UM2");
                            String itemkategori = users.getString("ItemCategories").trim();
                            String displayname = users.getString("DisplayName");
                            String descriptionname = users.getString("DescriptionName");
                            String realprice = users.getString("RealPrice");
                            String endprice = users.getString("EndPrice");
                            String source = urlgambar + itemkategori +"/"+users.getString("ImageUrl");

                            ModelItem postItem = new ModelItem(itemnumber, itemdescription, sizeandcolor, um, um2,
                                    itemkategori, displayname, descriptionname, sizeandcolor, realprice, endprice,
                                    source);

                            listItem.add(postItem);

                            if (no > offset) offset=no;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapterItem.notifyDataSetChanged();
                }else{
                    adapterItem.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("error", error.getMessage().toString());
                adapterItem.notifyDataSetChanged();

            }
        });
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        mRequestQueue.add(stringRequest);

    }


    @Override
    public void onClick(View view, int positon) {
        ModelItem modelItem = listItem.get(positon);

        String itemnumber = modelItem.getItemNumber();
        String itemdescription = modelItem.getItemDescription();
        String sizeandcolor = modelItem.getSizeandcolor();
        String um = modelItem.getUm();
        String um2 = modelItem.getUm2();
        String itemkategori = modelItem.getItemcategories();
        String displayname = modelItem.getDisplayname();
        String descriptionname = modelItem.getDescriptionname();
        String realprice = modelItem.getRealprice();
        String endprice = modelItem.getEndprice();
        String source = modelItem.getSource();

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("itemnumber", itemnumber);
        intent.putExtra("itemdescription", itemdescription);
        intent.putExtra("sizeandcolor", sizeandcolor);
        intent.putExtra("um", um);
        intent.putExtra("um2", um2);
        intent.putExtra("itemkategori", itemkategori);
        intent.putExtra("displayname", displayname);
        intent.putExtra("descriptionname", descriptionname);
        intent.putExtra("realprice", realprice);
        intent.putExtra("endprice", endprice);
        intent.putExtra("source", source);
        startActivity(intent);


    }
}