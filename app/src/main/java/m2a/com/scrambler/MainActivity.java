package m2a.com.scrambler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        byte[] crypText = new byte[secText.length];             //шифр и его длина
        //String secText = eSec.getText().toString();     //взяли то, что введено в секретный текст
        //String key = eKey.getText().toString();         //взяли ключ
        //String crypText = "";

        for (int i = 0; i < secText.length; i++) {        //идем по секретному тексту

            crypText[i] = (byte)(secText[i] ^ key[i % key.length]);   //ксорим одно с другим

        }

        //пилим передатчик
        Intent intent = new Intent(this, CryptActivity.class);
        intent.putExtra(EXTRA_MESSAGE, new String(crypText));
        startActivity(intent);      //давай досвидос!

    }

}
