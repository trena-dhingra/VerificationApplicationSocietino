package societino.com.societinoverification;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class viewIn extends AppCompatActivity {
    private TextView chName, chNumber, chEmail, chGender;
    private TextView socName, socAddress, socState, socCity, socPincode, socEmail;
    private Button imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_in);
        Intent intent = getIntent();
        final Hero hero = (Hero) intent.getParcelableExtra("hero");
        imv = (Button) findViewById(R.id.proofbtn);
        chName = (TextView) findViewById(R.id.chairmanname);
        chNumber = (TextView) findViewById(R.id.chairmannumber);
        chEmail = (TextView) findViewById(R.id.chairmanemail);
        chGender = (TextView) findViewById(R.id.chairmangender);
        socName = (TextView) findViewById(R.id.socName);
        socAddress = (TextView) findViewById(R.id.socAddress);
        socState = (TextView) findViewById(R.id.socState);
        socCity = (TextView) findViewById(R.id.socCity);
        socPincode = (TextView) findViewById(R.id.socPincode);
        socEmail = (TextView) findViewById(R.id.socEmail);
        //Set Text\\
        chName.setText(hero.getChName());
        chNumber.setText(hero.getChNum());
        chEmail.setText(hero.getChEmail());
        chGender.setText(hero.getChGen());
        socName.setText(hero.getSocName());
        socAddress.setText(hero.getSocAdd());
        socState.setText(hero.getSocState());
        socCity.setText(hero.getSocCity());
        socPincode.setText(hero.getSocPin());
        socEmail.setText(hero.getSocEmail());
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = hero.getChUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });

    }
}