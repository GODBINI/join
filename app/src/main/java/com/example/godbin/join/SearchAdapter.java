package com.example.godbin.join;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<SearchData> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_searched, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(SearchData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView hotel_name;
        private TextView hotel_address;
        private Button reservation;



        ItemViewHolder(View itemView) {
            super(itemView);

            hotel_name = itemView.findViewById(R.id.hotel_name);
            hotel_address = itemView.findViewById(R.id.hotel_address);
            reservation = itemView.findViewById(R.id.reservation);
        }

        void onBind(SearchData data) {
            hotel_name.setText(data.getHotel_name());
            hotel_address.setText(data.getHotel_address());
            reservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try{
                        BitMatrix bitMatrix = multiFormatWriter.encode("20190730", BarcodeFormat.QR_CODE,200,200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        Intent intent = new Intent(v.getContext(),CheckingReservation.class);
                        intent.putExtra("pic",bitmap);
                        v.getContext().startActivity(intent);
                    }catch (WriterException e){
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}