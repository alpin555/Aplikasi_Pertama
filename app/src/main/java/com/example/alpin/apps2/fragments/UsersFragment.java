package com.example.alpin.apps2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alpin.apps2.R;
import com.example.alpin.apps2.adapters.UsersAdapter;
import com.example.alpin.apps2.api.RetrofitClient;
import com.example.alpin.apps2.models.User;
import com.example.alpin.apps2.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<UsersResponse> call = RetrofitClient.getInstance().getApi().getUsers();

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                userList = response.body().getUsers();
                adapter = new UsersAdapter(getActivity(),userList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }
}
