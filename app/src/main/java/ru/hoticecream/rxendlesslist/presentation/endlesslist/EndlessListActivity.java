package ru.hoticecream.rxendlesslist.presentation.endlesslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.hoticecream.rxendlesslist.AppComponent;
import ru.hoticecream.rxendlesslist.R;
import ru.hoticecream.rxendlesslist.data.DataItem;
import ru.hoticecream.rxendlesslist.presentation.utils.EndlessRecyclerOnScrollListener;

public class EndlessListActivity extends AppCompatActivity implements EndlessListView {

    @Inject
    EndlessListPresenter presenter;

    @Bind(R.id.recycler)
    RecyclerView recycler;
    private EndlessListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endless_list);
        AppComponent.Holder.get().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapter = new EndlessListAdapter();
        recycler.setAdapter(adapter);
        recycler.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                presenter.loadMore(adapter.getLastItemId());
            }
        });
        presenter.loadMore(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void renderItems(List<DataItem> dataItems) {
        adapter.addAll(dataItems);
    }

    public static class EndlessListAdapter extends RecyclerView.Adapter<DataItemHolder> {

        private SortedList.Callback<DataItem> callback = new SortedListAdapterCallback<DataItem>(this) {
            @Override
            public int compare(DataItem o1, DataItem o2) {
                return o1.getId() - o2.getId();
            }

            @Override
            public boolean areContentsTheSame(DataItem oldItem, DataItem newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(DataItem item1, DataItem item2) {
                return item1.getId() == item2.getId();
            }
        };
        private SortedList<DataItem> items = new SortedList<>(DataItem.class, callback);

        @Override
        public DataItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DataItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
        }

        @Override
        public void onBindViewHolder(DataItemHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void addAll(List<DataItem> data) {
            items.beginBatchedUpdates();
            items.addAll(data);
            items.endBatchedUpdates();
        }

        public int getLastItemId() {
            return items.size() == 0 ? 0 : items.get(items.size() - 1).getId();
        }
    }

    public static class DataItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_message)
        TextView textMessage;

        public DataItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DataItem item) {
            textMessage.setText(item.getMessage());
        }
    }
}
