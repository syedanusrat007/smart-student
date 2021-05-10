package com.cse.mist.audio_upload_download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AudioTab extends Fragment  {

    RecyclerView rvlist;
    DatabaseReference draudio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_audio_tab,container,false);
        draudio = FirebaseDatabase.getInstance().getReference().child("Audio");
        rvlist = (RecyclerView) rootview.findViewById(R.id.rv_audio);
        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Downloads,DownloadingAudio> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Downloads, DownloadingAudio>(
                Downloads.class,R.layout.downloads,DownloadingAudio.class,draudio
        ) {
            @Override
            protected void populateViewHolder(DownloadingAudio viewHolder, Downloads model, int position) {
                viewHolder.setAudiotitle(model.getAudiotitle());
                viewHolder.setUid(model.getUid());

            }
        };
        rvlist.setAdapter(firebaseRecyclerAdapter);
    }

    public  static  class  DownloadingAudio extends RecyclerView.ViewHolder{

        View mview;

        public DownloadingAudio(View itemView) {
            super(itemView);

            mview=itemView;
        }

        public  void  setAudiotitle(String audiotitle){

            TextView Audiotitle = (TextView) mview.findViewById(R.id.tvaudiofilename);
            Audiotitle.setText(audiotitle);

        }

        public  void  setUid(String uid) {

            TextView Audiouser = (TextView) mview.findViewById(R.id.tvaudiousername);
            Audiouser.setText(uid);

        }
    }
}
