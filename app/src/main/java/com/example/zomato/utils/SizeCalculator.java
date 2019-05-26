package com.example.zomato.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class SizeCalculator {

        public static int calculateNoOfColumns(Context context, float columnWidthDp) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5);
            return noOfColumns;
        }

}
