package com.example.anoop.applauncher;

import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     private TextView resultText;
    private ImageView imageView;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //resultText=(TextView)findViewById(R.id.textView1);
        //imageView=(ImageView) findViewById(R.id.imageView);
        //prompSpeechInput();
        //startService(new Intent(this, Service.class));
    }

    public void onButtonClick(View v){

        if (v.getId()==R.id.imageButton){

            //resultText.setText("");
            prompSpeechInput();
        }

    }

    public void prompSpeechInput(){
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say some thing !");


        try{
            startActivityForResult(i,100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(MainActivity.this,"SORRY ",Toast.LENGTH_LONG).show();
        }

    }

    public void MenuOpen(View view){

        switch(view.getId()) {

            case R.id.homeico:
                Intent iuuntent = new Intent(this, Home.class);

                startActivity(iuuntent);

                break;
            case R.id.infoicon:
                //fragmentTransaction=getSupportFragmentManager().beginTransaction();
                //fragmentTransaction.replace(R.id.main_container,new AboutUsFragment());
                //fragmentTransaction.commit();
                Intent iuuuntent = new Intent(this, Main2Activity.class);

                startActivity(iuuuntent);
                break;



            case R.id.wtsapp:
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(launchIntent);
                break;
            case R.id.setting:
                Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
                break;
            case R.id.gallery:
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(it);
                break;
            case R.id.dial:
                Intent itt = new Intent("android.intent.action.DIAL");
                startActivity(itt);

                break;
            case R.id.chrome:
                Uri uriUrl = Uri.parse("https://www.google.co.in/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);

                break;
            case R.id.camera:
                Intent intt = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intt);

                break;
            case R.id.gmail:
                Intent intet = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                startActivity(intet);

                break;
            case R.id.music:
                Intent intnt = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                startActivity(intnt);

                break;
            case R.id.file:
                Intent iintent = new Intent();
                iintent.setAction(Intent.ACTION_GET_CONTENT);
                iintent.setType("file/*");
                startActivity(iintent);

                break;
            case R.id.play:
                Intent launcIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                startActivity(launcIntent);

                break;


        }



    }
    public void onActivityResult(int request_code, int result_code,Intent i){

        super.onActivityResult(request_code,result_code,i);
        switch(request_code){
            case 100 : if(result_code == RESULT_OK && i !=null ){
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                //resultText.setText(result.get(0).toString());

                String sentence = result.get(0).toString();
                System.out.println(sentence);
                String search1  = "start";
                String search01  = "open";
                String search2  = "camera";
                String search3  = "gallery";
                String search4  = "browser";
                String search5  = "whatsapp";
                String search6  = "hike";
                String search7  = "settings";
                String search8  = "music";
                String search9  = "facebook";
                String search10  = "dialler";
                String search11  = "gmail";

                if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search2.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search2);
                    Toast.makeText(getApplicationContext(),"Opening "+search2,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);

                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search3.toLowerCase()) != -1) ){

                   // resultText.setText("Opening "+search3);
                    Toast.makeText(getApplicationContext(),"Opening "+search3,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                    startActivity(intent);

                }else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search4.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search4);
                    Toast.makeText(getApplicationContext(),"Opening "+search4,Toast.LENGTH_SHORT).show();
                    Uri uriUrl = Uri.parse("https://www.google.co.in/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);


                }else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search5.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search5);
                    Toast.makeText(getApplicationContext(),"Opening "+search5,Toast.LENGTH_SHORT).show();
                    try {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                        startActivity(launchIntent);
                    }catch (Exception e ){
                        //resultText.setText("Please install "+search5);
                        Toast.makeText(getApplicationContext(),"Please install "+search5,Toast.LENGTH_SHORT).show();

                    }


                }

                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search6.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search6);
                    Toast.makeText(getApplicationContext(),"Opening "+search6,Toast.LENGTH_SHORT).show();
                    try {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.hike");
                        startActivity(launchIntent);
                    }catch (Exception e ){
                        Toast.makeText(getApplicationContext(),"Please install hike",Toast.LENGTH_SHORT).show();
                        //resultText.setText("Please install hike");

                    }


                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search7.toLowerCase()) != -1) ){

                   // resultText.setText("Opening "+search7);
                    Toast.makeText(getApplicationContext(),"Opening "+search7,Toast.LENGTH_SHORT).show();


                    Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search8.toLowerCase()) != -1) ){

                   // resultText.setText("Opening "+search8);
                    Toast.makeText(getApplicationContext(),"Opening "+search8,Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                    try {
                        //Intent intent = new Intent("android.intent.category.APP_MUSIC");
                        Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Please Try Again",Toast.LENGTH_SHORT).show();
                        //resultText.setText("Please Try Again");
                    }

                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search9.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search9);
                    Toast.makeText(getApplicationContext(),"Opening "+search9,Toast.LENGTH_SHORT).show();
                    try {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                        startActivity(launchIntent);
                    }catch (Exception e ){
                        Toast.makeText(getApplicationContext(),"Please install "+search9,Toast.LENGTH_SHORT).show();
                        //resultText.setText("Please install "+search9);

                    }
                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search10.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search10);
                    Toast.makeText(getApplicationContext(),"Opening "+search10,Toast.LENGTH_SHORT).show();
                    try {
                        Intent intent = new Intent("android.intent.action.DIAL");
                        startActivity(intent);
                    }catch (Exception e ){
                        Toast.makeText(getApplicationContext(),"Please install "+search10,Toast.LENGTH_SHORT).show();
                        //resultText.setText("Please install "+search10);

                    }
                }
                else if ( (sentence.toLowerCase().indexOf(search1.toLowerCase()) != -1 || sentence.toLowerCase().indexOf(search01.toLowerCase()) != -1 )&& ( sentence.toLowerCase().indexOf(search11.toLowerCase()) != -1) ){

                    //resultText.setText("Opening "+search11);
                    Toast.makeText(getApplicationContext(),"Opening "+search11,Toast.LENGTH_SHORT).show();
                    try {
                        Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                        startActivity(intent);
                    }catch (Exception e ){
                        Toast.makeText(getApplicationContext(),"Please install "+search11,Toast.LENGTH_SHORT).show();
                        //resultText.setText("Please install "+search11);

                    }
                }
                else {

                    //resultText.setText("Please try again");
                    Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_SHORT).show();

                }
                //resultText.setText("Hello");

            }
                break;
        }
    }
    @Override
    public void onBackPressed() {
    }




}
