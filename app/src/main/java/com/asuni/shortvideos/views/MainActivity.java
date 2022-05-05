package com.asuni.shortvideos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asuni.shortvideos.R;

import com.asuni.shortvideos.adapter.CustomViewPagerAdapter;
import com.asuni.shortvideos.databinding.ActivityMainBinding;

import com.asuni.shortvideos.models.UserInfoModel;
import com.asuni.shortvideos.models.VideoDataModel;
import com.asuni.shortvideos.viewmodels.MainActivityVM;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
       activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
       activityMainBinding.setViewModel(new MainActivityVM(this));

    }
    Snackbar snackbar;

    CustomViewPagerAdapter pagerAdapter;

    int flag=0;

    public ViewPager viewPager;

    public void createUI( VideoDataModel videoDataModel[] ){

        pagerAdapter = new CustomViewPagerAdapter(this, videoDataModel );

        viewPager = activityMainBinding.verticalViewPager;
        viewPager.setAdapter( pagerAdapter );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {

            @Override public void onPageScrolled( int i, float v, int i1 ) { }
            @Override public void onPageSelected( int i ) {

                if( snackbar!=null )
                    snackbar.dismiss();

                if( i > flag ){

                    pagerAdapter.videoViews[ flag ].pause();
                    pagerAdapter.videoViews[ i ].start();

                }else{

                    pagerAdapter.videoViews[ flag ].pause();
                    pagerAdapter.videoViews[ i ].start();

                }

                flag=i;

            }
            @Override public void onPageScrollStateChanged(int i) {

            }

        });

        pagerAdapter.videoViews[flag].start();

    }

    public void userProfileCreate(View view1  , UserInfoModel userInfoModel){

        snackbar = Snackbar.make(view1 ,"",Snackbar.LENGTH_INDEFINITE);

        View view = getLayoutInflater().inflate(R.layout.layout_user_profile, null);

        try {
            InputStream in = new URL(userInfoModel.getProfilePic()).openStream();
            ImageView prifileBTN=view.findViewById(R.id.profileImage);
            prifileBTN.setImageBitmap( BitmapFactory.decodeStream(in) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView name=view.findViewById(R.id.name);
        name.setText( userInfoModel.getFirstName()+" "+userInfoModel.getLastName() );


        TextView userName=view.findViewById(R.id.userName);
        userName.setText( userInfoModel.getUsername() );


        ImageView close = view.findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        snackbarLayout.setPadding(20, 0, 20, 0);

        snackbarLayout.addView(view, 0);

        snackbar.show();
    }


    public ProgressDialog showPopup(){
        ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Please Wait.");
        progressDialog.show();
        return progressDialog;
    }

}