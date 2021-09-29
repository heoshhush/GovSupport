package com.example.govsupport.listFrags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.govsupport.R;
import com.example.govsupport.data.Detail;
import com.example.govsupport.data.ServiceItem;

import java.util.ArrayList;

public class ListFrag_6_serviceListAdapter extends RecyclerView.Adapter<ListFrag_6_serviceListAdapter.ViewHolder> {
    ArrayList<ServiceItem> items = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }

    }


    @NonNull
    @Override
    public ListFrag_6_serviceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recyclerview_frag6, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFrag_6_serviceListAdapter.ViewHolder holder, int position) {
        ServiceItem item = items.get(position);
        holder.tv_title.setText(item.서비스명);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<ServiceItem> inputList){
        items = inputList;
        notifyDataSetChanged();
    }
}
