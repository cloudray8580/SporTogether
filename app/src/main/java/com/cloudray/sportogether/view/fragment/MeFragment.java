package com.cloudray.sportogether.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import android.widget.Toast;

import com.cloudray.sportogether.R;
import com.cloudray.sportogether.model.Event;
import com.cloudray.sportogether.model.User;
import com.cloudray.sportogether.network.service.EventService;
import com.cloudray.sportogether.network.service.UserService;
import com.cloudray.sportogether.tools.MySharedPreference;
import com.cloudray.sportogether.view.activity.HistoryEventActivity;
import com.cloudray.sportogether.view.dialog.ConfirmPaticipateDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private LinearLayout editNickname, editSports, editAge, editGender, editPhone, editIntro;
    private ImageView nicknameImage, sportsImage, ageImage, genderImage, phoneImage, introImage;
    private LinearLayout nicknameShow, sportsShow, ageShow, genderShow, phoneShow, introShow;
    private Button  nicknameButton,  sportsButton, ageButton, genderButton, phoneButton, introButton;
    private MaterialEditText nicknameText, ageText, phoneText, introText;
    private RadioGroup radiogroup;
    private RadioButton maleRadio, femaleRadio, otherRadio;
    private CheckBox basketballCheckbox, footballCheckbox, runningCheckbox;
    private LinearLayout currentEvent, historyEvent;
    private Animation clockwiseRotate, unclockwiseRotate;
    Retrofit retrofit;
    UserService userService;
    EventService eventService;
    User user;

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
       // initRetrofit();
        findView(rootView);
        setListener();
        setAnimation();
        return rootView;
    }

    public void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userService = retrofit.create(UserService.class);
        eventService = retrofit.create(EventService.class);
    }

    public void findView(View rootView){
        editNickname = (LinearLayout)rootView.findViewById(R.id.fragment_me_nickname_edit);
        editSports = (LinearLayout)rootView.findViewById(R.id.fragment_me_sports_edit);
        editAge = (LinearLayout) rootView.findViewById(R.id.fragment_me_age_edit);
        editGender = (LinearLayout)rootView.findViewById(R.id.fragment_me_gender_edit);
        editPhone = (LinearLayout)rootView.findViewById(R.id.fragment_me_phone_edit);
        editIntro = (LinearLayout)rootView.findViewById(R.id.fragment_me_intro_edit);

        nicknameShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_nickname_edit_show);
        sportsShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_sports_edit_show);
        ageShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_age_edit_show);
        genderShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_gender_edit_show);
        phoneShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_phone_edit_show);
        introShow = (LinearLayout)rootView.findViewById(R.id.fragment_me_intro_edit_show);

        nicknameImage = (ImageView)rootView.findViewById(R.id.fragment_me_nickname_edit_image);
        sportsImage = (ImageView)rootView.findViewById(R.id.fragment_me_sports_edit_image);
        ageImage = (ImageView)rootView.findViewById(R.id.fragment_me_age_edit_image);
        genderImage = (ImageView)rootView.findViewById(R.id.fragment_me_gender_edit_image);
        phoneImage = (ImageView)rootView.findViewById(R.id.fragment_me_phone_edit_image);
        introImage = (ImageView)rootView.findViewById(R.id.fragment_me_intro_edit_image);

        nicknameButton = (Button)rootView.findViewById(R.id.fragment_me_nickname_edit_show_button);
        sportsButton = (Button)rootView.findViewById(R.id.fragment_me_sports_edit_show_button);
        ageButton = (Button)rootView.findViewById(R.id.fragment_me_age_edit_show_button);
        genderButton = (Button)rootView.findViewById(R.id.fragment_me_gender_edit_show_button);
        phoneButton = (Button)rootView.findViewById(R.id.fragment_me_phone_edit_show_button);
        introButton = (Button)rootView.findViewById(R.id.fragment_me_intro_edit_show_button);

        nicknameText = (MaterialEditText)rootView.findViewById(R.id.fragment_me_nickname_edit_show_new);
        ageText = (MaterialEditText)rootView.findViewById(R.id.fragment_me_age_edit_show_new);
        phoneText = (MaterialEditText)rootView.findViewById(R.id.fragment_me_phone_edit_show_new);
        introText = (MaterialEditText)rootView.findViewById(R.id.fragment_me_intro_edit_show_new);

        radiogroup = (RadioGroup)rootView.findViewById(R.id.fragment_me_gender_edit_show_radiogroup);
        maleRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio1);
        femaleRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio2);
        otherRadio = (RadioButton)rootView.findViewById(R.id.fragment_me_gender_edit_show_radio3);

        basketballCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox1);
        footballCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox2);
        runningCheckbox = (CheckBox)rootView.findViewById(R.id.fragment_me_sports_edit_show_checkbox3);

        currentEvent = (LinearLayout)rootView.findViewById(R.id.fragment_me_current_event);
        historyEvent = (LinearLayout)rootView.findViewById(R.id.fragment_me_history_event);
    }

    public void setListener(){
        editNickname.setOnClickListener(this);
        editSports.setOnClickListener(this);
        editAge.setOnClickListener(this);
        editGender.setOnClickListener(this);
        editPhone.setOnClickListener(this);
        editIntro.setOnClickListener(this);

        nicknameButton.setOnClickListener(this);
        sportsButton.setOnClickListener(this);
        ageButton.setOnClickListener(this);
        genderButton.setOnClickListener(this);
        phoneButton.setOnClickListener(this);
        introButton.setOnClickListener(this);

        currentEvent.setOnClickListener(this);
        historyEvent.setOnClickListener(this);

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
            case R.id.fragment_me_phone_edit:
                if(phoneShow.getVisibility()==View.GONE) {
                    // nicknameImage.setRotation(90);
                   phoneImage.startAnimation(clockwiseRotate);
                   phoneShow.setVisibility(View.VISIBLE);
                } else {
                    // nicknameImage.setRotation(0);
                    phoneImage.startAnimation(unclockwiseRotate);
                    phoneShow.setVisibility(View.GONE);
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
                String newNickName = nicknameText.getText().toString().trim();
                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setNickname(newNickName);
                Toast.makeText(getContext(), user.getUserName()+ " "+user.getPassword(),Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
                //Call<User> call1 = userService.updateUser(user);
//                call1.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_sports_edit_show_button:
                List<Integer> sportsInterested = new ArrayList<Integer>();
                if(basketballCheckbox.isChecked())
                    sportsInterested.add(1);
                if(footballCheckbox.isChecked())
                    sportsInterested.add(2);
                if(runningCheckbox.isChecked())
                    sportsInterested.add(3);

                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setInterestedSprots(sportsInterested);
                Toast.makeText(getContext(), user.getInterestedSprots()+ " ",Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
                //Call<User> call2 = userService.updateUser(user);
//                call2.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_age_edit_show_button:
                String age = ageText.getText().toString().trim();
                Integer ageInt = Integer.parseInt(age);
                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setAge(ageInt);
                Toast.makeText(getContext(), user.getAge()+ " ",Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
//                Call<User> call3 = userService.updateUser(user);
//                call3.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_gender_edit_show_button:
                int newGender = -1;
                if(femaleRadio.isChecked())
                    newGender = 0;
                if(maleRadio.isChecked())
                    newGender = 1;
                if(otherRadio.isChecked())
                    newGender = 2;
                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setGender(newGender);
                Toast.makeText(getContext(), user.getGender()+ " ",Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
//                Call<User> call4 = userService.updateUser(user);
//                call4.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_phone_edit_show_button:
                String newPhone = phoneText.getText().toString().trim();
                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setPhone(newPhone);
                Toast.makeText(getContext(), user.getPhone()+ " ",Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
//                Call<User> call5 = userService.updateUser(user);
//                call5.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_intro_edit_show_button:
                String newIntro = introText.getText().toString().trim();
                user = MySharedPreference.base64Decode((String)MySharedPreference.getData(getContext(), "user", " "));
                user.setSelfIntro(newIntro);
                Toast.makeText(getContext(), user.getSelfIntro()+ " ",Toast.LENGTH_SHORT).show();
                MySharedPreference.storeData(getContext(), "user",  MySharedPreference.base64Encode(user));
//                Call<User> call6 = userService.updateUser(user);
//                call6.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
                break;
            case R.id.fragment_me_current_event:
                Call<Event> call7 = eventService.getCurrentEvents((String)MySharedPreference.getData(getContext(),"userid", ""));
                call7.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        ConfirmPaticipateDialog dialog;
                        ConfirmPaticipateDialog.Builder builder = new ConfirmPaticipateDialog(getActivity(), R.style.dialog). new Builder(getActivity());
                        dialog = builder.create();
                        dialog.setEvent(response.body());
                        dialog.show();
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Log.e("get current event", "net work failure");
                    }
                });
                break;
            case R.id.fragment_me_history_event:
                Intent intent = new Intent(getActivity(), HistoryEventActivity.class);
                startActivity(intent);
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
