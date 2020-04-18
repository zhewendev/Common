package com.baiheng.common.network.utils;

import java.io.InputStream;
import java.lang.reflect.Field;

import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * author：zhewen description： date： 4/18/2020 version：
 */
public class ImageUtils {
    
    public static ImageSize getImageSize(InputStream imageStream) {
        
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(imageStream, null, options);
        return new ImageSize(options.outWidth, options.outHeight);
    }
    
    public static class ImageSize {
        int width;
        int height;
        
        public ImageSize() {
        
        }
        
        public ImageSize(int width, int height) {
            
            this.width = width;
            this.height = height;
        }
        
        @Override
        public String toString() {
            
            return "ImageSize{" + "width=" + width + ", height=" + height + '}';
        }
    }
    
    public static int calculateInSampleSize(ImageSize srcSize, ImageSize targetSize) {
        
        // width of source image
        int width = srcSize.width;
        int height = srcSize.height;
        int inSampleSize = 1;
        
        int reqWidth = targetSize.width;
        int reqHeight = targetSize.height;
        
        if (width > reqWidth && height > reqHeight) {
            // Calculate the ratio of actual width to target width
            int widthRatio = Math.round((float) width / (float) reqWidth);
            int heightRatio = Math.round((float) height / (float) reqHeight);
            inSampleSize = Math.max(widthRatio, heightRatio);
        }
        return inSampleSize;
    }
    
    /**
     * Compressed width and height according to ImageView.
     *
     * @param view
     * @return
     */
    public static ImageSize getImageViewSize(View view) {
        
        ImageSize imageSize = new ImageSize();
        
        imageSize.width = getExpectWidth(view);
        imageSize.height = getExpectHeight(view);
        
        return imageSize;
    }
    
    /**
     * High expectations based on view
     *
     * @param view
     * @return
     */
    private static int getExpectHeight(View view) {
        
        int height = 0;
        if (view == null)
            return 0;
        
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        // getHeight is not valid WRAP_CONTENT, the picture is not loaded at this time
        if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
            height = view.getHeight(); // Get the actual height
        }
        if (height <= 0 && params != null) {
            height = params.height; // Gets the height of the declaration in the layout file
        }
        
        if (height <= 0) {
            height = getImageViewFieldValue(view, "mMaxHeight");// Gets the maximum height of the settings
        }
        
        // If the width is still not obtained, use the height of the screen
        if (height <= 0) {
            DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
            height = displayMetrics.heightPixels;
        }
        
        return height;
    }
    
    private static int getExpectWidth(View view) {
        
        int width = 0;
        if (view == null)
            return 0;
        
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
            width = view.getWidth();
        }
        if (width <= 0 && params != null) {
            width = params.width;
        }
        
        if (width <= 0)
        
        {
            width = getImageViewFieldValue(view, "mMaxWidth");
        }
        if (width <= 0)
        
        {
            DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
            width = displayMetrics.widthPixels;
        }
        
        return width;
    }
    
    /**
     * Gets a property value of a imageview by reflection
     *
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName) {
        
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
        }
        return value;
        
    }
}
