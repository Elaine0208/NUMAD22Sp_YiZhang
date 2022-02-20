package edu.neu.madcourse.numad22sp_yizhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class ActivityLinkController extends AppCompatActivity implements EnterUrlPopup.EnterUrlListener {
    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    public void applyTexts(String name, String url) {
        if (isValidUrl(url)) {
            addItem(0, name, url);
        } else {
            Snackbar.make(recyclerView, "Invalid Url. Please re-enter.",
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private boolean isValidUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_controller);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnterUrl();
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(recyclerView, "Removed a link",
                        Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void openEnterUrl() {
        EnterUrlPopup enterUrlPopup = new EnterUrlPopup();
        enterUrlPopup.show(getSupportFragmentManager(), "enter url");
    }


    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getItemDesc());
        }
        super.onSaveInstanceState(outState);

    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String itemDesc = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");

                    ItemCard itemCard = new ItemCard(itemName, itemDesc);

                    itemList.add(itemCard);
                }
            }
        } else {
            // Displays empty list the first time to open this Activity
        }
    }

    private void createRecyclerView() {


        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }

    private void addItem(int position, String name, String url) {
        itemList.add(position, new ItemCard(name, url));
        Snackbar.make(recyclerView, "Url: " + url + " registered",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        rviewAdapter.notifyItemInserted(position);
    }
}