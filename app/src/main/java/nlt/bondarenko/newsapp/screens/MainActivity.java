package nlt.bondarenko.newsapp.screens;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nlt.bondarenko.newsapp.R;
import nlt.bondarenko.newsapp.screens.articleList.ArticleListFragment;
import nlt.bondarenko.newsapp.screens.bokmarks.BookmarkFragment;
import nlt.bondarenko.newsapp.screens.sourceList.SourceListFragment;

public class MainActivity extends AppCompatActivity {

    private ArticleListFragment articleListFragment;
    private BookmarkFragment bookmarkFragment;
    private SourceListFragment sourceListFragment;
    public static String TAG_ARTICLE_LIST_FRAGMENT = "ArticleListFragment";
    public static String TAG_ARTICLE_FRAGMENT = "ArticleFragment";
    public static String TAG_BOOKMARK_FRAGMENT = "BookmarkFragment";
    public static String TAG_SOURCE_LIST_FRAGMENT = "SourceListFragment";
    private BottomNavigationView bottomNavigationViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewMain = findViewById(R.id.bottom_navigation_main);

        articleListFragment = new ArticleListFragment(getSupportFragmentManager(), bottomNavigationViewMain);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout_main, articleListFragment, TAG_ARTICLE_LIST_FRAGMENT)
                .commit();


        bottomNavigationViewMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        articleListFragment = new ArticleListFragment(getSupportFragmentManager(), bottomNavigationViewMain);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout_main, articleListFragment, TAG_ARTICLE_LIST_FRAGMENT)
                                .commit();
                        break;
                    case R.id.navigation_sources:
                        sourceListFragment = new SourceListFragment(item);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_layout_main, sourceListFragment, TAG_SOURCE_LIST_FRAGMENT)
                                .commit();

                        break;
                    case R.id.navigation_bookmarks:
                        bookmarkFragment = new BookmarkFragment(getSupportFragmentManager(), bottomNavigationViewMain);
                        FragmentManager fragmentManagerBookmark = getSupportFragmentManager();
                        fragmentManagerBookmark.beginTransaction()
                                .replace(R.id.frame_layout_main, bookmarkFragment, TAG_BOOKMARK_FRAGMENT)
                                .commit();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Fragment articleListFragment = getSupportFragmentManager().findFragmentByTag(TAG_ARTICLE_LIST_FRAGMENT);
        Fragment articleFragment = getSupportFragmentManager().findFragmentByTag(TAG_ARTICLE_FRAGMENT);

        if (articleListFragment != null && articleListFragment.isVisible()) {
            super.onBackPressed();
        } else if (articleFragment != null && articleFragment.isVisible()) {
            super.onBackPressed();
        } else {
            bottomNavigationViewMain.setSelectedItemId(R.id.navigation_home);
        }
    }
}