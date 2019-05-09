package net.eazypg.moduletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory;
import com.google.android.play.core.splitinstall.SplitInstallRequest;
import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    SplitInstallManager splitInstallManager;

    Button featureButton1, featureButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitInstallManager = SplitInstallManagerFactory.create(getApplicationContext());

        featureButton1 = findViewById(R.id.featureButton1);
        featureButton2 = findViewById(R.id.featureButton2);

        featureButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFirstFeature();
            }
        });

        featureButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadSecondFeature();
            }
        });
    }

    private void loadFirstFeature() {

        SplitInstallRequest splitInstallRequest = SplitInstallRequest.newBuilder().addModule("feature1").build();

        splitInstallManager.startInstall(splitInstallRequest).addOnSuccessListener(new OnSuccessListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {

                Intent intent = new Intent().setClassName(getPackageName(), "net.eazypg.feature1.Feature1Activity");
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                Toast.makeText(MainActivity.this, "Couldn't download the feature", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSecondFeature() {

        SplitInstallRequest splitInstallRequest = SplitInstallRequest.newBuilder().addModule("feature2").build();

        splitInstallManager.startInstall(splitInstallRequest).addOnSuccessListener(new OnSuccessListener<Integer>() {
            @Override
            public void onSuccess(Integer integer) {

                Intent intent = new Intent().setClassName(getPackageName(), "net.eazypg.feature2.Feature2Activity");
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                Toast.makeText(MainActivity.this, "Couldn't download the feature", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
