package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//import edu.uw.fragmentdemo.R;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    public static final String SEARCH_KEY = "search_key";
    private OnSearchListener callback;


    interface OnSearchListener{
        void OnSearchSubmitted(String searchTerm);
    }


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        try{
            callback = (OnSearchListener)context;
        }catch(ClassCastException cee){
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Button button = (Button)rootView.findViewById(R.id.btn_search);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText search = (EditText)rootView.findViewById(R.id.txt_search);
                String searchTerm = search.getText().toString();
                callback.OnSearchSubmitted(searchTerm);
            }
        });
        return rootView;
    }

}
