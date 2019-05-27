package com.example.zomato.utils;

//from : https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns

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
