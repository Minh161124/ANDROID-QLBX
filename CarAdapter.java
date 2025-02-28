package com.example.appmobie;

import androidx.recyclerview.widget.RecyclerView;

public class CarAdapter  {

        private int id;
        private String bienso;
        private String tenxe;

        // Constructor
        public CarAdapter(int id, String bienso, String tenxe) {
            this.id = id;
            this.bienso = bienso;
            this.tenxe = tenxe;
        }

        // Getter cho các thuộc tính
        public int getId() {
            return id;
        }

        public String getBienso() {
            return bienso;
        }

        public String getTenxe() {
            return tenxe;
        }
    


}
