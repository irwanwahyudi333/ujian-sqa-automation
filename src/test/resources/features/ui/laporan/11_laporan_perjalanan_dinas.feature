# language: id
@ui @laporan @perjalanan-dinas @lean
Fitur: Pengujian Fitur Laporan Perjalanan Dinas — HADIR
  Sebagai Administrator atau Tim Operasional Hadir
  Saya ingin mengelola surat tugas dinas, anggaran operasional luar kantor, dan status persetujuan dinas
  Agar akuntabilitas pelaksanaan dinas luar kota karyawan tercatat transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan perjalanan dinas

  @UI-DNS-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Perjalanan Dinas
    Maka tabel data laporan perjalanan dinas terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter perjalanan dinas terlihat

  @DTR-DNS-001
  Skenario: Penyaringan rekaman dinas berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai perjalanan dinas "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada perjalanan dinas
    Maka tabel perjalanan dinas menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel perjalanan dinas menampilkan 3 baris data

  @DTR-DNS-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada perjalanan dinas
    Ketika admin memasukkan tanggal mulai perjalanan dinas "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada perjalanan dinas
    Maka sistem menampilkan notifikasi snackbar error perjalanan dinas "Start date tidak boleh melebihi end date"

  @SRC-DNS-001
  Skenario: Pencarian rekaman dinas berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian perjalanan dinas "Irwan"
    Dan admin menekan tombol Search pada perjalanan dinas
    Maka tabel perjalanan dinas menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel perjalanan dinas menampilkan 1 baris data

  @SRC-DNS-002
  Skenario: Pembersihan kriteria filter dan pencarian dinas melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian perjalanan dinas "Irwan"
    Dan admin menekan tombol Search pada perjalanan dinas
    Maka tabel perjalanan dinas menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada perjalanan dinas
    Maka seluruh data perjalanan dinas kembali ditampilkan pada tabel

  @FLT-DNS-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status approval
    Ketika admin membuka modal filter perjalanan dinas
    Maka modal dialog filter perjalanan dinas terbuka pada layar
    Ketika admin memilih filter departemen perjalanan dinas "Quality Assurance"
    Dan admin memilih filter status approval dinas "Approved"
    Dan admin menekan tombol Terapkan pada modal filter perjalanan dinas
    Maka tabel perjalanan dinas menampilkan 2 baris data

  @DTL-DNS-001
  Skenario: Peninjauan modal dialog detail perjalanan dinas karyawan
    Ketika admin mengklik tombol detail perjalanan dinas pada baris ke-1
    Maka modal dialog detail perjalanan dinas terbuka pada layar
    Dan modal menampilkan judul detail perjalanan dinas untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail perjalanan dinas
    Maka tabel data laporan perjalanan dinas terlihat pada halaman

  @PAG-DNS-001
  Skenario: Kontrol jumlah baris per halaman pada tabel perjalanan dinas
    Ketika admin mengatur jumlah baris perjalanan dinas menjadi "5" per halaman
    Maka tabel perjalanan dinas menampilkan 5 baris data

  @EXP-DNS-001
  Skenario: Ekspor berkas laporan perjalanan dinas ke format Excel
    Ketika admin menekan tombol Export perjalanan dinas
    Maka sistem menampilkan notifikasi snackbar sukses perjalanan dinas "Berhasil mengekspor Laporan Perjalanan Dinas ke Excel (.xlsx)"

  @BUG-DNS-001 @DEFECT-DNS-001 @live
  Skenario: Verifikasi fungsionalitas eksekusi tombol konfirmasi Approval pada Laporan Perjalanan Dinas
    Ketika admin mengklik tombol aksi kebab pada baris pertama perjalanan dinas
    Dan admin memilih opsi Approval Perjalanan Dinas pada menu aksi
    Maka sistem menampilkan dialog konfirmasi persetujuan "Approval User"
    Ketika admin menekan tombol Ya pada dialog konfirmasi perjalanan dinas
    Maka sistem memproses persetujuan dinas dan menutup dialog konfirmasi

  @BUG-DNS-002 @DEFECT-DNS-002 @live
  Skenario: Verifikasi fungsionalitas eksekusi tombol konfirmasi Reject pada Laporan Perjalanan Dinas
    Ketika admin mengklik tombol aksi kebab pada baris pertama perjalanan dinas
    Dan admin memilih opsi Reject Perjalanan Dinas pada menu aksi
    Maka sistem menampilkan dialog konfirmasi penolakan "Reject User"
    Ketika admin menekan tombol Ya pada dialog konfirmasi perjalanan dinas
    Maka sistem memproses penolakan dinas dan menutup dialog konfirmasi


