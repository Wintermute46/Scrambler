package m2a.com.scrambler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CryptActivity extends AppCompatActivity {

    public TextView vCrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypt);

        Intent intent = getIntent();        //ловим интент
        String crypText = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);        //выдираем из него сообщение

        vCrypt = (TextView)findViewById(R.id.textView2);    //ассоциируем переменную с объектом
        vCrypt.setText(crypText);       //выводим шифровку
    }

    public void onDecrypt (View view) {

        EditText eKey;      //ключ
        TextView vLabel;    //загооловок

        eKey = (EditText)findViewById(R.id.editText);       //ассоциируем переменную с объектом
        vLabel = (TextView)findViewById(R.id.textView);     //ассоциируем переменную с объектом
        byte[] crypText = vCrypt.getText().toString().getBytes();       //конвертим шифровку в байты
        byte[] key = eKey.getText().toString().getBytes();      //конвертим ключ в байты
        byte[] keyBase = Base64.encode(key, Base64.DEFAULT);
        byte[] decrypTextBase = new byte[crypText.length];      //объявляем выхлоп с длиной

        for (int i = 0; i < crypText.length; i++) {         //шагаем по шифровке

            decrypTextBase[i] = (byte)(crypText[i]^keyBase[i%keyBase.length]);      //ксорим то на это

        }

        byte[] decrypText = Base64.decode(decrypTextBase, Base64.DEFAULT);

        vCrypt.setText(new String(decrypText));     //выводим расшифрованый текст
        vLabel.setText(R.string.decrypt_label);

    }
}
