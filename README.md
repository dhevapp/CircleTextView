# CircleTextView
A circular TextView for Android

CircleTextView adalah custom View Android (Java) yang menampilkan sebuah lingkaran berisi inisial (huruf pertama dari nama). Teks default putih, background bisa diatur, ukuran teks menyesuaikan ukuran view, dan view dipastikan berbentuk kotak (segi empat sama sisi) di layout.

### Fitur
- Menampilkan inisial (huruf pertama, uppercase) dari nama.
- Warna teks dan background bisa diatur baik via XML attributes maupun programatis.
- Otomatis menyesuaikan ukuran teks berdasarkan ukuran view.
- Support atribut custom: name, bgColor, textColor, textSizeDp, borderWidthDp, borderColor.
- Dapat digunakan di layout XML atau di-set dari Activity/Fragment.

---
## attrs.xml
Tambahkan atribut custom di res/values/attrs.xml
```xml
<resources>
    <declare-styleable name="CircleTextView">
        <attr name="bgColor" format="color"/>
        <attr name="textColor" format="color"/>
        <attr name="name" format="string"/>
        <attr name="textSizePx" format="dimension"/>
        <attr name="borderWidthDp" format="dimension"/>
        <attr name="borderColor" format="color"/>
    </declare-styleable>
</resources>
```

---

## Contoh penggunaan di layout (XML)
```xml
<com.dhevapp.views.CircleTextView
    android:id="@+id/circleText"
    android:layout_width="64dp"
    android:layout_height="64dp"
    app:bgColor="#3F51B5"
    app:textColor="#FFFFFF"
    app:name="Agus"
    app:textSizePx="24sp"
    app:borderWidthDp="2dp"
    app:borderColor="#FFFFFF"/>
```

---

## Contoh penggunaan di Activity
```java
CircleTextView ctv = findViewById(R.id.circleText);
ctv.setName("Agus");
ctv.setBgColor(Color.parseColor("#FF5722"));
ctv.setTextColor(Color.WHITE);
ctv.setBorderWidthPx(getResources().getDisplayMetrics().density * 2); // 2dp
ctv.setBorderColor(Color.WHITE);
```

---

## Catatan implementasi
- View memaksa ukuran kotak pada onMeasure sehingga tetap segi empat sama sisi; lingkaran digambar di dalam kotak tersebut.
- textPaint.setTextSize diatur ke ~45% dari ukuran view agar proporsional. Ubah koefisien jika ingin ukuran teks berbeda.
- Untuk dukungan density-independen, gunakan dp/sp saat mengatur atribut di XML.
- Jika ingin menampilkan dua huruf inisial (mis. "AG"), ubah setName untuk mengambil dua karakter pertama dan kurangi ukuran teks agar muat.
- Untuk styling lebih lanjut (font custom), gunakan textPaint.setTypeface(...).

---
