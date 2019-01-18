package by.alexlevankou.testapp.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import by.alexlevankou.testapp.R;
import by.alexlevankou.testapp.main.adapter.RecyclerViewAdapter;
import by.alexlevankou.testapp.model.DataEntity;
import by.alexlevankou.testapp.presenter.BaseContract;

public class MainActivity extends AppCompatActivity implements BaseContract.View {

    private RecyclerView mRecyclerView;
    private TextView mNoDataText;
    private ProgressBar mProgressBar;
    private MenuItem searchItem;
    private FloatingActionButton fab;

    private RecyclerViewAdapter mAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNoDataText = findViewById(R.id.no_data_text);
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.list);
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = ViewModelProviders.of(this).get(MainPresenter.class);
        mPresenter.attachView(this, getLifecycle());
        mPresenter.getAllEntities();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> mPresenter.addEntity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        searchItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onSearchEnd();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                mPresenter.search(s);
                return false;
            }
        });


        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                mPresenter.onSearchStart();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                mPresenter.onSearchEnd();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateList(List<DataEntity> entities){
        mRecyclerView.setVisibility(View.VISIBLE);
        mNoDataText.setVisibility(View.GONE);
        mAdapter.setItems(entities);
    }

    @Override
    public void showLoading(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataText(){
        mRecyclerView.setVisibility(View.GONE);
        mNoDataText.setVisibility(View.VISIBLE);
    }

    @Override
    public void startSearch(){
        fab.hide();
    }

    @Override
    public void endSearch(){
        fab.show();
    }

    @Override
    public void fabClicked(){
        Snackbar.make(getWindow().getDecorView(), R.string.entity_added, Snackbar.LENGTH_SHORT).show();
    }
}
