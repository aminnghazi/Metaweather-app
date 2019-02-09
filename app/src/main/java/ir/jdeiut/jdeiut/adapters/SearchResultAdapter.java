package ir.jdeiut.jdeiut.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.jdeiut.jdeiut.LocationForecastActivity;
import ir.jdeiut.jdeiut.R;
import ir.jdeiut.jdeiut.model.caller.LocationForecastCaller;
import ir.jdeiut.jdeiut.model.entities.SearchEntity;

public class SearchResultAdapter extends BaseAdapter {

    ArrayList<SearchEntity> data = new ArrayList<>();
    Context cntx;

    public SearchResultAdapter(List<SearchEntity> data, Context cntx) {
        this.data.addAll(data);
        this.cntx = cntx;
    }

    private SearchResultAdapter() {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getWoeid();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(cntx);
        final View root;
        if (convertView != null)
            root = convertView;
        else
            root = inflater.inflate(R.layout.item_search, null, false);
        TextView txtView = root.findViewById(R.id.textView);
        final SearchEntity entity = (SearchEntity) getItem(position);
        txtView.setText(entity.getTitle());
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cntx, "name"+entity.getTitle() + "id" + entity.getWoeid(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(cntx , LocationForecastActivity.class);
                intent.putExtra("woeid" , entity.getWoeid());
                cntx.startActivity(intent);
            }
        });

        return root;
    }
}
