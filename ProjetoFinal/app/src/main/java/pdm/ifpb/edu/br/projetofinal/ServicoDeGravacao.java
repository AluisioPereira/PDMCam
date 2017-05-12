package pdm.ifpb.edu.br.projetofinal;

import android.app.Service;
import android.content.Intent;
import android.graphics.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Surface;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class ServicoDeGravacao extends Service {

    private void gravar(android.hardware.Camera mCamera, Surface surface){
        //
        Log.e("AGDebug", "Iniciando gravação.");
        //criar um gravador de mídia
        final MediaRecorder mediaRecorder = new MediaRecorder();
        //liberar a câmera
        mCamera.unlock();
        //configurar o gravador
        mediaRecorder.setCamera(mCamera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        //onde salvar
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "video.mp4");
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        //definição do surface
        mediaRecorder.setPreviewDisplay(surface);
        mediaRecorder.setMaxDuration(10000);
        //aguardar o final
        mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {
                if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED){
                    Log.e("AGDebug", "Finalizando gravação.");
                    Toast.makeText(getApplicationContext(), "Finalizando gravacao", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //preparar para gravar
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            throw new RuntimeException("Gravação não deu certo.");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Iniciando gravacao", Toast.LENGTH_SHORT).show();
        gravar(WebCam.getCameraInstance(), (Surface)intent.getParcelableExtra("surface"));
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
