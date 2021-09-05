package net.nov.notespart2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.nov.notespart2.R;
import net.nov.notespart2.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {


    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();

        View root = binding.getRoot();
        TextView slideTV1 = root.findViewById(R.id.slideTV1);
        TextView slideTV2 = root.findViewById(R.id.slideTV2);
        TextView slideTV3 = root.findViewById(R.id.slideTV3);
        if (bundle != null) {

        slideTV1.setText(bundle.getString("Name"));
        slideTV2.setText(bundle.getString("Details"));
        slideTV3.setText(bundle.getString("Date"));
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}