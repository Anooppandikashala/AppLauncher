package com.example.anoop.applauncher;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private ListView list;
    private PackageManager manager;
    private  List<Item> apps;


   // ListView userInstalledApps = (ListView)findViewById(R.id.);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadApps();
        loadListView();
        addClickListner();

    }

    private void loadApps(){

        manager =getPackageManager();
        apps=new ArrayList<>();

        Intent i =new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities =manager.queryIntentActivities(i,0);

        for(ResolveInfo ri:availableActivities){


            Item app=new Item();


            app.label=ri.activityInfo.packageName;
            app.name=ri.loadLabel(manager);
            app.icon=ri.loadIcon(manager);
            apps.add(app);

        }


    }

    /*private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(new AppList(appName, icon));
            }
        }
        return res;
    }*/

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    private void loadListView(){

        list=(ListView)findViewById(R.id.installed_app_list);
        ArrayAdapter<Item> adapter =new ArrayAdapter<Item>(this,R.layout.installed_app_list,apps){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

                if(convertView ==null){
                    convertView =getLayoutInflater().inflate(R.layout.installed_app_list,null);
                }

                ImageView appIcon =(ImageView) convertView.findViewById(R.id.app_icon);
                appIcon.setImageDrawable(apps.get(position).icon);

                TextView appName=(TextView)convertView.findViewById(R.id.list_app_name);
                appName.setText(apps.get(position).name);

                return  convertView;

            }




        };

        list.setAdapter(adapter);

    }

    private  void addClickListner(){

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position,long id){
                Intent i=manager.getLaunchIntentForPackage(apps.get(position).label.toString());
                startActivity(i);
            }
        });
    }









}
