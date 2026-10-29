# language: id
@ui @laporan @koreksi @lean
Fitur: Pengujian Fitur Laporan Koreksi Absensi — HADIR
  Sebagai Administrator atau Atasan Langsung (Upliner)
  Saya ingin meninjau permohonan perbaikan jam absensi dan memproses persetujuannya
  Agar rekaman jam kerja karyawan akurat dan terdokumentasi dengan transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan koreksi absensi

  @UI-KRK-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Koreksi
    Maka tabel data laporan koreksi terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter koreksi terlihat

  @DTR-KRK-001
  Skenario: Penyaringan rekaman koreksi absensi berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai koreksi "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada koreksi
    Maka tabel koreksi menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel koreksi menampilkan 3 baris data

  @DTR-KRK-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada koreksi absensi
    Ketika admin memasukkan tanggal mulai koreksi "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada koreksi
    Maka sistem menampilkan notifikasi snackbar error koreksi "Start date tidak boleh melebihi end date"

  @SRC-KRK-001
  Skenario: Pencarian rekaman koreksi berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian koreksi "Irwan"
    Dan admin menekan tombol Search pada koreksi
    Maka tabel koreksi menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel koreksi menampilkan 1 baris data

  @SRC-KRK-002
  Skenario: Pembersihan kriteria filter dan pencarian koreksi melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian koreksi "Irwan"
    Dan admin menekan tombol Search pada koreksi
    Maka tabel koreksi menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada koreksi
    Maka seluruh data koreksi kembali ditampilkan pada tabel

  @FLT-KRK-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan tipe koreksi
    Ketika admin membuka modal filter koreksi
    Maka modal dialog filter koreksi terbuka pada layar
    Ketika admin memilih filter departemen koreksi "Quality Assurance"
    Dan admin memilih filter tipe koreksi "Keluar"
    Dan admin menekan tombol Terapkan pada modal filter koreksi
    Maka tabel koreksi menampilkan 2 baris data

  @ACT-KRK-001
  Skenario: Aksi persetujuan permohonan koreksi absensi pada baris pertama tabel
    Ketika admin menekan tombol Approve koreksi jam pada baris pertama
    Maka sistem menampilkan notifikasi snackbar sukses koreksi "Koreksi jam disetujui"

  @PAG-KRK-001
  Skenario: Kontrol jumlah baris per halaman pada tabel koreksi
    Ketika admin mengatur jumlah baris koreksi menjadi "5" per halaman
    Maka tabel koreksi menampilkan 5 baris data

  @EXP-KRK-001
  Skenario: Ekspor berkas laporan koreksi absensi ke format Excel
    Ketika admin menekan tombol Export koreksi
    Maka sistem menampilkan notifikasi snackbar sukses koreksi "Berhasil mengekspor Laporan Koreksi ke Excel (.xlsx)"

