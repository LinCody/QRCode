package tw.cody.qrcode;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button scanBtn;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = findViewById(R.id.startBtn);
        textView = findViewById(R.id.text1);
        scanBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        scanCode();
    }



    private void scanCode() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            if (result.getContents() != null) {
                textView.setText("" + result.getContents());
            }
            else {
                Toast.makeText(this,"No Result",Toast.LENGTH_SHORT).show();
            }
        } else  {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}