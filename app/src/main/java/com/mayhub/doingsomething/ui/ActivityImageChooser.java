package com.mayhub.doingsomething.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mayhub.doingsomething.R;
import com.mayhub.doingsomething.ui.adapter.ImageChooserAdapter;
import com.mayhub.doingsomething.ui.base.ActivityBaseNoTitle;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by daihai on 2015/8/28.
 */
public class ActivityImageChooser extends ActivityBaseNoTitle {

    public static final int REQUEST_GET_IMAGES = 0x101;

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private ImageChooserAdapter imageChooserAdapter;

    private ArrayList<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_chooser);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolbar();

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadImagesFromDir();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageChooserAdapter.resetData(images);
                    }
                });
            }
        }).start();

    }

    private void loadImagesFromDir(){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            getDirImageFilePath(Environment.getExternalStorageDirectory(),images);
        }
    }

    private void getDirImageFilePath(File file, ArrayList<String> imageUrls){
        if(file != null && file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (int i = 0 ;i < files.length;i++){
                    getDirImageFilePath(files[i],imageUrls);
                }
            }else{
                if(file.getName().endsWith(".jpg")){//正则为图片文件
                    imageUrls.add("file://" + file.getAbsolutePath());
                }
            }
        }
    }

    private void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.image_chooser_recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(imageChooserAdapter = new ImageChooserAdapter());
    }

    private void initToolbar(){
        toolbar.setTitle("添加碎片");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

    }

    @Override
    public void finish() {
        setResult(RESULT_OK, getIntent().putStringArrayListExtra("ImageList",imageChooserAdapter.getSelectedItems()));
        super.finish();
    }
}
