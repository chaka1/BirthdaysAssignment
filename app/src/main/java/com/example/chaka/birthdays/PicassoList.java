package com.example.chaka.birthdays;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PicassoList extends ActionBarActivity {

    public static final String URL_BIRTHDAY = "https://cleverbug-staging.appspot.com/_ah/api/friends/v1/friends.list.test";

    RecyclerView birthdayRecyclerView;

    List<Birthday> birthdayList;

    private ProgressDialog pDialog;

    private String tag_birthdays = "birthdays";

    private BirthdayAdapter birthdayAdapter;

    LinearLayoutManager llm;

    Context mContext;

    private static final String tag_id = "id";
    private static final String tag_name = "name";
    private static final String tag_first_name = "first_name";
    private static final String tag_last_name = "last_name";
    private static final String tag_close_friend = "close_friend";
    private static final String tag_gender = "gender";
    private static final String tag_picture = "picture";
    private static final String tag_picture_orig = "picture_orig";
    private static final String tag_card_sent = "card_sent";
    private static final String tag_significant_other = "significant_other";
    private static final String tag_weight = "weight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        birthdayRecyclerView = (RecyclerView) findViewById(R.id.rvBirthdayList);

        birthdayList = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        birthdayRecyclerView.setLayoutManager(llm);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        birthdayRecyclerView.addOnItemTouchListener(
                new RecyclerClickerListener(this, new RecyclerClickerListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(PicassoList.this, PicassoDetailsActivity.class);
                        //intent.putExtra(DetailsActivity.ID, Contact.CONTACTS[position].getId());
                        intent.putExtra("Birthday",birthdayList.get(position));
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                // the context of the activity
                                PicassoList.this,

                                // For each shared element, add to this method a new Pair item,
                                // which contains the reference of the view we are transitioning *from*,
                                // and the value of the transitionName attribute
                                new Pair<View, String>(view.findViewById(R.id.img),
                                        getString(R.string.transition_name_circle)),
                                new Pair<View, String>(view.findViewById(R.id.nameText),
                                        getString(R.string.transition_name_name))
                        );
                        ActivityCompat.startActivity(PicassoList.this, intent, options.toBundle());
                    }
                }));

        makeBirthdaysRequest();


    }

    private void makeBirthdaysRequest() {

        showProgressDialog();



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                URL_BIRTHDAY,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyLog.d(response.toString());
                        JSONObject jsonObj = response;
                        try {
                            // Getting JSON Array node
                            JSONArray birthdays = jsonObj.getJSONArray(tag_birthdays);

                            // looping through All Birthdays
                            for (int i = 0; i < birthdays.length(); i++) {
                                JSONObject b = birthdays.getJSONObject(i);
                                Birthday birthday = new Birthday();

                                birthday.setId(b.getString(tag_id));
                                birthday.setName(b.getString(tag_name));
                                birthday.setFirstName(b.getString(tag_first_name));
                                birthday.setLastName(b.getString(tag_last_name));
                                birthday.setCloseFriend(b.getBoolean(tag_close_friend));
                                birthday.setGender(b.getString(tag_gender));
                                birthday.setPicture(b.getString(tag_picture));
                                birthday.setPictureOrig(b.getString(tag_picture_orig));
                                birthday.setCardSent(b.getBoolean(tag_card_sent));
                                birthday.setSignificantOther(b.getBoolean(tag_significant_other));
                                birthday.setWeight(b.getString(tag_weight));

                                birthdayList.add(birthday);

                            }
                            //contactAdapter = new BirthdayAdapter(contactsList);
                            //mBirthdayList.setAdapter(contactAdapter);
                            birthdayAdapter = new BirthdayAdapter(birthdayList);
                            birthdayRecyclerView.setAdapter(birthdayAdapter);
                            pDialog.hide();
                        } catch (JSONException e) {
                            e.printStackTrace();

                            hideProgressDialog();
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_birthdays);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        Intent intent;
        if(id == R.id.action_fresco){
            intent = new Intent(this,FrescoList.class);
            startActivity(intent);

        }

        if(id == R.id.action_picasso){
            intent = new Intent(this,PicassoList.class);
            startActivity(intent);

        }

        if(id == R.id.action_volley){
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.ButtonViewHolder> {

        private List<Birthday> pinList;

        public BirthdayAdapter(List<Birthday> pinList) {
            this.pinList = pinList;
        }

        @Override
        public int getItemCount() {
            return pinList.size();
        }

        @Override
        public void onBindViewHolder(final ButtonViewHolder pinViewHolder,int i) {
            final Birthday cd = pinList.get(i);

            Picasso.with(mContext).load(cd.getPicture()).into(pinViewHolder.vPhoto);
            pinViewHolder.vNameText.setText(cd.getName());
            pinViewHolder.vIdText.setText(cd.getId());

        }

        @Override
        public ButtonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            View itemView;

            itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_picasso_birthday, viewGroup, false);

            return new ButtonViewHolder(itemView);
        }

        public class ButtonViewHolder extends RecyclerView.ViewHolder {

            protected ImageView vPhoto;
            protected TextView vNameText;
            protected TextView vIdText;
            protected View vView;


            public ButtonViewHolder(View v) {
                super(v);
                vPhoto = (ImageView)v.findViewById(R.id.img);
                vNameText = (TextView)v.findViewById(R.id.nameText);
                vIdText = (TextView)v.findViewById(R.id.idText);
                vView = v;




            }
        }

    }
}
