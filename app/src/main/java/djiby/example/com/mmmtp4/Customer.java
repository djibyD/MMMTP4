package djiby.example.com.mmmtp4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by djiby on 18/01/17.
 */

public class Customer implements Parcelable{

    private String name;
    private String firstName;
    private String dayOfBirth;
    private String city;

    public Customer(){

    }

    public Customer(String n, String f, String d, String c){
        name = n;
        firstName = f;
        dayOfBirth = d;
        city = c;

    }

    private Customer(Parcel in) {
        name = in.readString();
        firstName = in.readString();
        dayOfBirth = in.readString();
        city = in.readString();


    }


    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void mapping(HashMap<String, String> hashMap, ArrayList<HashMap<String, String>> arrayList){
        hashMap = new HashMap<String, String>();
        hashMap.put("Name", this.getName());
        hashMap.put("First Name", this.getFirstName());
        hashMap.put("Date", this.getDayOfBirth());
        hashMap.put("City", this.getCity());
        arrayList.add(hashMap);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(firstName);
        dest.writeString(dayOfBirth);
        dest.writeString(city);
    }

    public static final Creator<Customer> CREATOR
            = new Creator<Customer>() {
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

}
