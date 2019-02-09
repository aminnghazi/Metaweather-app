package ir.jdeiut.jdeiut;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import ir.jdeiut.jdeiut.adapters.SearchResultAdapter;
import ir.jdeiut.jdeiut.model.caller.OnSearchResultReceived;
import ir.jdeiut.jdeiut.model.caller.SearchCaller;
import ir.jdeiut.jdeiut.model.entities.SearchEntity;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, OnSearchResultReceived {
    EditText etSearch;
    Button btnSearch;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        listView = findViewById(R.id.listView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                String txt = etSearch.getText().toString();
                if (txt.trim().isEmpty()) {
                    etSearch.setError("Enter something");
                    return;
                }
                SearchCaller caller = new SearchCaller();
                caller.setOnSearchResultReceived(this);
                caller.execute(txt);

                break;
        }
    }


    @Override
    public void onSearchResultReceived(SearchEntity[] entities) {
        List<SearchEntity> list = Arrays.asList(entities);
        SearchResultAdapter adapter = new SearchResultAdapter(list, this);
        listView.setAdapter(adapter);
    }
}
