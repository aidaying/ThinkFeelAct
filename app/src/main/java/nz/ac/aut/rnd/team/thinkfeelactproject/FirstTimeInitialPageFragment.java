package nz.ac.aut.rnd.team.thinkfeelactproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FirstTimeInitialPageFragment extends Fragment {
    public FirstTimeInitialPageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        View titleView = inflater.inflate(R.layout.fragment_first_time_initial_page, container, false);

        Button valueButton = (Button) titleView.findViewById(R.id.start);
        valueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("fragment_id",2);
                startActivity(intent);

            }
        });

        return titleView;
    }
}
