package com.mayhub.doingsomething.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.Calendar;

/**
 * Created by daihai on 2015/8/27.
 */
public class ImageUtils {

    public interface ChooserType {
        int REQUEST_PICK_PICTURE = 291;

        int REQUEST_CAPTURE_PICTURE = 294;

        int REQUEST_CAPTURE_VIDEO = 292;

        int REQUEST_PICK_VIDEO = 295;

        int REQUEST_PICK_PICTURE_OR_VIDEO = 300;

        int REQUEST_PICK_FILE = 500;
    }

    public static void chooseImageFromPhoto(Activity context) throws Exception{
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            context.startActivityForResult(intent, ChooserType.REQUEST_PICK_PICTURE);
    }

    public static String chooseImageFromCamera(Activity context) throws Exception{
            File file;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(file = new File(ImageLoader.getInstance().getDiskCache().getDirectory(), Calendar.getInstance().getTimeInMillis() + ".jpg")));
            context.startActivityForResult(intent, ChooserType.REQUEST_CAPTURE_PICTURE);
        return "file;//"+file.getAbsolutePath();
    }


    /**
     * Returns the path of the folder specified in external storage
     * @param foldername
     * @return
     */
    public static String getDirectory(String foldername) {
        File directory = null;
        directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + foldername);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory.getAbsolutePath();
    }

}
