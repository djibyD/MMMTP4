package djiby.example.com.mmmtp4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djiby on 18/01/17.
 */

public class ListViewActivity extends AppCompatActivity implements CustomerRecyclerViewAdapter.MyOnItemClickListener{

    //FireBase
    private DatabaseReference databaseReference;

    //Filter recyclerView Items by name
    private EditText customerName;

    //RecylcerView
    private RecyclerView recyclerView;
    private CustomerRecyclerViewAdapter customerRecyclerViewAdapter;
    private List<Customer> customers;

    //Get Customer info when clicked on listview item
    TextView name;
    TextView firstname;
    TextView date;
    TextView city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listview);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //retieving data from database
        customers = new ArrayList<Customer>();
        databaseReference = FirebaseDatabase.getInstance().getReference("customer");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Customer customer = dataSnapshot.getValue(Customer.class);
                customers.add(customer);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        customerRecyclerViewAdapter = new CustomerRecyclerViewAdapter(this, customers);
        customerRecyclerViewAdapter.setMyOnItemClickListener(this);
        recyclerView.setAdapter(customerRecyclerViewAdapter);

        //Filter recylerView Items by name
        customerName = (EditText) findViewById(R.id.editFilterCustomer);
        customerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Customer> temp = new ArrayList();
                for(Customer c: customers){
                    if(c.getName().toLowerCase().contains(customerName.getText().toString().toLowerCase())){
                        temp.add(c);
                    }
                }
                //update recyclerview
                customerRecyclerViewAdapter.updateList(temp);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void createNewCustomer(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(View view, int position) {
        name = (TextView) view.findViewById(R.id.viewName);
        firstname = (TextView) view.findViewById(R.id.viewFirstName);
        date = (TextView) view.findViewById(R.id.viewDate);
        city = (TextView) view.findViewById(R.id.viewCity);

        String customerInfo = name.getText().toString() +
                "\n" + firstname.getText().toString() +
                "\n" + date.getText().toString() +
                "\n" + city.getText().toString();

        Intent intent = new Intent(getApplicationContext(), ShowCustomerInFoActivity.class);
        intent.putExtra("information", customerInfo);
        startActivity(intent);
    }
}
