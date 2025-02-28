package com.example.appmobie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
public class View_Car extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> carList;
    private ArrayAdapter<String> adapter, Adapter;
    private Spinner spin;
    private ImageView Exit;
    private Button btnDelete, btnEdit;
    private CarManager car;
    private SearchView searchView;
    private ArrayList<String> filteredList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_car);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        searchView = findViewById(R.id.SeachCar);
        Exit = findViewById(R.id.btnback);
        listView = findViewById(R.id.list);
        carList = new ArrayList<>();
        spin = findViewById(R.id.spinner_view_car);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, carList);
        btnDelete = findViewById(R.id.btnDelete);
        listView.setAdapter(adapter);
        car = new CarManager();

        fetchCars();


        String[] options = {"Chọn Chức năng:", "Add Car", "View Car"};

        // Tạo Adapter cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent intentB = new Intent(View_Car.this, Add_Car.class);
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


        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentB = new Intent(View_Car.this, SelectAd.class);
                startActivity(intentB);
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedItem = carList.get(position);
            Intent intent = new Intent(View_Car.this, Add_Car.class);
            intent.putExtra("carData", selectedItem);
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
    }


    private void fetchCars() {
        String url = "http://192.168.119.175/qlbx/phpList/CarList.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        carList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject car = response.getJSONObject(i);

                            String display = "Biển số: " + car.getString("bienso") +
                                    "\nNhà xe: " + car.getString("tenxe") +
                                    "\nTài xế: " + car.getString("tentaixe") +
                                    "\nSố điện thoại: " + car.getString("sdt") +
                                    "\nPhí: " + car.getString("phi")+
                                    "\nTuyến: " + car.getString("tuyen") ;
                            carList.add(display);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(View_Car.this, "Lỗi JSON!", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(View_Car.this, "Lỗi kết nối server!", Toast.LENGTH_SHORT).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void filterList(String query) {

        filteredList.clear();
        for (String item : carList) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredList.isEmpty() ? carList : filteredList);
        listView.setAdapter(adapter);
    }
}