package by.alexlevankou.testapp.mainscreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.alexlevankou.testapp.R;
import by.alexlevankou.testapp.database.DataEntity;

class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView idText;
    private final TextView userIdText;
    private final TextView nameText;
    private final TextView bodyText;
    private final TextView numberText;

    ViewHolder(View view) {
        super(view);
        idText = view.findViewById(R.id.idText);
        userIdText = view.findViewById(R.id.userIdText);
        nameText = view.findViewById(R.id.nameText);
        bodyText = view.findViewById(R.id.bodyText);
        numberText = view.findViewById(R.id.numberText);
    }

    void bindItem(DataEntity entity){
        idText.setText(String.valueOf(entity.getId()));
        userIdText.setText(String.valueOf(entity.getUserId()));
        nameText.setText(entity.getName());
        bodyText.setText(entity.getBody());
        numberText.setText(String.valueOf(entity.getNumber()));
    }
}

