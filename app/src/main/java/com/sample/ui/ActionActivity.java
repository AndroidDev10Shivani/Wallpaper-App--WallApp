package com.sample.ui;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ActionActivity extends AppCompatActivity {
    ConstraintLayout theme_preview;
    String newImage, ctrImage, ctrName;
    ImageView imageView, creatorImage, pixabayImage;
    TextView creatorName;

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    //public static final String EXTRA_LIKES = "likeCount";
    public static final String EXTRA_CREATOR_IMAGE = "creatorImage";

    private float xCoOrdinate, yCoOrdinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        theme_preview = findViewById(R.id.constrainLayout);
        imageView = findViewById(R.id.imageView_actionPage);
        creatorImage = findViewById(R.id.creator_img);
        creatorName = findViewById(R.id.CreatorName);
        pixabayImage = findViewById(R.id.pixabay_img);

        if (savedInstanceState == null) {
            newImage = getIntent().getStringExtra(EXTRA_URL).toString();
            ctrName = getIntent().getStringExtra(EXTRA_CREATOR).toString();
            ctrImage = getIntent().getStringExtra(EXTRA_CREATOR_IMAGE).toString();
            if (newImage != null) {
                Picasso.with(getApplicationContext()).load(newImage).fit().into(imageView);
                creatorName.setText(ctrName);

                if (!ctrImage.isEmpty())
                {
                    Picasso.with(getApplicationContext()).load(ctrImage).fit().centerInside().into(creatorImage);
                }
                else {
                    Picasso.with(getApplicationContext()).load("https://cdn.pixabay.com/photo/2016/10/25/18/27/unknown-1769656_960_720.png").fit().centerInside().into(creatorImage);
                }
            }
        }

        //Animation animation = AnimationUtils.loadAnimation(ActionActivity.this, R.anim.zoomin);
       //, imageView.startAnimation(animation);

        findViewById(R.id.shareButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.downloadButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.wallpaperButton).setVisibility(View.INVISIBLE);
        findViewById(R.id.creatorCard).setVisibility(View.INVISIBLE);

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkShareButtonVisibility = findViewById(R.id.shareButton).getVisibility();
                int checkDownloadButtonVisibility = findViewById(R.id.downloadButton).getVisibility();
                int checkWallpaperButtonVisibility = findViewById(R.id.wallpaperButton).getVisibility();

                if (checkShareButtonVisibility == View.VISIBLE && checkDownloadButtonVisibility == View.VISIBLE && checkWallpaperButtonVisibility == View.VISIBLE) {
                    findViewById(R.id.shareButton).setVisibility(View.GONE);
                    findViewById(R.id.downloadButton).setVisibility(View.GONE);
                    findViewById(R.id.wallpaperButton).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.shareButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.downloadButton).setVisibility(View.VISIBLE);
                    findViewById(R.id.wallpaperButton).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.infoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkCreatorCardVisibility = findViewById(R.id.creatorCard).getVisibility();

                if (checkCreatorCardVisibility == View.VISIBLE ) {
                    findViewById(R.id.creatorCard).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.creatorCard).setVisibility(View.VISIBLE);
                }
            }
        });

        findViewById(R.id.shareButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
                Uri imageUri = Uri.parse(path);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.setPackage("com.whatsapp");

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(share, "Select"));
                Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.downloadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.wallpaperButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
        findViewById(R.id.wallpaperBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });
    }
    private void setWallpaper()
    {
        final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        final Dialog dialog = new Dialog(ActionActivity.this);
        dialog.setContentView(R.layout.action_wallpaper);
        dialog.findViewById(R.id.home_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.lock_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.both_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM|WallpaperManager.FLAG_LOCK);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.cancel_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
