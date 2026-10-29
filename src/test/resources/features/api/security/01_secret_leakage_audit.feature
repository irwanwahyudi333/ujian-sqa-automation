# language: id
@api @security @secret-leakage
Fitur: Audit Keamanan Kebocoran Data Rahasia dan Kredensial Sisi Klien
  Sebagai Security QA Engineer
  Saya ingin memverifikasi bahwa tidak ada rahasia server, kunci enkripsi, atau kredensial yang terekspos ke publik
  Agar sistem terlindungi dari pemalsuan sesi, pembajakan akun, dan penyalahgunaan infrastruktur

  @TC-SEC-LEAK-01 @critical @cwe-798 @cwe-312
  Skenario: TC-SEC-LEAK-01 Verifikasi ketiadaan kata sandi enkripsi sesi pada bundel JavaScript frontend
    Ketika client mengunduh dan menganalisis seluruh bundel JavaScript frontend
    Maka bundel JavaScript tidak boleh memuat kata kunci "SESSION_PASSWORD"
    Dan bundel JavaScript tidak boleh memuat secret key enkripsi sesi hardcoded

  @TC-SEC-LEAK-02 @high @cwe-200
  Skenario: TC-SEC-LEAK-02 Verifikasi pembatasan domain pada Google Cloud Platform Maps API Key
    Ketika client mengirim request ke Google Maps API menggunakan key "AIzaSyApRe1R2n-1TEgk5s0Eq0HRZDKo7WzBEWE" dengan referer tidak terdaftar "https://unauthorized-attacker-domain.com"
    Maka Google API harus menolak request dan melarang akses dari domain luar

  @TC-SEC-LEAK-03 @critical @cwe-798 @cwe-312
  Skenario: TC-SEC-LEAK-03 Verifikasi integritas sesi dan pencegahan pemalsuan cookie sesi dengan secret key bocor
    Ketika client menguji integritas konfigurasi enkripsi cookie sesi sisi klien
    Maka sistem tidak boleh mengekspos secret key enkripsi sesi pada chunk klien

  @TC-SEC-LEAK-04 @high @cwe-613
  Skenario: TC-SEC-LEAK-04 Verifikasi masa aktif token JWT autentikasi tidak melebihi batas toleransi keamanan
    Ketika client menganalisis durasi masa berlaku token JWT dari endpoint autentikasi
    Maka durasi kedaluwarsa token JWT tidak boleh melebihi batas maksimal 86400 detik

  @TC-SEC-LEAK-05 @medium @cwe-200 @cwe-639
  Skenario: TC-SEC-LEAK-05 Verifikasi perlindungan akses terhadap berkas foto selfie pada storage backend
    Ketika client mengirim request GET ke endpoint upload berkas selfie tanpa menyertakan token otorisasi
    Maka sistem backend harus menolak akses atau menerapkan proteksi otorisasi

  @TC-SEC-LEAK-06 @high @cwe-798 @cwe-259
  Skenario: TC-SEC-LEAK-06 Verifikasi ketiadaan kredensial akun dan password plaintext pada template autentikasi statis
    Ketika client menganalisis file template autentikasi login HTML
    Maka file template tidak boleh memuat kredensial akun dan password plaintext secara hardcoded

  @TC-SEC-LEAK-07 @high @cwe-922 @cwe-312
  Skenario: TC-SEC-LEAK-07 Verifikasi ketiadaan penyimpanan token autentikasi pada Web Storage sisi klien
    Ketika client menganalisis mekanisme penyimpanan sesi pada berkas skrip autentikasi login
    Maka skrip autentikasi tidak boleh menyimpan token akses pada localStorage atau sessionStorage

