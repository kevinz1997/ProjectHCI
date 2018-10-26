package com.example.locnt.app_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    ImageView imgDetail;
    TextView txtNameDetail,txtPhoneDetail,txtAddrDetail,txtIntroDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        createView();
        rate();
        TextView back = findViewById(R.id.txtDetailBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void rate() {
        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.txtRating);
        mFeedback = findViewById(R.id.edt);
        mSendFeedback = findViewById(R.id.btnRate);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Ghét");
                        break;
                    case 2:
                        mRatingScale.setText("Không thích");
                        break;
                    case 3:
                        mRatingScale.setText("OK");
                        break;
                    case 4:
                        mRatingScale.setText("Thích");
                        break;
                    case 5:
                        mRatingScale.setText("Rất thích");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    SharedPreferences pre = getSharedPreferences("feedback", MODE_PRIVATE);
//                    SharedPreferences.Editor edit = pre.edit();
//                    edit.putString("datafeedback",mFeedback.getText().toString());
//                    edit.putString("ratefeedback",mRatingScale.getText().toString());
//                    edit.commit();
                    Toast.makeText(DetailActivity.this, "Cảm ơn bạn đã đánh giá." + mFeedback.getText().toString(), Toast.LENGTH_SHORT).show();
                    mFeedback.setText("");
                    mRatingBar.setRating(5);
            }
        });
    }

    private void createView() {
        imgDetail = findViewById(R.id.imgDetail);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtPhoneDetail = findViewById(R.id.txtPhoneDetail);
        txtAddrDetail = findViewById(R.id.txtAddrDetail);
        txtIntroDetail = findViewById(R.id.txtIntroDetail);
        String introduce = "Trung tâm gồm 2 sân đa năng, 1 nhà thi đấu đa năng, 1 hồ bơi cao cấp, và 9 sân bóng đá mini (gồm 8 sân bóng 5 người và 1 sân bóng 7 người)." + "\n" + "\n" + "Sân bóng đá mini nhân tạo được trang bị mái che. Ngoài ra hệ thống đèn chiếu sáng đều đạt tiêu chuẩn hiện đại. Đảm bảo cho các cầu thủ, vận động viên thoải mái nhất và thí đấu tốt nhất, trong điều kiện tốt nhất.";
        Intent intent = this.getIntent();
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String addr = intent.getStringExtra("addr");
//        Toast.makeText(this, name + "\n" + phone + "\n" + addr, Toast.LENGTH_LONG).show();
        if(name.contains("Trung tâm thể thao A2")) {
            imgDetail.setBackgroundResource(R.drawable.a2);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText(introduce);
        } else if(name.contains("Sân bóng đá cỏ nhân tạo Đạt Đức")) {
            imgDetail.setBackgroundResource(R.drawable.datduc);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText(introduce);
        } else if(name.contains("Sân bóng đá cỏ nhân tạo Phương Nam")) {
            imgDetail.setBackgroundResource(R.drawable.phuongnam);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText(introduce);
        } else if(name.contains("Sân Bóng Trần Hưng Đạo")) {
            imgDetail.setBackgroundResource(R.drawable.thd);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText(introduce);
        } else if(name.contains("Sân bóng đá mini đường Cây Trâm")) {
            imgDetail.setBackgroundResource(R.drawable.caytram);
            txtNameDetail.setText(name);
            txtPhoneDetail.setText(phone);
            txtAddrDetail.setText(addr);
            txtIntroDetail.setText(introduce);
        }
    }

    public void clickToDetailBook(View view) {
        Intent intent = new Intent(this,BookActivity.class);
        intent.putExtra("name",txtNameDetail.getText().toString());
        startActivity(intent);
    }
}
