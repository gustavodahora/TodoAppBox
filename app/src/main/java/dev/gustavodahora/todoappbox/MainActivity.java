package dev.gustavodahora.todoappbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


        rvMain.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter adapter = new MainAdapter(mainItems);

//        adapter.setListener(id -> {
//            switch(id) {
//                case 1:
//                    startActivity( new Intent(MainActivity.this, EditTodoItem.class));
//                    break;
//                case 2:
//                    startActivity( new Intent(MainActivity.this, EditTodoItem.class));
//                    break;
//            }
//
//        });

        rvMain.setAdapter(adapter);

        View newItem = findViewById(R.id.action_new_item);
        newItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewItem.class);
            startActivity(intent);
        });

    }

    // faz a conecção entre a recycle view e a view holder
    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
        private List<MainItem> mainItems;
//        private OnItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

//        public void setListener(OnItemClickListener listener) {
//            this.listener = listener;
//        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.todo_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.MainAdapter.MainViewHolder holder, int position) {
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
//                ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);

                ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);
                ImageButton btnEdit = itemView.findViewById(R.id.btn_edit);
//
                titleId.setText(item.getTitleId());

                btnDelete.setOnClickListener(v -> {
                    mainItems.remove(position);
                    notifyDataSetChanged();
                });

                btnEdit.setOnClickListener(v -> {
                    startActivity( new Intent(MainActivity.this, EditItem.class));
                });

            }
        }
    }

}