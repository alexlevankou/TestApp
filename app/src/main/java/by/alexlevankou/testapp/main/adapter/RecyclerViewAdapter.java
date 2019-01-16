package by.alexlevankou.testapp.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<DataEntity> values;

    public RecyclerViewAdapter() {
        values = new ArrayList<>();
    }

    public void addItem(DataEntity item) {
        values.add(item);
        notifyDataSetChanged();
    }

    public void setItems(List<DataEntity> items) {
        values = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = values.get(position);
        holder.idText.setText(Integer.toString(holder.item.getId()));
        holder.userIdText.setText(Integer.toString(holder.item.getUserId()));
        holder.nameText.setText(holder.item.getName());
        holder.bodyText.setText(holder.item.getBody());
        holder.numberText.setText(Double.toString(holder.item.getUserId()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView idText;
        final TextView userIdText;
        final TextView nameText;
        final TextView bodyText;
        final TextView numberText;
        DataEntity item;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            idText = view.findViewById(R.id.idText);
            userIdText = view.findViewById(R.id.userIdText);
            nameText = view.findViewById(R.id.nameText);
            bodyText = view.findViewById(R.id.bodyText);
            numberText = view.findViewById(R.id.numberText);
        }
    }
}