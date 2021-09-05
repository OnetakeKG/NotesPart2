package net.nov.notespart2.ui.gallery;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.nov.notespart2.NotesDetails;
import net.nov.notespart2.R;
import net.nov.notespart2.databinding.FragmentGalleryBinding;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GalleryFragment extends Fragment {
    public final static String NOTES = "My notes";
    EditText name, details;
    TextView date;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_notes,container,false);
        FloatingActionButton fab = root.findViewById(R.id.btn_add);
        name = root.findViewById(R.id.noteName);
        details = root.findViewById(R.id.details);
        date = root.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog= new DatePickerDialog(getActivity(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.show();
            }


        });


        fab.setOnClickListener(v -> {
            NotesDetails notesDetails = new NotesDetails(name.getText().toString(), details.getText().toString(), date.getText().toString());
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(NOTES, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            ArrayList<NotesDetails> notesDetails1 = gson.fromJson(sharedPreferences.getString(NOTES, ""),new TypeToken<ArrayList<NotesDetails>>(){}.getType());
            notesDetails1.add(notesDetails);
            String json = gson.toJson(notesDetails1);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NOTES,json);
            editor.apply();
            Navigation.findNavController(v).navigate(R.id.action_nav_gallery_to_nav_home);
        });


        return root;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String dateYouChoosed = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            date.setText(dateYouChoosed );
        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}