package com.example.database_studentrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHandler handler;
    EditText editText, editText1, editText2, editText3;
    Button button, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DBHandler(this);

        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }

    public void addBtn(View v){
        boolean istrue = handler.addRecords(editText.getText().toString(), editText1.getText().toString(), editText2.getText().toString());
        if(istrue = true)
        Toast.makeText(this, "Successfully Created", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }

    public void viewAll(View v){
        Cursor cursor = handler.getAllDAta();
        if (cursor.getCount() == 0) {
            showMessage("Error Happened", "Something went wrong");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("ID: " + cursor.getString(0) + "\n");
            buffer.append("Name: " + cursor.getString(1) + "\n");
            buffer.append("Surname: " + cursor.getString(2) + "\n");
            buffer.append("Marks: " + cursor.getString(3) + "\n\n");
        }
        showMessage("Data is: ", buffer.toString());

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void addUpdate(View v){
        boolean isUpdate = handler.updateData(editText3.getText().toString(), editText.getText().toString(), editText1.getText().toString(), editText2.getText().toString());
        if (isUpdate == true)
            Toast.makeText(this, "UPdated Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "An Error has occured", Toast.LENGTH_SHORT).show();
    }

    public void deleteID(View v){
        handler.deleteData(editText3.getText().toString());
    }
}