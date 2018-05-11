package com.edu.knowit.knowit.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.knowit.knowit.Models.FeedsItemModel;
import com.edu.knowit.knowit.Models.HomeItemModel;
import com.edu.knowit.knowit.R;

import java.util.ArrayList;

public class FeedsListAdapter  extends ArrayAdapter<FeedsItemModel> implements View.OnClickListener{

    String TAG="";

    private ArrayList<FeedsItemModel> dataSet;//create array object list
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        HomeItemModel item;
        TextView txtId;
        TextView txtAuthor;
        TextView txtDate;
        TextView txtTitle;
        ImageView imgImage;
        TextView txtDesc;
        Button btnLike;
        Button btnComment;
        Button btnDislike;
    }

    public FeedsListAdapter(ArrayList<FeedsItemModel> data, Context context) {
        super(context, R.layout.row_feed_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HomeItemModel dataModel=(HomeItemModel) object;
        switch (v.getId())
        {
            case R.id.like:
                System.out.println("Like button call"+dataModel.getAuthor());
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FeedsItemModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        FeedsListAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new FeedsListAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_feed_item, parent, false);

//            viewHolder.txtId = (TextView) convertView.findViewById(R.id.id);
             viewHolder.imgImage = (ImageView) convertView.findViewById(R.id.image);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FeedsListAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

//        viewHolder.txtId.setText(dataModel.getId());

        viewHolder.imgImage.setVisibility(View.INVISIBLE);


        // Return the completed view to render on screen
        return convertView;
    }

}
