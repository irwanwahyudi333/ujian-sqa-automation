# language: id
@ui @laporan @cuti @lean
Fitur: Pengujian Fitur Laporan Cuti — HADIR
  Sebagai Administrator atau Tim HR Hadir
  Saya ingin memantau dan memvalidasi permohonan hak cuti karyawan, durasi hari kerja, dan approval Client V2
  Agar alokasi ketidakhadiran dan hak cuti tahunan karyawan terkelola dengan baik

  Latar Belakang:
    Dengan admin membuka halaman laporan cuti

  @UI-CUTI-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Cuti
    Maka tabel data laporan cuti terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter cuti terlihat

  @DTR-CUTI-001
  Skenario: Penyaringan rekaman cuti berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai cuti "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada cuti
    Maka tabel cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel cuti menampilkan 3 baris data

  @DTR-CUTI-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada cuti
    Ketika admin memasukkan tanggal mulai cuti "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada cuti
    Maka sistem menampilkan notifikasi snackbar error cuti "Start date tidak boleh melebihi end date"

  @SRC-CUTI-001
  Skenario: Pencarian rekaman cuti berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian cuti "Irwan"
    Dan admin menekan tombol Search pada cuti
    Maka tabel cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel cuti menampilkan 1 baris data

  @SRC-CUTI-002
  Skenario: Pembersihan kriteria filter dan pencarian cuti melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian cuti "Irwan"
    Dan admin menekan tombol Search pada cuti
    Maka tabel cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada cuti
    Maka seluruh data cuti kembali ditampilkan pada tabel

  @FLT-CUTI-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan tipe cuti
    Ketika admin membuka modal filter cuti
    Maka modal dialog filter cuti terbuka pada layar
    Ketika admin memilih filter departemen cuti "Quality Assurance"
    Dan admin memilih filter tipe cuti "Cuti Tahunan"
    Dan admin menekan tombol Terapkan pada modal filter cuti
    Maka tabel cuti menampilkan 2 baris data

  @DTL-CUTI-001
  Skenario: Peninjauan modal dialog detail cuti karyawan
    Ketika admin mengklik tombol detail cuti pada baris ke-1
    Maka modal dialog detail cuti terbuka pada layar
    Dan modal menampilkan judul detail cuti untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail cuti
    Maka tabel data laporan cuti terlihat pada halaman

  @PAG-CUTI-001
  Skenario: Kontrol jumlah baris per halaman pada tabel cuti
    Ketika admin mengatur jumlah baris cuti menjadi "5" per halaman
    Maka tabel cuti menampilkan 5 baris data

  @EXP-CUTI-001
  Skenario: Ekspor berkas laporan cuti ke format Excel
    Ketika admin menekan tombol Export cuti
    Maka sistem menampilkan notifikasi snackbar sukses cuti "Berhasil mengekspor Laporan Cuti ke Excel (.xlsx)"

  @BUG-CUTI-001 @DEFECT-CUTI-001 @live
  Skenario: Verifikasi ketersediaan kontrol form dan fungsi tombol Ubah Data pada modal ubah atasan cuti
    Ketika admin membuka menu aksi baris pertama dan memilih Edit pada cuti
    Maka sistem menampilkan modal dialog ubah atasan cuti "Ubah atasan user yang request"
    Dan modal cuti memuat kontrol dropdown pilihan atasan yang dapat diubah oleh admin
    Ketika admin menekan tombol Ubah Data pada modal cuti
    Maka seluruh data cuti kembali ditampilkan pada tabel


