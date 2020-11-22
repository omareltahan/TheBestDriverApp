package com.thebest.resturant.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thebest.resturant.R;


public class ShareUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_us);
        findViewById(R.id.drawer).setOnClickListener(v -> startActivity(new Intent(this, DrawerView.class)));
        findViewById(R.id.call).setOnClickListener(v -> openCall());
        findViewById(R.id.facebook).setOnClickListener(v -> openFacebook("SuperBekala"));
        findViewById(R.id.mail).setOnClickListener(v -> openMail("info@superbekala.com"));
        findViewById(R.id.whatsapp).setOnClickListener(v -> openWhatsApp());
        findViewById(R.id.twitter).setOnClickListener(v -> openTwitter("SuperBekala"));
        findViewById(R.id.instgram).setOnClickListener(v -> openInstgram("SuperBekala"));
    }
    public void openFacebook(String  url) {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/733295540207770")));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"+url)));
        }
    }
    public void openTwitter(String  url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?screen_name="
                            .concat(url))));

        } catch (Exception e) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/#!/".concat(url))));
        }
    }
    public void openInstgram(String  url) {
        try {
            getPackageManager().getPackageInfo("com.instagram.android", 0);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/"+url)));
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/"+url)));
        }
    }
    public void openWhatsApp() {
        Uri uri = Uri.parse("smsto:" + "201066672019");
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }
    public void openMail(String  url) {
        try{
            Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + url));
            intent.putExtra(Intent.EXTRA_SUBJECT, "superbekala");
            intent.putExtra(Intent.EXTRA_TEXT, "i have ask for : ");
            startActivity(intent);
        }catch(Exception e){
            //TODO smth
        }
    }
    public void openCall() {
        String uri = "tel:01066672019" ;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
