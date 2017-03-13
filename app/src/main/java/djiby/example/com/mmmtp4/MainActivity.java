package djiby.example.com.mmmtp4;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    EditText date;
    EditText ville;

    private DatabaseReference databaseReference;

    //new field added for the phone number
    EditText phoneNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("customer");


        nom = (EditText) findViewById(R.id.editName);
        prenom = (EditText) findViewById(R.id.editFirstName);
        date = (EditText) findViewById(R.id.editDate);
        ville = (EditText) findViewById(R.id.editCity);
    }

    public void valider(View view) {

        String key =  databaseReference.push().getKey(); //generating a key
        databaseReference.child(key).setValue(new Customer(nom.getText().toString(), prenom.getText().toString(),
                date.getText().toString(), ville.getText().toString()));
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.itemReset:
                resetData();
                return true;
            case R.id.itemNew:
                newField();
                return true;
            case R.id.itemBrowseCity:
                browseCity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void browseCity() {
        EditText editText = (EditText) findViewById(R.id.editCity);
        String path = "http://fr.wikipedia.org" + "/wiki/" + editText.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        startActivity(intent);
    }

    private void newField() {
        phoneNumer = new EditText(getApplicationContext());
        phoneNumer.setHint("Phone number");
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoneTextS);
        linearLayout.addView(phoneNumer);

    }

    private void resetData() {
        EditText nom = (EditText) findViewById(R.id.editName);
        nom.setText("");
        EditText prenom = (EditText) findViewById(R.id.editFirstName);
        prenom.setText("");
        EditText date = (EditText) findViewById(R.id.editDate);
        date.setText("");
        EditText ville = (EditText) findViewById(R.id.editCity);
        ville.setText("");

        //remove the new field is case it's been added
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoneTextS);
        linearLayout.removeView(phoneNumer);
    }
}
