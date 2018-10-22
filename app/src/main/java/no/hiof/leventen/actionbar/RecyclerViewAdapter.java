package no.hiof.leventen.actionbar;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder> {

    List<Person> persons;
    private RecyclerViewClickListener clickListener;


    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        List<Person> persons;
        public Person person;

        PersonViewHolder(View itemView, final RecyclerViewClickListener clickListener) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cv);
            personName = itemView.findViewById(R.id.person_name);
            personAge = itemView.findViewById(R.id.person_age);
            personPhoto = itemView.findViewById(R.id.person_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Istedenfor å bytte personens navn til Hei vil vi heller bli tatt med videre til personens profil. Dette blir implementert senere.
                    clickListener.onClick(view, getAdapterPosition());
                }
            });
        }

    }

    public RecyclerViewAdapter(List<Person> persons, RecyclerViewClickListener clickListener) {
        this.persons = persons;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_layout, viewGroup,false);
        PersonViewHolder personViewHolder = new PersonViewHolder(v, clickListener);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personAge.setText(persons.get(i).age+" år");
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        personViewHolder.person = persons.get(i);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }
}