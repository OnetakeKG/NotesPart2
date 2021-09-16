package net.nov.notespart2.ui.home;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.nov.notespart2.CardData;
import net.nov.notespart2.CardsSource;
import net.nov.notespart2.CardsSourceFirebaseImpl;
import net.nov.notespart2.CardsSourceResponse;
import net.nov.notespart2.NotesAdapter;
import net.nov.notespart2.NotesDetails;
import net.nov.notespart2.R;
import net.nov.notespart2.databinding.FragmentHomeBinding;
import net.nov.notespart2.ui.gallery.GalleryFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    SharedPreferences sharedPreferences;
    public final static String NOTES = "My notes";

    private CardsSource data;
    private Activity activity;
    public View view;
    private Context context;
    ArrayList<NotesDetails> notesDetailsArrayList;
    NotesAdapter adapter = null;
    RecyclerView recyclerView;
    private FragmentHomeBinding binding;
    private boolean moveToFirstPosition;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        data = new CardsSourceFirebaseImpl().init(new CardsSourceResponse() {
            @Override
            public void initialized(CardsSource cardsData) {
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setDataSource(data);
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

    private CardData collectCardData(){
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        Date date = getDateFromDatePicker();
        if (cardData != null){
            CardData answer;
            answer = new CardData(title, description, cardData.getPicture(), cardData.isLike(), date);
            answer.setId(cardData.getId());
            return answer;
        } else {int picture = PictureIndexConverter.getPictureByIndex(PictureIndexConverter.randomPictureIndex());
            return new CardData(title, description, picture, false, date);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}