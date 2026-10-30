# language: id
@ui @management @user-monitoring @lean
Fitur: Verifikasi Alur Kritis Antarmuka Halaman User Monitoring (Lean UI)
  Sebagai administrator HADIR
  Saya ingin memastikan elemen visual dan alur kritis interaksi halaman pemantauan pengguna berfungsi dengan baik
  Agar pengawasan keaktifan dan lokasi presensi karyawan di lapangan dapat dilakukan secara akurat

  Latar Belakang:
    Dengan admin membuka halaman user monitoring

  @UMON-UI-001
  Skenario: Membuka halaman user monitoring dan verifikasi elemen utama antarmuka
    Maka halaman user monitoring berhasil dimuat
    Dan judul halaman user monitoring menampilkan "User Monitoring"
    Dan form pencarian user monitoring terlihat
    Dan tombol Tambahkan monitoring terlihat
    Dan tabel data user monitoring menampilkan 8 kolom header:
      | NO               |
      | FOTO             |
      | NAMA KARYAWAN    |
      | NIK              |
      | TERAKHIR AKTIF   |
      | LOKASI GEOFENCE  |
      | STATUS           |
      | AKSI             |

  @UMON-UI-002
  Skenario: Melakukan pencarian user monitoring dengan nama yang valid
    Ketika admin mencari user monitoring dengan kata kunci "Irwan"
    Maka tabel user monitoring hanya menampilkan baris yang mengandung nama "Irwan"
    Ketika admin menekan tombol reset pencarian monitoring
    Maka seluruh data user monitoring ditampilkan kembali di tabel

  @UMON-UI-003
  Skenario: Membuka modal tambah user monitoring dan membatalkannya
    Ketika admin mengklik tombol Tambahkan monitoring
    Maka modal tambah user monitoring ditampilkan dengan judul "Tambah User Monitoring"
    Ketika admin menekan tombol Batal pada modal tambah monitoring
    Maka modal tambah user monitoring tertutup

  @UMON-UI-004
  Skenario: Membuka modal hapus monitoring dan membatalkan penghapusan
    Ketika admin mengklik tombol Hapus pada baris pertama monitoring
    Maka modal konfirmasi hapus monitoring ditampilkan
    Dan pesan konfirmasi memuat teks "Apakah anda ingin menghapus monitoring user"
    Ketika admin menekan tombol Tidak pada modal hapus monitoring
    Maka modal konfirmasi hapus monitoring tertutup tanpa menghapus data

