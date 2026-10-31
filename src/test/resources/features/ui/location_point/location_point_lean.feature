# language: id
@ui @management @location-point @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman Location Point (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman titik lokasi presensi geofencing berfungsi dengan baik
  Agar batas radius presensi kehadiran karyawan dapat ditentukan secara presisi

  Latar Belakang:
    Dengan admin membuka halaman location point

  @LOC-UI-001
  Skenario: Membuka halaman location point dan verifikasi elemen antarmuka
    Maka halaman location point berhasil dimuat
    Dan judul halaman location point menampilkan "Location Point"
    Dan form pencarian location point terlihat
    Dan tombol Tambahkan lokasi terlihat
    Dan tabel data location point menampilkan 7 kolom header:
      | NO                      |
      | NAMA LOKASI             |
      | LATITUDE                |
      | LONGITUDE               |
      | MAKSIMAL RADIUS (METER) |
      | DESCRIPTION             |
      | AKSI                    |

  @LOC-UI-002
  Skenario: Melakukan pencarian titik lokasi presensi berdasarkan nama
    Ketika admin mencari lokasi presensi dengan kata kunci "Kantor Pusat"
    Maka tabel location point hanya menampilkan baris yang mengandung nama "Kantor Pusat"
    Ketika admin menekan tombol reset pencarian location point
    Maka seluruh data location point ditampilkan kembali di tabel

  @LOC-UI-003
  Skenario: Membuka modal tambah location point dan membatalkannya
    Ketika admin mengklik tombol Tambahkan lokasi
    Maka modal tambah lokasi ditampilkan dengan judul "Tambah Location Point"
    Dan field input latitude, longitude, dan radius terlihat pada modal tambah lokasi
    Ketika admin menekan tombol Batal pada modal tambah lokasi
    Maka modal tambah lokasi tertutup

  @LOC-UI-004
  Skenario: Membuka modal konfirmasi hapus lokasi dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama lokasi
    Maka modal konfirmasi hapus lokasi ditampilkan
    Dan pesan konfirmasi hapus lokasi memuat teks "Apakah anda ingin menghapus Location Point"
    Ketika admin menekan tombol Tidak pada modal hapus lokasi
    Maka modal konfirmasi hapus lokasi tertutup tanpa menghapus data

