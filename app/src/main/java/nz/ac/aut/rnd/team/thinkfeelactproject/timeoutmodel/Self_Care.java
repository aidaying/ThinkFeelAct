package nz.ac.aut.rnd.team.thinkfeelactproject.timeoutmodel;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

import nz.ac.aut.rnd.team.thinkfeelactproject.R;

public class Self_Care extends Fragment{

    ImageButton playBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View selfCareView = inflater.inflate(R.layout.fragment_self_care,container,false);

        playBtn = (ImageButton) selfCareView.findViewById(R.id.playMusicBtn);

        setMusicPlayer();
        setupDownloadPDF();

        return selfCareView;
    }

    private void setupDownloadPDF(){


    }

    private void setMusicPlayer(){

        try {
            final AssetFileDescriptor magic = getActivity().getAssets().openFd("MagicForest.mp3");
            final MediaPlayer mediaPlayer = new MediaPlayer();

            mediaPlayer.setDataSource(magic.getFileDescriptor(), magic.getStartOffset(),magic.getLength());
            mediaPlayer.prepare();

            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                        Toast.makeText(getContext(), "Paused Music", Toast.LENGTH_SHORT);

                    }else {
                        mediaPlayer.start();
                        mediaPlayer.setLooping(true);
                        Toast.makeText(getContext(), "Music is playing", Toast.LENGTH_SHORT);
                    }

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
