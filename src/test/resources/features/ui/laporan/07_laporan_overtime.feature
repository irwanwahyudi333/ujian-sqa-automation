# language: id
@ui @laporan @overtime @lean
Fitur: Pengujian Fitur Laporan Overtime — HADIR
  Sebagai Administrator atau Tim HR Hadir
  Saya ingin memantau dan memvalidasi rekaman jam kerja lembur karyawan, uraian tugas lembur, dan status persetujuan
  Agar alokasi kompensasi lembur dan jam kerja tambahan karyawan terkelola secara adil dan transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan overtime

  @UI-OT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Overtime
    Maka tabel data laporan overtime terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter overtime terlihat

  @DTR-OT-001
  Skenario: Penyaringan rekaman lembur berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai overtime "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada overtime
    Maka tabel overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel overtime menampilkan 3 baris data

  @DTR-OT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada overtime
    Ketika admin memasukkan tanggal mulai overtime "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada overtime
    Maka sistem menampilkan notifikasi snackbar error overtime "Start date tidak boleh melebihi end date"

  @SRC-OT-001
  Skenario: Pencarian rekaman lembur berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian overtime "Irwan"
    Dan admin menekan tombol Search pada overtime
    Maka tabel overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel overtime menampilkan 1 baris data

  @SRC-OT-002
  Skenario: Pembersihan kriteria filter dan pencarian overtime melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian overtime "Irwan"
    Dan admin menekan tombol Search pada overtime
    Maka tabel overtime menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada overtime
    Maka seluruh data overtime kembali ditampilkan pada tabel

  @FLT-OT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status lembur
    Ketika admin membuka modal filter overtime
    Maka modal dialog filter overtime terbuka pada layar
    Ketika admin memilih filter departemen overtime "Quality Assurance"
    Dan admin memilih filter status lembur "Approved"
    Dan admin menekan tombol Terapkan pada modal filter overtime
    Maka tabel overtime menampilkan 3 baris data

  @DTL-OT-001
  Skenario: Peninjauan modal dialog detail lembur karyawan
    Ketika admin mengklik tombol detail overtime pada baris ke-1
    Maka modal dialog detail overtime terbuka pada layar
    Dan modal menampilkan judul detail lembur untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail overtime
    Maka tabel data laporan overtime terlihat pada halaman

  @PAG-OT-001
  Skenario: Kontrol jumlah baris per halaman pada tabel overtime
    Ketika admin mengatur jumlah baris overtime menjadi "5" per halaman
    Maka tabel overtime menampilkan 5 baris data

  @EXP-OT-001
  Skenario: Ekspor berkas laporan overtime ke format Excel
    Ketika admin menekan tombol Export overtime
    Maka sistem menampilkan notifikasi snackbar sukses overtime "Berhasil mengekspor Laporan Overtime ke Excel (.xlsx)"

  @BUG-OT-001 @DEFECT-OT-001 @live
  Skenario: Verifikasi ketersediaan kontrol form dan fungsi tombol Ubah Data pada modal ubah atasan
    Ketika admin mengklik tombol aksi ubah data pada baris pertama tabel overtime
    Maka sistem menampilkan modal dialog ubah atasan "Ubah atasan user yang request"
    Dan modal memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin
    Ketika admin menekan tombol Ubah Data pada modal ubah atasan
    Maka seluruh data overtime kembali ditampilkan pada tabel


