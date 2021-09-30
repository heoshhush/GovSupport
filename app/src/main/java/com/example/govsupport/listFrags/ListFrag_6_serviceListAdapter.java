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

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if( pos != RecyclerView.NO_POSITION){
                        mListener.onItemClick(v, pos);
                    }

                }
            });

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
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
        holder.tv_date.setText(item.신청기한);
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
