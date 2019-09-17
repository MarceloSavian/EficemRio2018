package br.ufsc.eficem.eficemrio2018;

import android.os.Handler;
import android.widget.TextView;

public class Chronometer1 {

    private static boolean isRunning;
    private Handler handler;
    private long initialTime;
    private final long MILLIS_IN_SEC = 1000L;
    private final int SECS_IN_MIN = 60;
    private TextView timeTextView,regressiveTimeTextView,lapTimeTextView;
    private Integer tempoBanco,tempoDeProva,regressiveTime,tempoVolta,somaTempo = 0;
    private Carro carro;

    public Integer getTempoVolta() {
        return tempoVolta;
    }

    public void setSomaTempo(Integer somaTempo) {
        this.somaTempo = somaTempo;
    }

    public Integer getTempoBanco() {
        return tempoBanco;
    }

    public void iniciaCronometro(RunningActivity runningActivity, Competicao competicao, Carro carro){
        handler = new Handler();
        isRunning = true;
        this.carro = carro;

        initialTime = System.currentTimeMillis();

        timeTextView = runningActivity.getTimeTextView();
        regressiveTimeTextView = runningActivity.getRegressiveTimeTextView();
        lapTimeTextView = runningActivity.getLapTimeTextView();

        handler.postDelayed(runnable, MILLIS_IN_SEC);
        tempoDeProva = Integer.valueOf(competicao.tempoDeProva);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                long totalTime = (System.currentTimeMillis() - initialTime) / MILLIS_IN_SEC;
                tempoBanco = Integer.valueOf((int) totalTime);
                carro.setTime(tempoBanco);

                regressiveTime = tempoDeProva - tempoBanco;

                tempoVolta = tempoBanco - somaTempo;
                carro.setLapTime(tempoVolta);

                timeTextView.setText(String.format("%02d:%02d", totalTime / SECS_IN_MIN, totalTime % SECS_IN_MIN));
                regressiveTimeTextView.setText(String.format("%02d:%02d", regressiveTime / SECS_IN_MIN, regressiveTime % SECS_IN_MIN));
                lapTimeTextView.setText(String.format("%02d:%02d", tempoVolta / SECS_IN_MIN, tempoVolta % SECS_IN_MIN));

                handler.postDelayed(runnable, MILLIS_IN_SEC);
            }
        }
    };
}
