package lap.hou.demoreactiveandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Observable<String> mObservable;
    private Observer<String> mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=(TextView)findViewById(R.id.textView);
        mObservable=Observable.just("Hello rx Android!!!");
        mObserver= new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Toast.makeText(MainActivity.this, "OnSubscribe", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(@NonNull String s) {
                mTextView.setText(s);
                Toast.makeText(MainActivity.this, "onNext", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity.this, "onERRor", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "OnComplete", Toast.LENGTH_SHORT).show();
            }
        };
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeNow();
            }
        });
    }
    public void subscribeNow(){
        mObservable.subscribe(mObserver);

    }
}
