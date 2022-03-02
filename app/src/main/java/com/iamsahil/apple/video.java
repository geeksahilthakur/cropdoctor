package com.iamsahil.apple;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class video extends AppCompatActivity {

    private Player ExoPlayer;
    TextView t;
    WebView wbv;




    String url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

      //  t = findViewById(R.id.t);
        wbv = findViewById(R.id.wbv);


        Intent i=getIntent();
        String A =i.getStringExtra("A");
        String D =i.getStringExtra("D");


       // t.setText((D).toString());

        //ExoPlayer simpleExoPlayer = new ExoPlayer.Builder(this).build();
        ExoPlayer player = new ExoPlayer.Builder(this).build();


       StyledPlayerView playerView = findViewById(R.id.exp);

        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(A);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();


        wbv.loadUrl((D).toString());
        wbv.getSettings().setJavaScriptEnabled(true);
        wbv.setWebViewClient(new WebViewClient());
        wbv.getSettings() .setDomStorageEnabled(true);






    }
}