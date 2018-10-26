package com.example.locnt.app_project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BookActivity extends AppCompatActivity {
    Spinner startHourSpinner, endHourSpinner, numberPitch;
    TextView help, txtDate, txtPitchName, txtTotal;
    Button btnBook;

    int number, total;
    double start, end;
    int totalPrice = 0;
    String dateBook, startDate, endDate, pitchName;
    private View progressView;
    private View bookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        progressView = findViewById(R.id.book_progess);
        bookView = findViewById(R.id.bookView);
        startHourSpinner = findViewById(R.id.startHour);
        endHourSpinner = findViewById(R.id.endHour);
        numberPitch = findViewById(R.id.numberPitch);
//        txtDetail = findViewById(R.id.txtDetail);
        txtDate = findViewById(R.id.txtDate);
        txtPitchName = findViewById(R.id.txtPitchName);
        Intent intent = this.getIntent();
        pitchName = intent.getStringExtra("name");
        txtPitchName.setText(pitchName);
        help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(BookActivity.this);
                String type;
                if(!pitchName.contains("A2")) {
                    type = "Sân 5 người: 1, 2, 3" + "\n" + "Sân 7 người: 4, 5";
                } else {
                    type = "Sân 5 người: 1, 2, 3, 4, 5, 6, 7, 8" + "\n" + "Sân 7 người: 9";
                }
                alert.setCancelable(false);
                alert.setTitle("Sân");
                alert.setMessage(type);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });
        txtTotal = findViewById(R.id.txtTotal);
//        txtDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BookActivity.this, DetailActivity.class);
//                startActivity(intent);
////                finish();
//            }
//        });
        txtDate.setText("Chọn ngày");
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate();
            }
        });
        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start >= end || (end - start) < 1) {
                    Toast.makeText(BookActivity.this, "Giờ tối thiểu là 1 giờ.", Toast.LENGTH_SHORT).show();
                } else if (dateBook == null) {
                    Toast.makeText(BookActivity.this, "Vui lòng chọn ngày.", Toast.LENGTH_SHORT).show();
                } else {
                    show(pitchName, dateBook, startDate, endDate, number, totalPrice);
                }
            }
        });

        String[] data = {"6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00"};

        String[] data1 = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
                "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
                "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30",
                "21:00", "21:30", "22:00", "22:30", "23:00"};
        String[] data2;
        if(!pitchName.contains("A2")) {
            data2  = new String[]{"1", "2", "3", "4", "5"};
        } else {
            data2  = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data2);
        startHourSpinner.setAdapter(adapter);
        popup(startHourSpinner);
        endHourSpinner.setAdapter(adapter1);
        popup(endHourSpinner);
        numberPitch.setAdapter(adapter2);
        adapter.notifyDataSetChanged();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        change();
    }

    public void popup(Spinner s) {
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(s);
            popupWindow.setHeight(500);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void change() {
        startHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String s = adapterView.getItemAtPosition(position).toString();
                String[] s1 = s.split(":");
                int half = 0;
                for (int i = 0; i < 1; i++) {
                    start = Double.parseDouble(s1[i]);
                    half = Integer.parseInt(s1[i+1]);
                }
                if(half == 30) {
                    start = start + 0.5;
                }
//                Toast.makeText(BookActivity.this, "" + start, Toast.LENGTH_SHORT).show();
                startDate = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        endHourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String s = adapterView.getItemAtPosition(position).toString();
                String[] s1 = s.split(":");
                int half = 0;
                for (int i = 0; i < 1; i++) {
                    end = Double.parseDouble(s1[i]);
                    half = Integer.parseInt(s1[i+1]);
                }
                if(half == 30) {
                    end = end + 0.5;
                }
                if(!pitchName.contains("A2")) {
                    if(number == 4 || number == 5) {
                        if(end - start >= 1) {
                            if(end <= 17){
                                totalPrice = (int) ((end - start) * 300000);
                                txtTotal.setText(""+totalPrice);
                            } else {
                                totalPrice = (int) ((end - start) * 400000);
                                txtTotal.setText(""+totalPrice);
                            }
                        }
                    } else {
                        if(end <= 16){
                            totalPrice = (int) ((end - start) * 150000);
                            txtTotal.setText(""+totalPrice);
                        } else if(16 < end && end <= 18){
                            totalPrice = (int) ((end - start) * 180000);
                            txtTotal.setText(""+totalPrice);
                        } else {
                            totalPrice = (int) ((end - start) * 220000);
                            txtTotal.setText(""+totalPrice);
                        }
                    }
                } else {
                    if(number == 9) {
                        if(end - start >= 1) {
                            if(end <= 17){
                                totalPrice = (int) ((end - start) * 300000);
                                txtTotal.setText(""+totalPrice);
                            } else {
                                totalPrice = (int) ((end - start) * 400000);
                                txtTotal.setText(""+totalPrice);
                            }
                        }
                    } else {
                        if(end <= 16){
                            totalPrice = (int) ((end - start) * 150000);
                            txtTotal.setText(""+totalPrice);
                        } else if(16 < end && end <= 18){
                            totalPrice = (int) ((end - start) * 180000);
                            txtTotal.setText(""+totalPrice);
                        } else {
                            totalPrice = (int) ((end - start) * 220000);
                            txtTotal.setText(""+totalPrice);
                        }
                    }
                }
//                Toast.makeText(BookActivity.this, "" + end, Toast.LENGTH_SHORT).show();
                endDate = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberPitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                number = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
//                Toast.makeText(BookActivity.this, "" + number, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void showPriceDetail(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        String s;
//        if(!pitchName.contains("A2")) {
            s = "Khung giờ:" + "\n" + "Sân 5: " + "\n" + "06:00 - 15:00: 150.000/h" + "\n" + "15:00 - 18:00: 180.000/h" + "\n" + "18:00 - 24:00: 220.000/h" + "\n" + "Sân 7:" + "\n" + "6:00 - 17:00: 300.000/h"  + "\n" + "17:00 - 24:00: 400.000/h" + "\n" + "Không cần đặt cọc." + "\n" + "Bóng dùng miễn phí." + "\n" + "Nước uống miễn phí.";
//        } else {
//            s = "Khung giờ:" + "\n" + "06:00 - 16:00: 150.000/h" + "\n" + "16:00 - 17:00: 180.000/h" + "\n" + "17:00 - 24:00: 220.000/h" + "\n" + "Không cần đặt cọc." + "\n" + "Bóng dùng miễn phí." + "\n" + "Nước uống miễn phí.";
//        }
        alert.setCancelable(false);
        alert.setTitle("Bảng giá");
        alert.setMessage(s);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void show(String name, String date, String start, String end, int number, int total) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setTitle("Thông tin đặt sân");
        alert.setMessage("Tên sân: " + name + "\n" + "Ngày: " + date + "\n" + "Giờ bắt đầu: " + start + "\n"
                + "Giờ kết thúc: " + end + "\n" + "Sân số: " + number + "\n" + "Tổng tiền: " + total);
        alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        showProgress(true);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(BookActivity.this, "Đặt sân thành công ", Toast.LENGTH_LONG).show();
                        showProgress(false);
                    }
                }.start();

                Intent intent = new Intent(BookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
    private void showProgress(boolean param) {
        progressView.setVisibility(param ? View.VISIBLE : View.GONE);
        bookView.setVisibility(param ? View.GONE : View.VISIBLE);
    }
    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtDate.setText(sdf.format(calendar.getTime()));
                dateBook = sdf.format(calendar.getTime());
            }
        }, year, month, date);
        datePickerDialog.setCancelable(false);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}
