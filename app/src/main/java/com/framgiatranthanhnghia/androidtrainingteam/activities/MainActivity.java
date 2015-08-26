package com.framgiatranthanhnghia.androidtrainingteam.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.fragments.HomeFragment;
import com.framgiatranthanhnghia.androidtrainingteam.fragments.NavigationDrawerCallbacks;
import com.framgiatranthanhnghia.androidtrainingteam.fragments.NavigationDrawerFragment;
import com.framgiatranthanhnghia.androidtrainingteam.fragments.NotesFragment;
import com.framgiatranthanhnghia.androidtrainingteam.fragments.NotificationFragment;

public class MainActivity extends BaseActivity implements NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        switch (position){
            case 1:
                startFragment(NotificationFragment.class,null);
                break;
            case 2:
                startFragment(NotesFragment.class,null);
                break;
            default:
                startFragment(HomeFragment.class,null);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        }
        else if(getSupportFragmentManager().getBackStackEntryCount()==1)
        {
            this.finish();
        }else{
            super.onBackPressed();
        }
    }
}
