package com.example.sbpinilla.imgpiker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import es.guiguegon.gallerymodule.GalleryActivity;
import es.guiguegon.gallerymodule.GalleryHelper;
import es.guiguegon.gallerymodule.model.GalleryMedia;

public class MainActivity extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new GalleryHelper()
                        .setMultiselection(true)

                        .setMaxSelectedItems(100)
                        .setShowVideos(false)
                        .getCallingIntent(activity), 100);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<GalleryMedia> galleryMedias =
                    data.getParcelableArrayListExtra(GalleryActivity.RESULT_GALLERY_MEDIA_LIST);


            for (GalleryMedia galleryMedia:galleryMedias) {



                Uri myUri = Uri.parse(galleryMedia.mediaUri());

                Log.d("MainActivity","myUri.getPath() : "+myUri.getPath());
            }

        }

    }


}
