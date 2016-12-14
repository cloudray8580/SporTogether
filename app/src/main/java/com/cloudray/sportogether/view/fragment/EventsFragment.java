// # CSIT 5510     # Li Zhe        20386967    zlicx@connect.ust.hk
// # CSIT 5510     # Zhang Chen    20399782    jxzcv.zhang@connect.ust.hk
// # CSIT 5510     # Zhao Shixiong 20402060    szhaoag@connect.ust.hk
package com.cloudray.sportogether.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.adapter.EventListAdapter;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.network.service.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private List<Event> data;
    private EventListAdapter mAdapter;
    private Retrofit retrofit;
    private EventService service;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.fragment_event_list_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        retrofit = new Retrofit.Builder().baseUrl("http://52.43.221.21:8081/").addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(EventService.class);
        Call<List<Event>> call = service.getAllEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                data = response.body();
                if(data == null || data.size() == 0){
                    Log.e("event_fragment", "data no valid");
                    recyclerView.setVisibility(View.GONE);
                    return;
                } else {
                    mAdapter = new EventListAdapter(getActivity(), data);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("get local events", "net work failre");
                Toast.makeText(getContext(), "net work failure", Toast.LENGTH_SHORT).show();
            }
        });

       // getData();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // this is just used for test
//    public void getData(){
//        Event myEvent = new Event();
//        myEvent.setCurrentPlayerNumber(2);
//        myEvent.setRequiredPlayerNumber(5);
//        myEvent.setType(1);
//        myEvent.setEventTitle("Let's play basketball!");
//        myEvent.setLocation("School gym in LG1");
//        myEvent.setEventDescription("welcome everyone here");
//        myEvent.setTime("19 : 00");
//        myEvent.setValid(true);
//
//        List<Event> testList = new ArrayList<Event>();
//        for(int i = 0; i < 10; i++)
//            testList.add(myEvent);
//
//        data = testList;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
