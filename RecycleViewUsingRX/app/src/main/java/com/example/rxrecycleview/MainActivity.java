package com.example.rxrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String TAG ="";
    List<String> first;
    List<String> second;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        first= new ArrayList<>();

        second = new ArrayList<>();


        Observable<String> nameObservable = Observable.just("esraa","hamed","ahmed","hamed"); //1
         compositeDisposable = new CompositeDisposable();
        DisposableObserver<String> nameObserver = getNameObserver();

        compositeDisposable.add(nameObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(nameObserver));


        DisposableObserver<String> descObserver = getDescCaps();
        compositeDisposable.add(nameObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(descObserver));




        myAdapter = new MyAdapter(first,second);
        recyclerView.setAdapter(myAdapter);


    }
    private DisposableObserver<String> getDescCaps() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                second.add(s.toUpperCase());
            }
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
            @Override
            public void onComplete() {
               myAdapter.notifyDataSetChanged();
            }
        };
    }
    private DisposableObserver<String> getNameObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
               first.add(s);
            }
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
            @Override
            public void onComplete() {
                myAdapter.notifyDataSetChanged();
            }
        };
    }
}
