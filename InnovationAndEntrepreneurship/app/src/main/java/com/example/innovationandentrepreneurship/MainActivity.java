package com.example.innovationandentrepreneurship;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";
    private LocationClient locationClient;
    private MapView mapView;
    private BaiduMap baiduMap;
    private Toolbar toolbar;
    private EditText mainSearchEditText;
    private Button mainSearchButton;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private boolean isFirstLocate =true;
    //private TextView positionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initView();
        initDo();
        initClick();
    }

    private void initClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        mainSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainSearchEditText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "请输入正确的球馆名称",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(MainActivity.this,SearchResult.class);
                    intent.putExtra("text",mainSearchEditText.getText().toString().trim());
                    startActivity(intent);
                }
            }
        });
    }

    private void initDo() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        locationClient=new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        //地图自动显示自己的位置
        baiduMap= mapView.getMap();
        //将自己显示在地图上
        baiduMap.setMyLocationEnabled(true);
        //positionTextView=findViewById(R.id.position_TextView);
        requestPermission();

        //导航栏
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_format_list_bulleted_black_24dp);
        }
        //搜索框
        //mainEditText.setCompoundDrawables(null,null,searchDrawable,null);
    }

    private void initView() {
        mapView =findViewById(R.id.baidu_MapView);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.main_DrawerLayout);
        navigationView=findViewById(R.id.navigationView);
        mainSearchEditText=findViewById(R.id.main_search_EditText);
        mainSearchButton=findViewById(R.id.main_search_Button);
        //navigationView.setCheckedItem(R.id.main_menu_group_call);
        //searchDrawable=getResources().getDrawable(R.drawable.ic_search_black_24dp);
        //searchDrawable.setBounds(0,0,80,80);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * 处理各种点击事件
         */
        switch (item.getItemId()){
            case R.id.toolbar_more_ImageView:
                Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 加载toolbar.xml这个菜单文件
         */
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    private void requestPermission() {
        /**
         * 请求权限
         */
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else {
            requestLocation();
        }
    }

    private void requestLocation() {
        /**
         * 地图
         */
        Log.d(TAG, "requestLocation: isFirstLocate "+isFirstLocate);
        initLocation();
        locationClient.start();
    }

    private void initLocation() {
        /**
         * 地图
         */
        Log.d(TAG, "initLocation: isFirstLocate "+isFirstLocate);
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        locationClient.setLocOption(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        Log.d(TAG, "onResume: isFirstLocate "+isFirstLocate);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        Log.d(TAG, "onPause: isFirstLocate "+isFirstLocate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        mapView.onDestroy();
        Log.d(TAG, "onDestroy: isFirstLocate "+isFirstLocate);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        /**
         * @param requestCode 请求码
         * @param permissions 权限
         * @param grantResults 结果
         */
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result:grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void navigateTo(BDLocation location){
        /**
         * @param location 地图
         */
        Log.d(TAG, "navigateTo: isFirstLocate "+isFirstLocate);
        if (isFirstLocate){
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            Log.d(TAG, "navigateTo: getLatitude "+latLng.toString());

            //定位到自己的位置
            MapStatus mapStatus=new MapStatus.Builder()
                    .target(latLng)
                    .zoom(16f)
                    .build();
            MapStatusUpdate mapStatusUpdate=MapStatusUpdateFactory.newMapStatus(mapStatus);
            baiduMap.setMapStatus(mapStatusUpdate);
//            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(latLng);
//            baiduMap.animateMapStatus(update);
//            update=MapStatusUpdateFactory.zoomTo(16f);
//            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }

        //将自己显示在地图上
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);

    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            /**
             * @param bdLocation 地图
             */
            Log.d(TAG, "onReceiveLocation: isFirstLocate "+isFirstLocate);
//            StringBuilder currentPosition=new StringBuilder();
//            currentPosition.append("维度：").append(bdLocation.getLatitude()).append("\n");
//            currentPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
//
//            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
//            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
//            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
//            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
//            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
//
//            currentPosition.append("定位方式：");
//            if (bdLocation.getLocType()==BDLocation.TypeGpsLocation){
//                currentPosition.append("GPS");
//            }else if (bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
//                currentPosition.append("网络");
//            }
//            positionTextView.setText(currentPosition);
            if (bdLocation.getLocType()==BDLocation.TypeGpsLocation
                    ||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }
}
