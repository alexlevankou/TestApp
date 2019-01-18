package by.alexlevankou.testapp.mainscreen.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import by.alexlevankou.testapp.R;
import by.alexlevankou.testapp.database.DataEntity;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<DataEntity> values;
    private Comparator<DataEntity> comparator;

    public RecyclerViewAdapter() {
        values = new ArrayList<>();
        comparator = new Comparator<DataEntity>() {
            @Override
            public int compare(DataEntity lhs, DataEntity rhs) {
                int cmp = compareUserId(lhs.getUserId(), rhs.getUserId());
                if( cmp == 0) {
                    return lhs.getName().compareTo(rhs.getName());
                }
                return cmp;
            }

            private int compareUserId(int lhs, int rhs) {
                return Integer.signum(lhs - rhs);
            }
        };
    }

    public void setItems(List<DataEntity> items) {
        values = items;
        values.sort(comparator);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        DataEntity item = values.get(position);
        ((ViewHolder)holder).bindItem(item);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
