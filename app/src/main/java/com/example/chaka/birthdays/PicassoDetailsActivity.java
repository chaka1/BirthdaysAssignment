package com.example.chaka.birthdays;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;


public class PicassoDetailsActivity extends ActionBarActivity {

    Birthday birthday;

    ImageView networkImageView;

    TextView nameText;
    TextView idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_details);


        birthday = getIntent().getParcelableExtra("Birthday");

        networkImageView = (ImageView) findViewById(R.id.imgNetwork);
        nameText = (TextView)findViewById(R.id.nameText);
        idText = (TextView)findViewById(R.id.idText);


        Picasso.with(this).load(birthday.getPicture()).into(networkImageView);
        // If you are using NetworkImageView
        //networkImageView.setImageUrl(birthday.getPicture(), imageLoader);
        nameText.setText(birthday.getName());
        idText.setText(birthday.getId());

        ViewCompat.setTransitionName(networkImageView, getString(R.string.transition_name_circle));
        ViewCompat.setTransitionName(nameText, getString(R.string.transition_name_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picasso_details, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
