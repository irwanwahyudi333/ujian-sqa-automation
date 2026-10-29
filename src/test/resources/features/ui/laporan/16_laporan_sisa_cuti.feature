# language: id
@ui @laporan @sisa-cuti @lean
Fitur: Pengujian Fitur Laporan Sisa Cuti — HADIR
  Sebagai Administrator atau Tim Human Resources
  Saya ingin memantau kuota hak cuti tahunan, cuti terpakai, dan sisa saldo berjalan karyawan
  Agar manajemen cuti karyawan transparan dan terdata secara teratur

  Latar Belakang:
    Dengan admin membuka halaman laporan sisa cuti

  @UI-SC-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Sisa Cuti
    Maka tabel data laporan sisa cuti terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter sisa cuti terlihat

  @DTR-SC-001
  Skenario: Penyaringan rekaman sisa cuti berdasarkan rentang tanggal
    Ketika admin memasukkan tanggal mulai sisa cuti "2026-10-20" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada sisa cuti
    Maka tabel sisa cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel sisa cuti menampilkan 10 baris data

  @DTR-SC-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada sisa cuti
    Ketika admin memasukkan tanggal mulai sisa cuti "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada sisa cuti
    Maka sistem menampilkan notifikasi snackbar error sisa cuti "Start date tidak boleh melebihi end date"

  @SRC-SC-001
  Skenario: Pencarian rekaman sisa cuti berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian sisa cuti "Irwan"
    Dan admin menekan tombol Search pada sisa cuti
    Maka tabel sisa cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel sisa cuti menampilkan 1 baris data

  @SRC-SC-002
  Skenario: Pembersihan kriteria filter dan pencarian sisa cuti melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian sisa cuti "Irwan"
    Dan admin menekan tombol Search pada sisa cuti
    Maka tabel sisa cuti menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada sisa cuti
    Maka seluruh data sisa cuti kembali ditampilkan pada tabel

  @FLT-SC-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen
    Ketika admin membuka modal filter sisa cuti
    Maka modal dialog filter sisa cuti terbuka pada layar
    Ketika admin memilih filter departemen sisa cuti "Quality Assurance"
    Dan admin menekan tombol Terapkan pada modal filter sisa cuti
    Maka tabel sisa cuti menampilkan 3 baris data

  @ACT-SC-001
  Skenario: Aksi sinkronisasi kuota cuti karyawan secara global
    Ketika admin menekan tombol Sync Kuota Cuti
    Maka sistem menampilkan notifikasi snackbar sukses sisa cuti "Berhasil menyinkronkan saldo kuota cuti seluruh karyawan"

  @PAG-SC-001
  Skenario: Kontrol jumlah baris per halaman pada tabel sisa cuti
    Ketika admin mengatur jumlah baris sisa cuti menjadi "5" per halaman
    Maka tabel sisa cuti menampilkan 5 baris data

  @EXP-SC-001
  Skenario: Ekspor berkas laporan sisa cuti ke format Excel
    Ketika admin menekan tombol Export sisa cuti
    Maka sistem menampilkan notifikasi snackbar sukses sisa cuti "Berhasil mengekspor Laporan Sisa Cuti ke Excel (.xlsx)"

