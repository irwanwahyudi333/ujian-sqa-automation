# language: id
@ui @laporan @validation-overtime @lean
Fitur: Pengujian Fitur Laporan Validation Overtime — HADIR
  Sebagai Administrator atau Tim Verifikator Lembur Hadir
  Saya ingin meninjau pengajuan lembur, memvalidasi tahapan persetujuan multi-level, dan memberikan aksi persetujuan
  Agar akuntabilitas kompensasi kerja lembur karyawan tervalidasi dengan tepat

  Latar Belakang:
    Dengan admin membuka halaman laporan validation overtime

  @UI-VOT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Validation Overtime
    Maka tabel data laporan validation overtime terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter validation overtime terlihat

  @DTR-VOT-001
  Skenario: Penyaringan rekaman validasi lembur berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai validation overtime "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada validation overtime
    Maka tabel validation overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel validation overtime menampilkan 3 baris data

  @DTR-VOT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada validation overtime
    Ketika admin memasukkan tanggal mulai validation overtime "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada validation overtime
    Maka sistem menampilkan notifikasi snackbar error validation overtime "Start date tidak boleh melebihi end date"

  @SRC-VOT-001
  Skenario: Pencarian rekaman validasi lembur berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian validation overtime "Irwan"
    Dan admin menekan tombol Search pada validation overtime
    Maka tabel validation overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel validation overtime menampilkan 1 baris data

  @SRC-VOT-002
  Skenario: Pembersihan kriteria filter dan pencarian validation overtime melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian validation overtime "Irwan"
    Dan admin menekan tombol Search pada validation overtime
    Maka tabel validation overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada validation overtime
    Maka seluruh data validation overtime kembali ditampilkan pada tabel

  @FLT-VOT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status validasi
    Ketika admin membuka modal filter validation overtime
    Maka modal dialog filter validation overtime terbuka pada layar
    Ketika admin memilih filter departemen validation overtime "Quality Assurance"
    Dan admin memilih filter status validasi "Pending"
    Dan admin menekan tombol Terapkan pada modal filter validation overtime
    Maka tabel validation overtime menampilkan 1 baris data

  @ACT-VOT-001
  Skenario: Aksi persetujuan lembur pada baris pertama tabel validasi
    Ketika admin menekan tombol Approve lembur pada baris pertama
    Maka sistem menampilkan notifikasi snackbar sukses validation overtime "Lembur disetujui (Approved)"

  @PAG-VOT-001
  Skenario: Kontrol jumlah baris per halaman pada tabel validation overtime
    Ketika admin mengatur jumlah baris validation overtime menjadi "5" per halaman
    Maka tabel validation overtime menampilkan 5 baris data

  @EXP-VOT-001
  Skenario: Ekspor berkas laporan validation overtime ke format Excel
    Ketika admin menekan tombol Export validation overtime
    Maka sistem menampilkan notifikasi snackbar sukses validation overtime "Berhasil mengekspor Laporan Validasi Overtime ke Excel (.xlsx)"

