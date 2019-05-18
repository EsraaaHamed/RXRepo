package com.example.daytworx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    String[] maleUsers = new String[]{"Ahmed", "Mohammed", "hamed", "hamda"};
    String[] femaleUsers = new String[]{"eesraa", "sara", "sahar"};
    int []  allNumbers = new int [] {10,11,12,14,16,19,20};

    List<String> firstArr;
    List<String> secondArr;
    final List<User> users = new ArrayList<>();
    private static final String TAG = "MyApp";
    TextView userTV;
    TextView intTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        firstArr= new ArrayList<>();

        secondArr = new ArrayList<>();

        //userTV = (TextView) findViewById(R.id.userID);
        //intTv = (TextView) findViewById(R.id.integerId);
        for (String name : maleUsers) {
            User user = new User();
            user.setUserName(name);
            user.setGeneder("male");
            users.add(user);
        }
        for (String name : femaleUsers) {
            User user = new User();
            user.setUserName(name);
            user.setGeneder("female");
            users.add(user);
        }
        myAdapter = new MyAdapter(firstArr,secondArr);

        getUsersObservable()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGeneder().equalsIgnoreCase("Female");
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(User user) {
                        //userTV.append(user.getUserName() + ", " + user.getGeneder());
                        firstArr.add(user.userName);
                        Log.e(TAG, user.getUserName() + ", " + user.getGeneder());
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        myAdapter.notifyDataSetChanged();
                    }
                });


        getIntObservable()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        if(integer%2==0) {
                            //intTv.append(integer.toString()+"\n");
                            secondArr.add(integer.toString());
                            Log.e(TAG, "hey" + integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        myAdapter.notifyDataSetChanged();

                    }
                });

        recyclerView.setAdapter(myAdapter);


    }
    private Observable<User> getUsersObservable(){
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for (User user : users) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(user);
                    }
                }
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
    private Observable<Integer> getIntObservable(){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for ( int i=0; i<allNumbers.length; i++) {
                    if (!emitter.isDisposed()) {
                        if(i%2==0)
                        emitter.onNext(allNumbers[i]);
                    }
                }
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());
    }
}
