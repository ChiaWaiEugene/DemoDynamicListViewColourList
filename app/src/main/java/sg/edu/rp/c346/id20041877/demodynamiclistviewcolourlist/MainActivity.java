package sg.edu.rp.c346.id20041877.demodynamiclistviewcolourlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement, etIndexElement;
    Button btnAdd, btnRmv, btnUp;
    ListView lvColour;
    ArrayList<String> alColours;
    ArrayAdapter<String> aaColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextColour);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRmv = findViewById(R.id.buttonRemoveItem);
        btnUp = findViewById(R.id.buttonUpdateItem);
        lvColour = findViewById(R.id.listViewColour);
        etIndexElement = findViewById(R.id.editTextPosition);

        alColours = new ArrayList<>();
        // add
        alColours.add("Red");
        alColours.add("Orange");

        aaColour = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alColours);

        lvColour.setAdapter(aaColour);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colour = etElement.getText().toString();
                if (etIndexElement.getText().toString().isEmpty() == false) {
                    int pos = Integer.parseInt(etIndexElement.getText().toString());
                    alColours.add(pos, colour);
                }
                else {
                    alColours.add(colour);
                }
                aaColour.notifyDataSetChanged();
            }
        });

        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = alColours.get(position);
                Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
            }
        });

        btnRmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etIndexElement.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Index is Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Integer.parseInt(etIndexElement.getText().toString()) > alColours.size() - 1) {
                        Toast.makeText(MainActivity.this, "The index is too large!", Toast.LENGTH_SHORT).show();
                    } else {
                        alColours.remove(Integer.parseInt(etIndexElement.getText().toString()));
                        aaColour.notifyDataSetChanged();
                    }
                }
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etIndexElement.getText().toString().equalsIgnoreCase("") || etElement.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Colour and Index is Required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Integer.parseInt(etIndexElement.getText().toString()) > alColours.size() - 1) {
                        Toast.makeText(MainActivity.this, "The index is too large!", Toast.LENGTH_SHORT).show();
                    } else {
                        alColours.set(Integer.parseInt(etIndexElement.getText().toString()), etElement.getText().toString());
                        aaColour.notifyDataSetChanged();
                    }
                }
            }
        });


    }
}