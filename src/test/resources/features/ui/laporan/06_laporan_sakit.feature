# language: id
@ui @laporan @sakit @lean
Fitur: Pengujian Fitur Laporan Sakit — HADIR
  Sebagai Administrator atau Tim HR Hadir
  Saya ingin memverifikasi izin sakit karyawan, validasi surat dokter, dan status konfirmasi medis
  Agar pencatatan ketidakhadiran karena kondisi medis tercatat rapi dan akurat

  Latar Belakang:
    Dengan admin membuka halaman laporan sakit

  @UI-SKT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Sakit
    Maka tabel data laporan sakit terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter sakit terlihat

  @DTR-SKT-001
  Skenario: Penyaringan rekaman sakit berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai sakit "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada sakit
    Maka tabel sakit menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel sakit menampilkan 3 baris data

  @DTR-SKT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada sakit
    Ketika admin memasukkan tanggal mulai sakit "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada sakit
    Maka sistem menampilkan notifikasi snackbar error sakit "Start date tidak boleh melebihi end date"

  @SRC-SKT-001
  Skenario: Pencarian rekaman sakit berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian sakit "Irwan"
    Dan admin menekan tombol Search pada sakit
    Maka tabel sakit menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel sakit menampilkan 1 baris data

  @SRC-SKT-002
  Skenario: Pembersihan kriteria filter dan pencarian sakit melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian sakit "Irwan"
    Dan admin menekan tombol Search pada sakit
    Maka tabel sakit menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada sakit
    Maka seluruh data sakit kembali ditampilkan pada tabel

  @FLT-SKT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status verifikasi
    Ketika admin membuka modal filter sakit
    Maka modal dialog filter sakit terbuka pada layar
    Ketika admin memilih filter departemen sakit "Quality Assurance"
    Dan admin memilih filter status verifikasi sakit "Verified"
    Dan admin menekan tombol Terapkan pada modal filter sakit
    Maka tabel sakit menampilkan 2 baris data

  @DTL-SKT-001
  Skenario: Peninjauan modal dialog detail izin sakit karyawan
    Ketika admin mengklik tombol detail sakit pada baris ke-1
    Maka modal dialog detail sakit terbuka pada layar
    Dan modal menampilkan judul detail sakit untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail sakit
    Maka tabel data laporan sakit terlihat pada halaman

  @PAG-SKT-001
  Skenario: Kontrol jumlah baris per halaman pada tabel sakit
    Ketika admin mengatur jumlah baris sakit menjadi "5" per halaman
    Maka tabel sakit menampilkan 5 baris data

  @EXP-SKT-001
  Skenario: Ekspor berkas laporan sakit ke format Excel
    Ketika admin menekan tombol Export sakit
    Maka sistem menampilkan notifikasi snackbar sukses sakit "Berhasil mengekspor Laporan Sakit ke Excel (.xlsx)"

  @BUG-SKT-001 @DEFECT-SKT-001 @live
  Skenario: Verifikasi integritas rendering gambar bukti sakit dan avatar pada tabel Laporan Sakit
    Maka tabel data laporan sakit terlihat pada halaman
    Dan seluruh gambar dan thumbnail bukti sakit berhasil dimuat dengan benar tanpa broken image

  @BUG-SKT-002 @DEFECT-SKT-002 @live
  Skenario: Verifikasi validitas tautan unduh berkas bukti surat sakit
    Maka tabel data laporan sakit terlihat pada halaman
    Dan seluruh tautan unduh berkas bukti sakit mengarah ke URL berkas dokumen yang valid


