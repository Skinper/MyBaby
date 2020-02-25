package br.ufop.wagner.mybabywagner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ItemListeners implements RecyclerView.OnTouchListener, RecyclerView.OnItemTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public interface  OnItemClickListeners {
        void onClick(View view, int postion);
    }

    private GestureDetector gestureDetector;
    private OnItemClickListeners onItemTouchListener;

    public ItemListeners(Context context, OnItemClickListeners onItemTouchListener) {
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        this.onItemTouchListener = onItemTouchListener;
    }


    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && onItemTouchListener != null &&
                gestureDetector.onTouchEvent(e)){         onItemTouchListener.onClick(childView,rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
