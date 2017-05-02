package m2a.com.scrambler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eSec, eKey;  //переменные класса EditText для дальнейшей ассоциации
    public static final String EXTRA_MESSAGE = "m2a.com.scrambler.MESSAGE";     //параметр для интента

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eSec = (EditText)findViewById(R.id.editText3);  //увязываем Edit с переменной, секретный текст
        eKey = (EditText)findViewById(R.id.editText4);  //увязываем Edit с переменной, ключ
       }

    public void onCrypt (View view) {

        byte[] secText = eSec.getText().toString().getBytes();  //взяли то, что введено в секретный текст
        byte[] key = eKey.getText().toString().getBytes();      //взяли ключ

        if ( (secText.length != 0) & (key.length !=0) ) {

            byte[] secTextBase = Base64.encode(secText, Base64.DEFAULT);    //конвертируем текст в Base64
            byte[] keyBase = Base64.encode(key, Base64.DEFAULT);            //конвертируем ключ в Base64

            byte[] crypText = new byte[secTextBase.length];             //шифр и его длина

            for (int i = 0; i < secTextBase.length; i++) {        //идем по секретному тексту

                crypText[i] = (byte) (secTextBase[i] ^ keyBase[i % keyBase.length]);   //ксорим одно с другим

            }

            //пилим передатчик
            Intent intent = new Intent(this, CryptActivity.class);
            intent.putExtra(EXTRA_MESSAGE, new String(crypText));
            startActivity(intent);      //давай досвидос!

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.message, Toast.LENGTH_LONG);
            toast.show();
        }

    }

}
