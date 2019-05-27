package com.example.zomato.ui.restaurants.fragments.collectionsFragment.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomato.R;
import com.example.zomato.client.responses.collections.CollectionsItem;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsItemVH extends RecyclerView.ViewHolder {

    @BindView(R.id.collection_imageview)
    ImageView collectionImage;

    @BindView(R.id.collection_name)
    TextView collectionName;


    private CollectionsItem collectionsItem;
    private String defaultCollectionImage = "http://www.realdetroitweekly.com/wp-content/uploads/2017/06/Restaurants.jpg";

    public CollectionsItemVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(collectionsItem);
            }
        });
    }

    public void setData(CollectionsItem collectionsItem){
        this.collectionsItem = collectionsItem;

        String restaurantImageUrl = collectionsItem.getCollection().getImageUrl();
        String url = restaurantImageUrl.isEmpty() ? defaultCollectionImage : restaurantImageUrl;
        Picasso.get().load(url).into(collectionImage);

        collectionName.setText(collectionsItem.getCollection().getTitle());
    }

}
