package com.th2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.th2.db.Database;
import com.th2.model.Book;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText eName, eAuthor, ePublishDate, ePrice;
    private Spinner spPublisher;
    Button btUpdate, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();

        //Hien thi dialog chon ngay phat hanh
        ePublishDate.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    m++;
                    String date = d + "/" + m + "/" + y;
                    ePublishDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        });

        //btUpdate de them sach
        btUpdate.setOnClickListener(view -> {
            String name = eName.getText().toString();
            String author = eAuthor.getText().toString();
            String publishDate = ePublishDate.getText().toString();
            String publisher = spPublisher.getSelectedItem().toString();
            String price = ePrice.getText().toString();

            Book newBook = new Book(name, author, publishDate, publisher, price);
            Database db = new Database(AddActivity.this);
            db.addBook(newBook);

            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btCancel.setOnClickListener(view -> {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        eName = findViewById(R.id.eName);
        eAuthor = findViewById(R.id.eAuthor);
        ePublishDate = findViewById(R.id.ePublishDate);
        ePrice = findViewById(R.id.ePrice);
        spPublisher = findViewById(R.id.spPublisher);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);

        spPublisher.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.publisher)));
    }
}