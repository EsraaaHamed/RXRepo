package com.example.daythreerx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String> firstArr;
    List<String> resultArr;
    EditText inputText ;
    TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText)findViewById(R.id.editText);
        firstArr= new ArrayList<>();
        resultArr = new ArrayList<>();
        firstArr.add("esraa");
        firstArr.add("salma");
        firstArr.add("sara");
        firstArr.add("nouran");
        resultArr.addAll(firstArr);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(resultArr);
        recyclerView.setAdapter(myAdapter);
        RxTextView.textChangeEvents(inputText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        if(textViewTextChangeEvent.text().toString().isEmpty())
                        {
                            resultArr.clear();
                            resultArr.addAll(firstArr);
                            myAdapter.notifyDataSetChanged();
                        }
                        else {
                            resultArr.clear();
                            for (int i = 0; i < firstArr.size(); i++) {
                                String txt = inputText.getText().toString();

                                if (firstArr.get(i).toLowerCase().contains(txt.toLowerCase()))
                                    resultArr.add(firstArr.get(i));
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onComplete() {
                        myAdapter.notifyDataSetChanged();
                    }
                });
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
