package ru.exemple.uksorganizer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ru.exemple.uksorganizer.App;
import ru.exemple.uksorganizer.R;
import ru.exemple.uksorganizer.db.EventsDatabase;
import ru.exemple.uksorganizer.db.EventsDatabaseFile;
import ru.exemple.uksorganizer.model.Event;

//TODO: сделать чтобы можно было выбирать setLayoutManager recycler из UI
//TODO: сделть чтобы если нет events - отображалась вьюшка с текстом "Еще нет евентиов, добавьте"
//TODO: сделать загрузку данных асинхронно (в другом потоке), пока грузится выводить прогресс
 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private RecyclerView recycler;
    ArrayList<Event> events;
    DividerItemDecoration dividerItemDecoration;
    EventsDatabaseFile eventsDatabaseFile;
    private ListView listViewEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*EventsDatabase eventsDb = ((App) getApplication()).getEventsDb();*/
        /*EventsDatabase eventsDatabaseFile = ((App) getApplication()).getEventsDb();*/
        eventsDatabaseFile = new EventsDatabaseFile();

        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        listViewEvents = findViewById(R.id.listViewEvents);

        /*recycler = findViewById(R.id.rvEvents);
        LinearLayoutManager llManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(llManager);*/
        /*events = (ArrayList<Event>) eventsDb.getAllEvents();*/
        events = (ArrayList<Event>) eventsDatabaseFile.getAllEvents();
        ArrayAdapter<ArrayList<Event>> eventsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);
        listViewEvents.setAdapter(eventsAdapter);
        /*recycler.setAdapter(eventsAdapter);
        dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),
                llManager.getOrientation());
        recycler.addItemDecoration(dividerItemDecoration);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*checkEmptyList();*/
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_event_item:
                return true;
            case R.id.recycle_view_orientation_vertical_item:
                recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                dividerItemDecoration.setOrientation(RecyclerView.VERTICAL);
                return true;
            case R.id.recycle_view_orientation_horizontal_item:
                recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                dividerItemDecoration.setOrientation(RecyclerView.HORIZONTAL);
                return true;
            case R.id.recycle_view_orientation_grid_item:
                recycler.setLayoutManager(new GridLayoutManager(this, 2));
                return true;
            case R.id.settings_item:
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    /*public void checkEmptyList() {
        if (events.size() == 0)
            findViewById(R.id.tvEmpty).setVisibility(View.VISIBLE);
    }*/
}
