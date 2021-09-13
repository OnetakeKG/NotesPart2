package net.nov.notespart2.ui.home;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.nov.notespart2.NotesAdapter;
import net.nov.notespart2.NotesDetails;
import net.nov.notespart2.R;
import net.nov.notespart2.databinding.FragmentHomeBinding;
import net.nov.notespart2.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    SharedPreferences sharedPreferences;
    public final static String NOTES = "My notes";


    private Activity activity;
    public View view;
    private Context context;
    ArrayList<NotesDetails> notesDetailsArrayList;
    NotesAdapter adapter = null;
    RecyclerView recyclerView;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        activity = requireActivity();
        context = requireActivity().getApplicationContext();
        notesDetailsArrayList = new ArrayList<>();
        sharedPreferences = activity.getSharedPreferences(NOTES, Context.MODE_PRIVATE);

        final Gson gson = new Gson();


        notesDetailsArrayList = gson.fromJson(sharedPreferences.getString(NOTES, ""),new TypeToken<ArrayList<NotesDetails>>(){}.getType());
        adapter = new NotesAdapter(context,notesDetailsArrayList,HomeFragment.this);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(activity,1,GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return root;
    }

    public void goToFragment (){

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_fragment,new GalleryFragment(),"111")
                .addToBackStack("one")
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_update:
                // Do some stuff
                return true;
            case R.id.action_delete:
                // Do some stuff
                return true;
        }
        return super.onContextItemSelected(item);
    }




}