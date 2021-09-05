//package net.nov.notespart2;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//    private Activity activity;
//    public View view;
//    private Context context;
//    ArrayList<NotesDetails> notesDetailsArrayList;
//    NotesAdapter adapter = null;
//    RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    view = findViewById(R.id.container1);
//    activity = MainActivity.this;
//    context = getApplicationContext();
//    notesDetailsArrayList = new ArrayList<>();
//
//    notesDetailsArrayList.add(new NotesDetails("First", "Hi, it's me mario1", "03.05.2021"));
//        notesDetailsArrayList.add(new NotesDetails("Second", "Hi, it's me mario2", "03.05.2021"));
//        notesDetailsArrayList.add(new NotesDetails("Third", "Hi, it's me mario3", "03.05.2021"));
//    adapter = new NotesAdapter(context,notesDetailsArrayList,activity);
//
//    recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new GridLayoutManager(activity,1,GridLayoutManager.VERTICAL,false));
//    recyclerView.setAdapter(adapter);
//    adapter.notifyDataSetChanged();
//
//
//
//
//    }
//
//    public void goToFragment (){
//        View view = findViewById(R.id.frame2);
//        view.setVisibility(View.INVISIBLE);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container2,new NotesFragment(),"123")
//                .addToBackStack("one")
//                .commit();
//    }
//
//
//
//
//}