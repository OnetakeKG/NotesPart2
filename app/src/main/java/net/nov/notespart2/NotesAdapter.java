package net.nov.notespart2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import net.nov.notespart2.ui.home.HomeFragment;
import net.nov.notespart2.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private Context context;
    private HomeFragment homeFragment;
    private ArrayList<NotesDetails> notesList;
    private ViewGroup parent;
    private int viewType;


    public NotesAdapter(Context context, ArrayList<NotesDetails> notesList, HomeFragment homeFragment) {

        this.context = context;
        this.notesList = notesList;
        this.homeFragment = homeFragment;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        this.viewType = viewType;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_name, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position){

    final NotesDetails details = notesList.get(position);
    String headerName = details.getNoteName();
    holder.noteHeader.setText(headerName);
    holder.container.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//             homeFragment.goToFragment();
//            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_slideshow);
            SlideshowFragment slideshowFragment = new SlideshowFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Name", details.getNoteName());
            bundle.putString("Details", details.getDetails());
            bundle.putString("Date", details.getDate());
            slideshowFragment.setArguments(bundle);
            homeFragment.requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_fragment, slideshowFragment,null)
                    .addToBackStack(null)
                    .commit();
        }
    });

}
    public void setDataSource(CardsSource dataSource){
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != notesList ? notesList.size() :0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private RelativeLayout container;
        private TextView noteHeader;




        public ViewHolder(View view, int viewType) {
            super(view);

            container=(RelativeLayout) view.findViewById(R.id.parent1);
            noteHeader = (TextView) view.findViewById(R.id.headerTextView);


        }


    }}
