package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener,
        SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";
    private SearchFragment searchFragment;
    private MovieListFragment listFragment;
    private DetailFragment detailFragment;
    private ViewPager pager;
    private MoviePagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFragment = SearchFragment.newInstance();
        pager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }

//    //respond to search button clicking
//    public void handleSearchClick(View v){
//        EditText text = (EditText)findViewById(R.id.txt_search);
//        String searchTerm = text.getText().toString();
//
//        listFragment = MovieListFragment.newInstance(searchTerm);
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.container, listFragment, MOVIE_LIST_FRAGMENT_TAG);
//        ft.addToBackStack(null);
//        ft.commit();
//    }


    @Override
    public void OnSearchSubmitted(String searchTerm) {
        listFragment = MovieListFragment.newInstance(searchTerm);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(1); //hard-code the shift
    }

    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie);
        pagerAdapter.notifyDataSetChanged();
        pager.setCurrentItem(2); //hard-code the shift
    }



    private class MoviePagerAdapter extends FragmentStatePagerAdapter{

        public MoviePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return searchFragment;
            }
            if(position == 1){
                return listFragment;
            }
            if(position == 2){
                return detailFragment;
            }
            return null;
        }

        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            if(listFragment == null){
                return 1;
            }else if (detailFragment == null){
                return 2;
            }else{
                return 3;
            }
        }
    }

}
