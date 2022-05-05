package com.asuni.shortvideos.adapter;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;

import com.asuni.shortvideos.R;
import com.asuni.shortvideos.models.VideoDataModel;
import com.asuni.shortvideos.views.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class CustomViewPagerAdapter extends PagerAdapter  {

    VideoDataModel []videoDataModels;
    MainActivity activity;

    public VideoView [] videoViews;

    public CustomViewPagerAdapter(MainActivity activity , VideoDataModel videosDataModel[]) {

        this.activity=activity;
        this.videoDataModels = videosDataModel ;
        videoViews=new VideoView[videosDataModel.length];
    }

    @Override public int getCount() {
        return videoDataModels.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public  Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.layout_video_views, container, false);

        TextView userName=view.findViewById(R.id.userNameTV);
        userName.setText( videoDataModels [position].getUserInfoModel().getUsername() );

        TextView videoName=view.findViewById(R.id.videoNameTV);
        videoName.setText( videoDataModels [position].getDescription() );


        InputStream in = null;

        try {
            in = new URL( videoDataModels [position].getSoundModel().getThum() ).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageButton thuBTN=view.findViewById(R.id.thuBTN);
        thuBTN.setImageBitmap( BitmapFactory.decodeStream(in) );


        TextView soundName=view.findViewById(R.id.soundName);
        soundName.setText( videoDataModels [position].getSoundModel().getSoundName() );


        try {
            in = new URL( videoDataModels [position].getUserInfoModel().getProfilePic() ).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageButton prifileBTN=view.findViewById(R.id.prifileBTN);
        prifileBTN.setImageBitmap( BitmapFactory.decodeStream(in) );
        prifileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.userProfileCreate( view, videoDataModels [position].getUserInfoModel()  );


            }
        });


        ImageButton likeBTN=view.findViewById(R.id.likeBTN);
        likeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Like",Toast.LENGTH_LONG).show();
            }
        });

        TextView likeTV=view.findViewById(R.id.likeTV);
        likeTV.setText( String.valueOf( videoDataModels[position].getCountModel().getLikeCount() ) );


        ImageButton commentBtn=view.findViewById(R.id.commentBtn);
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Comment",Toast.LENGTH_LONG).show();
            }
        });

        TextView commentTV=view.findViewById(R.id.commentTV);
        commentTV.setText( String.valueOf( videoDataModels[position].getCountModel().getVideoCommentCount() ));


        ImageButton shareBtn=view.findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Share",Toast.LENGTH_LONG).show();
            }
        });

        TextView shareTV=view.findViewById(R.id.shareTV);
        shareTV.setText( String.valueOf( videoDataModels[position].getCountModel().getView() ));

        Uri uri = Uri.parse( videoDataModels[position].getVideo());

        VideoView videoView=view.findViewById(R.id.videoView);

        videoView.setVideoURI( uri );

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                int i=activity.viewPager.getCurrentItem();
                activity.viewPager.setCurrentItem(i+1);

            }
        });

        videoViews[position]=videoView;

        final boolean[] start = {false};
        final VideoView[] videoView1 = new VideoView[1];

//        videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int i=activity.viewPager.getCurrentItem();
//                videoView1[0] =videoViews[i];
//
//                System.out.println( videoView1[0]  );
//
//
//                if(start[0]){
//
//                    videoView1[0].start();
//                    start[0] =false;
//
//                    Toast.makeText(activity,"Resume Video",Toast.LENGTH_SHORT).show();
//
//                }else{
//
//                    videoView1[0].pause();
//                    start[0] =true;
//
//                    Toast.makeText(activity,"Pause Video",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

        container.addView(view);

        return view;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

}