package com.example.zomato.utils;

import android.view.ViewTreeObserver;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class CustomScrollListener extends RecyclerView.OnScrollListener {

    private OnScrollChangedListener listener;

    public CustomScrollListener(OnScrollChangedListener listener) {
        this.listener = listener;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                System.out.println("The RecyclerView is not scrolling");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                System.out.println("Scrolling now");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                System.out.println("Scroll Settling");
                break;
        }
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dx > 0) {
            System.out.println("Scrolled Right");
        } else if (dx < 0) {
            System.out.println("Scrolled Left");
        } else {
//            listener.stoppedScrolling();
        }

        if (dy > 0) {
            listener.scrolledDown();
        } else if (dy < 0) {
            listener.scrolledUp();
        } else {
            System.out.println("No Vertical Scrolled");
        }
    }

    public interface OnScrollChangedListener {
         void scrolledDown();
         void scrolledUp();
         void stoppedScrolling();
    }
}