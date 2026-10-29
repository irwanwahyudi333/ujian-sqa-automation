# language: id
@ui @laporan @shifting @lean
Fitur: Pengujian Fitur Laporan Shifting — HADIR
  Sebagai Administrator atau Tim HR Operasional Hadir
  Saya ingin memantau kehadiran karyawan shift, kepatuhan jam kerja, dan alokasi pola shift
  Agar operasional rotasi shift berjalan tertib dan terdata secara transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan shifting

  @UI-SFT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Shifting
    Maka tabel data laporan shifting terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter shifting terlihat

  @DTR-SFT-001
  Skenario: Penyaringan rekaman shift berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai shifting "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada shifting
    Maka tabel shifting menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel shifting menampilkan 3 baris data

  @DTR-SFT-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada shifting
    Ketika admin memasukkan tanggal mulai shifting "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada shifting
    Maka sistem menampilkan notifikasi snackbar error shifting "Start date tidak boleh melebihi end date"

  @SRC-SFT-001
  Skenario: Pencarian rekaman shift berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian shifting "Irwan"
    Dan admin menekan tombol Search pada shifting
    Maka tabel shifting menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel shifting menampilkan 1 baris data

  @SRC-SFT-002
  Skenario: Pembersihan kriteria filter dan pencarian shifting melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian shifting "Irwan"
    Dan admin menekan tombol Search pada shifting
    Maka tabel shifting menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada shifting
    Maka seluruh data shifting kembali ditampilkan pada tabel

  @FLT-SFT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan pola shift
    Ketika admin membuka modal filter shifting
    Maka modal dialog filter shifting terbuka pada layar
    Ketika admin memilih filter departemen shifting "Quality Assurance"
    Dan admin memilih filter pola shift "Shift Pagi"
    Dan admin menekan tombol Terapkan pada modal filter shifting
    Maka tabel shifting menampilkan 1 baris data

  @DTL-SFT-001
  Skenario: Peninjauan modal dialog detail shifting karyawan
    Ketika admin mengklik tombol detail shifting pada baris ke-1
    Maka modal dialog detail shifting terbuka pada layar
    Dan modal menampilkan judul detail shifting untuk "Irwan Wahyudi"
    Ketika admin menutup modal detail shifting
    Maka tabel data laporan shifting terlihat pada halaman

  @PAG-SFT-001
  Skenario: Kontrol jumlah baris per halaman pada tabel shifting
    Ketika admin mengatur jumlah baris shifting menjadi "5" per halaman
    Maka tabel shifting menampilkan 5 baris data

  @EXP-SFT-001
  Skenario: Ekspor berkas laporan shifting ke format Excel
    Ketika admin menekan tombol Export shifting
    Maka sistem menampilkan notifikasi snackbar sukses shifting "Berhasil mengekspor Laporan Shifting ke Excel (.xlsx)"

