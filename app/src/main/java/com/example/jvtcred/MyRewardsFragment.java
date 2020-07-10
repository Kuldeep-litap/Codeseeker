package com.example.jvtcred;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRewardsFragment extends Fragment {

    private RecyclerView rewardsRecyclerView;
    private Dialog loadingDialog;
    public static MyRewardsAdapter myRewardsAdapter;



    public MyRewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setStatusBarColor(this.getResources().getColor(R.color.white));

        View view= inflater.inflate(R.layout.fragment_my_rewards, container, false);

        //////loadingDialog

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);//dialog widh and height
        loadingDialog.show();

        /////loadingDialog

        rewardsRecyclerView= view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        rewardsRecyclerView.setLayoutManager(layoutManager);
        myRewardsAdapter = new MyRewardsAdapter(DBqueries.rewardModelList,false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        if (DBqueries.rewardModelList.size()==0){  //query will fire here with the help of if statment
            DBqueries.loadRewards(getContext(),loadingDialog,true);
        }else{
            loadingDialog.dismiss();
        }

        myRewardsAdapter.notifyDataSetChanged();
        return view;
    }

}
