package djiby.example.com.mmmtp4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by djiby on 18/01/17.
 */

public class ShowCustomerInFoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle info = getIntent().getExtras();
        if(info==null){
            return;
        }
        TextView textView = (TextView) findViewById(R.id.viewInfo);
        textView.setText(info.getString("information"));
    }
}
