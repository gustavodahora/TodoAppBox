package dev.gustavodahora.todoappbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dark theme function
        SwitchCompat switchTheme = findViewById(R.id.switch_theme);
        switchTheme.setOnClickListener(v -> {
            theme(switchTheme.isChecked());
        });

        rvMain = findViewById(R.id.main_rv);
        List<MainItem> mainItems = new ArrayList<>();

        mainItems.add(new MainItem(1, "Acordar mais cedo", "Lorem Ipsum is " +
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                "been the industry's standard dummy text ever since the 1500s, when an unknown " +
                "printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic" +
                " typesetting, remaining essentially un"));

        mainItems.add(new MainItem(2, "Contas a pagar", "Lorem Ipsum is " +
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                "been the industry's standard dummy text ever since the 1500s, when an unknown " +
                "printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic" +
                " typesetting, remaining essentially un"));

        mainItems.add(new MainItem(3, "Lista de compras", "Lorem Ipsum is " +
                "simply dummy text of the printing and typesetting industry. Lorem Ipsum has " +
                "been the industry's standard dummy text ever since the 1500s, when an unknown " +
                "printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic" +
                " typesetting, remaining essentially un"));

        // Creation of recycle view manager.
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        // Setup the adapter.
        MainAdapter adapter = new MainAdapter(mainItems);
        rvMain.setAdapter(adapter);

        // New item
        View newItem = findViewById(R.id.action_new_item);
        newItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewItem.class);
            startActivity(intent);
        });

    }

    // Theme function
    public void theme(boolean checked) {
        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Toast.makeText(this, "CHAMA 1", Toast.LENGTH_SHORT).show();
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(this, "CHAMA 2", Toast.LENGTH_SHORT).show();
        }
    }

    // Main adapter
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
        private List<MainItem> mainItems;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.todo_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.MainAdapter.MainViewHolder holder,
                                     int position) {
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent, position);
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }

        // Entenda como sendo a View da celula que está dentro do recycler
        private class MainViewHolder extends RecyclerView.ViewHolder {

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(MainItem item, int position) {
                TextView titleId = itemView.findViewById(R.id.title_item);
                titleId.setText(item.getTitleId());

                ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
                btnDelete.setOnClickListener(v -> {
                    mainItems.remove(position);
                    notifyDataSetChanged();
                });

                ImageButton btnEdit = itemView.findViewById(R.id.btn_edit);
                btnEdit.setOnClickListener(v -> {
                    startActivity( new Intent(MainActivity.this, EditItem.class));
                });

            }
        }
    }

}