package com.cloudray.sportogether.view.fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cloudray.sportogether.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //**************************************************************
    private LinearLayout editNickname, editSports, editAge, editGender, editIntro;
    private ImageView nicknameImage, sportsImage, ageImage, genderImage, introImage;
    private LinearLayout nicknameShow, sportsShow, ageShow, genderShow, introShow;
    private Button  nicknameButton,  sportsButton, ageButton, genderButton, introButton;
    private RadioGroup radiogroup;
    private RadioButton maleRadio, femaleRadio, otherRadio;
    private CheckBox basketballCheckbox, footballCheckbox, runningCheckbox;

    private Animation clockwiseRotate, unclockwiseRotate;

    //**************************************************************

    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_me, container, false);
        findView(rootView);
        setListener();
        setAnimation();
        return rootView;
    }

    public void findView(View rootView){
        editNickname = (LinearLayout)rootView.findViewById(R.id.fragment_me_nickname_edit);
        editSports = (LinearLayout)rootView.findViewById(R.id.fragment_me_sports_edit);
        editAge = (LinearLayout) rootView.findViewById(R.id.fragment_me_age_edit);
        editGender = (LinearLayout)rootView.findViewById(R.id.fragment_me_gender_edit);
        editIntro = (LinearLayout)rootView.findViewById(R.id.fragment_me_intro_edit);

        nicknameShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_nickname_edit_show);
        sportsShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_sports_edit_show);
        ageShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_age_edit_show);
        genderShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_gender_edit_show);
        introShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_intro_edit_show);

        nicknameImage = (ImageView)rootView.findViewById(R.id.fragment_me_nickname_edit_image);
        sportsImage = (ImageView)rootView.findViewById(R.id.fragment_me_sports_edit_image);
        ageImage = (ImageView)rootView.findViewById(R.id.fragment_me_age_edit_image);
        genderImage = (ImageView)rootView.findViewById(R.id.fragment_me_gender_edit_image);
        introImage = (ImageView)rootView.findViewById(R.id.fragment_me_intro_edit_image);

        nicknameButton = (Button)rootView.findViewById(R.id.fragment_me_nickname_edit_show_button);
        sportsButton = (Button)rootView.findViewById(R.id.fragment_me_sports_edit_show_button);
        ageButton = (Button)rootView.findViewById(R.id.fragment_me_age_edit_show_button);
        genderButton = (Button)rootView.findViewById(R.id.fragment_me_gender_edit_show_button);
        introButton = (Button)rootView.findViewById(R.id.fragment_me_intro_edit_show_button);

        radiogroup = (RadioGroup)rootView.findViewById(R.id.fragment_me_gender_edit_show_radiogroup);
        maleRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio1);
        femaleRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio2);
        otherRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio3);

        basketballCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox1);
        footballCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox2);
        runningCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox3);
    }

    public void setListener(){
        editNickname.setOnClickListener(this);
        editSports.setOnClickListener(this);
        editAge.setOnClickListener(this);
        editGender.setOnClickListener(this);
        editIntro.setOnClickListener(this);

        nicknameButton.setOnClickListener(this);
        sportsButton.setOnClickListener(this);
        ageButton.setOnClickListener(this);
        genderButton.setOnClickListener(this);
        introButton.setOnClickListener(this);

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

    public void setAnimation(){
        LinearInterpolator lin = new LinearInterpolator();

        clockwiseRotate = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);
        clockwiseRotate.setInterpolator(lin);
        clockwiseRotate.setFillAfter(true);

        unclockwiseRotate = AnimationUtils.loadAnimation(getContext(),R.anim.back_rotate);
        unclockwiseRotate.setInterpolator(lin);
        unclockwiseRotate.setFillAfter(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_me_nickname_edit:
                if(nicknameShow.getVisibility()==View.GONE) {
                   // nicknameImage.setRotation(90);
                    nicknameImage.startAnimation(clockwiseRotate);
                    nicknameShow.setVisibility(View.VISIBLE);
                } else {
                   // nicknameImage.setRotation(0);
                    nicknameImage.startAnimation(unclockwiseRotate);
                    nicknameShow.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_me_sports_edit:
                if(sportsShow.getVisibility()==View.GONE) {
                    // nicknameImage.setRotation(90);
                    sportsImage.startAnimation(clockwiseRotate);
                    sportsShow.setVisibility(View.VISIBLE);
                } else {
                    // nicknameImage.setRotation(0);
                    sportsImage.startAnimation(unclockwiseRotate);
                    sportsShow.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_me_age_edit:
                if(ageShow.getVisibility()==View.GONE) {
                    // nicknameImage.setRotation(90);
                    ageImage.startAnimation(clockwiseRotate);
                    ageShow.setVisibility(View.VISIBLE);
                } else {
                    // nicknameImage.setRotation(0);
                    ageImage.startAnimation(unclockwiseRotate);
                    ageShow.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_me_gender_edit:
                if(genderShow.getVisibility()==View.GONE) {
                    // nicknameImage.setRotation(90);
                    genderImage.startAnimation(clockwiseRotate);
                    genderShow.setVisibility(View.VISIBLE);
                } else {
                    // nicknameImage.setRotation(0);
                    genderImage.startAnimation(unclockwiseRotate);
                    genderShow.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_me_intro_edit:
                if(introShow.getVisibility()==View.GONE) {
                    // nicknameImage.setRotation(90);
                    introImage.startAnimation(clockwiseRotate);
                    introShow.setVisibility(View.VISIBLE);
                } else {
                    // nicknameImage.setRotation(0);
                    introImage.startAnimation(unclockwiseRotate);
                    introShow.setVisibility(View.GONE);
                }
                break;
            case R.id.fragment_me_nickname_edit_show_button:
                break;
            case R.id.fragment_me_sports_edit_show_button:
                break;
            case R.id.fragment_me_age_edit_show_button:
                break;
            case R.id.fragment_me_gender_edit_show_button:
                break;
            case R.id.fragment_me_intro_edit_show_button:
                break;
            default:
                break;
        }
    }

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
