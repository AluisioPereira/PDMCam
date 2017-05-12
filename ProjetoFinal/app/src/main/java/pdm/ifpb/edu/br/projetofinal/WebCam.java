package pdm.ifpb.edu.br.projetofinal;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;

public class WebCam extends AppCompatActivity {

    private static Camera mCamera = null;

    //
    private CameraPreview mPreview;
    private ActionBar bar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_cam);
        setTitle("Web Cam");
        //
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        mCamera = Camera.open();
        //
        if (mCamera != null) {
            mPreview = new CameraPreview(this, mCamera);//testar se mCamera == null
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("AGDebug", "WebCam.onResume().");
        mPreview.setOnChangeListener(new CameraPreview.OnChangeListener(){
            public void onChange(Surface surface){
                Intent intent = new Intent(WebCam.this, ServicoDeGravacao.class);
                intent.putExtra("surface", surface);
                WebCam.this.startService(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        Log.d("pdm", "Retornando ao ambiente principal.");
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        return mCamera; // returns null if camera is unavailable
    }

}
