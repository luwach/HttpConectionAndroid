package pl.sdacademy.network;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.sdacademy.network.dto.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<Person> data = new ArrayList<>();

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = data.get(position);
        holder.fullName.setText(person.getFirstName() + " " + person.getLastName());
        holder.birthDate.setText(person.getBirthDay().toString());
        holder.phoneNumber.setText(person.getPhoneNumber());
        holder.email.setText(person.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Person> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView birthDate;
        private TextView phoneNumber;
        private TextView email;

        public ViewHolder(View v) {
            super(v);
            fullName = (TextView) v.findViewById(R.id.full_name);
            birthDate = (TextView) v.findViewById(R.id.birth_date);
            phoneNumber = (TextView) v.findViewById(R.id.phone_number);
            email = (TextView) v.findViewById(R.id.email);
        }
    }
}
