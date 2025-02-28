package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class View_Sevice extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> SevList;
    private ArrayAdapter<String> adapter;
    private Spinner spin;
    private ImageView Exit;
    private SearchView searchView;
    private ArrayList<String> filteredList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_sevice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Exit = findViewById(R.id.btnback);
        listView = findViewById(R.id.list);
        SevList = new ArrayList<>();
        spin = findViewById(R.id.spinner_view_sevice);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SevList);
        listView.setAdapter(adapter);
        searchView = findViewById(R.id.seachSev);
        fetchSev();

        String[] options = {"Chọn chức năng:","Add Service", "View Service"};

        // Tạo Adapter cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        // Xử lý sự kiện chọn Spinner
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(View_Sevice.this, Add_Sevice.class);
                        startActivity(intentB);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedItem = SevList.get(position);
            Intent intent = new Intent(View_Sevice.this, Add_Sevice.class);
            intent.putExtra("Data", selectedItem);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(View_Sevice.this, SelectAd.class);
                startActivity(intentB);
            }
        });
    }
    private void fetchSev() {
        String url = "http://192.168.119.175/qlbx/phpList/SevList.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        SevList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject view = response.getJSONObject(i);
                            String display = "Mã dịch vụ: " + view.getString("madv") +
                                    "\nTên dịch vụ: " + view.getString("tendv") +
                                    "\nGiá vé: " + view.getString("giave");

                            SevList.add(display);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(View_Sevice.this, "Lỗi JSON!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(View_Sevice.this, "Lỗi kết nối server!", Toast.LENGTH_SHORT).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void filterList(String query) {

        filteredList.clear();
        for (String item : SevList) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList.isEmpty() ? SevList : filteredList);
        listView.setAdapter(adapter);
    }
}