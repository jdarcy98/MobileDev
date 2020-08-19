package com.example.admin.fcbarcelona;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/*
Class that displays a players image, as an image button and prompts the user to touch
the button which will bring them to that player's highlights on YouTube
*/
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Intent that passes an index from a switch statement further down that determines which player's image is to be shown
        Intent in = getIntent();
        int index = in.getIntExtra("com.example.admin.PLAYER_INDEX", -1);

        if(index > -1) {
            int pic = getImg(index);
            ImageButton img = (ImageButton) findViewById(R.id.imageButton);
            scaleImg(img, pic);
            img.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //Bringing the user to a YouTube playlist full of each player's individual highlights
                    String yt = "https://www.youtube.com/playlist?list=PLO5zfjUkOaXjdX6jFEURWTqKx3kXrkyKA";
                    Uri webaddress = Uri.parse(yt);

                    Intent gotoYT = new Intent(Intent.ACTION_VIEW, webaddress);
                    if(gotoYT.resolveActivity(getPackageManager())!= null)
                    {
                        startActivity(gotoYT);

                    }
                }
            });
        }

    }

    //Different cases for different players, corresponding to the list in Main Activity
    private int getImg(int index) {
        switch (index) {
            case 0: return R.drawable.terstegen;
            case 1: return R.drawable.semedo;
            case 2: return R.drawable.pique;
            case 3: return R.drawable.rakitic;
            case 4: return R.drawable.busquets;
            case 5: return R.drawable.dsuarez;
            case 6: return R.drawable.coutinho;
            case 7: return R.drawable.arthur;
            case 8: return R.drawable.suarez;
            case 9: return R.drawable.messi;
            case 10: return R.drawable.dembele;
            case 11: return R.drawable.rafinha;
            case 12: return R.drawable.cillessen;
            case 13: return R.drawable.malcom;
            case 14: return R.drawable.lenglet;
            case 15: return R.drawable.alba;
            case 16: return R.drawable.roberto;
            case 17: return R.drawable.vidal;
            case 18: return R.drawable.umtiti;

            default: return -1;
        }
    }

    //The images were too big for the screen, so by using bitmap functions the images dimensions are rounded to fit into the button
    private void scaleImg(ImageButton img, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imgWidth > screenWidth) {
            int ratio = Math.round((float)imgWidth/(float)screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
    }
}