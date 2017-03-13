package djiby.example.com.mmmtp4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by djiby on 07/03/17.
 */

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.CustomerViewHolder> {
    private List<Customer> customers;
    private Context mContext;

    //Handling the click on the recycleView items
    private MyOnItemClickListener myOnItemClickListener;

    public CustomerRecyclerViewAdapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.mContext = context;

    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        CustomerViewHolder customerViewHolder = new CustomerViewHolder(view);
        return customerViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder customerViewHolder, int i) {
        Customer customer = customers.get(i);
        customerViewHolder.nom.setText(customer.getName());
        customerViewHolder.prenom.setText(customer.getFirstName());
        customerViewHolder.date.setText(customer.getDayOfBirth());
        customerViewHolder.ville.setText(customer.getCity());

    }

    @Override
    public int getItemCount() {
        return (null != customers ? customers.size() : 0);
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView nom;
        protected TextView prenom;
        protected TextView date;
        protected TextView ville;

        public CustomerViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.nom = (TextView) view.findViewById(R.id.viewName);
            this.prenom = (TextView) view.findViewById(R.id.viewFirstName);
            this.date = (TextView) view.findViewById(R.id.viewDate);
            this.ville = (TextView) view.findViewById(R.id.viewCity);

        }

        @Override
        public void onClick(View v) {
            if(myOnItemClickListener!=null){
                myOnItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void updateList(List<Customer> list) {
        customers = list;
        notifyDataSetChanged();
    }

    //Handling the click on the recycleView items
    public interface MyOnItemClickListener {
        void onItemClick(View view, int position);
    }

    //Setting the onclickListener
    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

}
