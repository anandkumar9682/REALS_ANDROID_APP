package com.asuni.shortvideos.viewmodels;

import android.app.ProgressDialog;
import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.asuni.shortvideos.models.VideoDataModel;
import com.asuni.shortvideos.networkutil.ServerURL;
import com.asuni.shortvideos.views.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivityVM {

    private MainActivity mainActivity;

    public MainActivityVM( MainActivity mainActivity ){
        this.mainActivity=mainActivity;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fetchingData( );
    }

    ProgressDialog progressDialog;

    public void fetchingData( ){

        progressDialog=mainActivity.showPopup();

        RequestQueue queue = Volley.newRequestQueue(mainActivity);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ServerURL.videoURL ,null,
                new Response.Listener<JSONObject>() {
                    @Override public void onResponse(JSONObject response) {
                        try {

                            System.out.println(response );

                            VideoDataModel videoDataModelList [] =parsing ( response );
                            mainActivity.createUI(videoDataModelList);

                            progressDialog.dismiss();

                        } catch (Exception e) {
                            System.out.println( e );
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        queue.add(request);

    }

    VideoDataModel videoDataModelList[];

    public VideoDataModel[] parsing(JSONObject jsonObject1 ) throws Exception{

        JSONArray msgJsonArray=jsonObject1.getJSONArray("msg");
        videoDataModelList=new VideoDataModel[msgJsonArray.length()];

        for ( int i=0 ; i< videoDataModelList.length ; i++){

            JSONObject jsonObject= (JSONObject) msgJsonArray.get(i);

            VideoDataModel videoDataModel=new VideoDataModel();

            try{
                videoDataModel.setTp( jsonObject.getInt("tp") );
                videoDataModel.setLinked( jsonObject.getInt("liked") );
                videoDataModel.setIsBlock( jsonObject.getInt("is_block") );
                videoDataModel.setScore( jsonObject.getInt("score") );
                videoDataModel.setId( jsonObject.getInt("id") );
                videoDataModel.set__v( jsonObject.getInt("__v") );

                videoDataModel.setFbId( jsonObject.getString("fb_id") );
                videoDataModel.setCity( jsonObject.getString("city") );
                videoDataModel.set_id( jsonObject.getString("_id") );
                videoDataModel.setUid( jsonObject.getString("uid") );
                videoDataModel.setDescription( jsonObject.getString("description") );
                videoDataModel.setStatus( jsonObject.getString("status") );
                videoDataModel.setVideo( jsonObject.getString("video") );
                videoDataModel.setGif( jsonObject.getString("gif") );
                videoDataModel.setThum(  jsonObject.getString("thum") );
            }catch (Exception e){ System.out.println( " videos : "+ e ); }

            try{
                JSONObject userInfoJson=(JSONObject)jsonObject.getJSONObject("user_info");

                videoDataModel.getUserInfoModel().setFirstName( userInfoJson.getString( "first_name"));
                videoDataModel.getUserInfoModel().setLastName( userInfoJson.getString( "last_name"));
                videoDataModel.getUserInfoModel().setFbId( userInfoJson.getString( "fb_id"));
                videoDataModel.getUserInfoModel().setProfilePic( userInfoJson.getString( "profile_pic"));
                videoDataModel.getUserInfoModel().setGender( userInfoJson.getString( "gender"));
                videoDataModel.getUserInfoModel().setVerified( userInfoJson.getString( "verified"));
                videoDataModel.getUserInfoModel().set_id( userInfoJson.getString( "_id"));
                videoDataModel.getUserInfoModel().setUsername( userInfoJson.getString( "username"));
            }catch (Exception e){ System.out.println( " user info : "+ e ); }

            try{
                JSONObject countJson=(JSONObject)jsonObject.getJSONObject("count");

                videoDataModel.getCountModel().setLikeCount( countJson.getInt( "like_count"));
                videoDataModel.getCountModel().setVideoCommentCount( countJson.getInt( "video_comment_count"));
                videoDataModel.getCountModel().setView( countJson.getInt( "view"));
                videoDataModel.getCountModel().set_id( countJson.getString( "_id"));
            }catch (Exception e){ System.out.println( " count : "+ e ); }

            try{
                JSONObject soundJson=(JSONObject)jsonObject.getJSONObject("sound");

                videoDataModel.getSoundModel().setId( soundJson.getInt( "id"));
                videoDataModel.getSoundModel().setSoundName( soundJson.getString( "sound_name"));
                videoDataModel.getSoundModel().setDescription( soundJson.getString( "description"));
                videoDataModel.getSoundModel().setThum( soundJson.getString( "thum"));
                videoDataModel.getSoundModel().setSection( soundJson.getString( "section"));
                videoDataModel.getSoundModel().set_id( soundJson.getString( "_id"));
                videoDataModel.getSoundModel().setCreated( soundJson.getString( "created"));

                JSONObject audioPathJson=(JSONObject)soundJson.getJSONObject("audio_path");

                videoDataModel.getSoundModel().getAudioPathModel().setMp3( audioPathJson.getString( "mp3"));
                videoDataModel.getSoundModel().getAudioPathModel().setAcc( audioPathJson.getString( "acc"));

            }catch (Exception e){ System.out.println( " sound : "+ e ); }

            videoDataModelList[i]=videoDataModel;

        }

        return videoDataModelList;
    }
}