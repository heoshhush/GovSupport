package com.example.govsupport.listFrags;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.govsupport.R;
import com.example.govsupport.data.Detail;

import java.util.ArrayList;

public class ListFrag_6_detailsAdapter extends RecyclerView.Adapter<ListFrag_6_detailsAdapter.ViewHolder> {
    ArrayList<Detail> items = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_target;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_target = itemView.findViewById(R.id.tv_target);
        }

    }


    @NonNull
    @Override
    public ListFrag_6_detailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recyclerview_frag6, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFrag_6_detailsAdapter.ViewHolder holder, int position) {
        Detail item = items.get(position);
        holder.tv_title.setText(item.서비스명);
        holder.tv_target.setText(item.지원대상);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Detail> inputList){
        items = inputList;
        notifyDataSetChanged();
    }
}
