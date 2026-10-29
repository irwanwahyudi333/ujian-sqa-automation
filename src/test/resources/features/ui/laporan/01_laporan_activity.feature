# language: id
@ui @laporan @activity @lean
Fitur: Pengujian Fitur Laporan Activity (Agenda & Aktivitas Kerja) — HADIR
  Sebagai Administrator atau Tim HR/Supervisor
  Saya ingin memantau dan memvalidasi rekaman kegiatan harian, tipe aktivitas, waktu pelaksanaan, serta mengekspor data agenda kerja karyawan
  Agar pencatatan tugas dan produktivitas karyawan terdokumentasi rapi dan akurat

  Latar Belakang:
    Dengan admin membuka halaman laporan activity

  @UI-ACT-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Activity
    Maka tabel data laporan activity terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter activity terlihat

  @DTR-ACT-001
  Skenario: Penyaringan rekaman aktivitas berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai activity "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada activity
    Maka tabel activity menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel activity menampilkan 3 baris data

  @DTR-ACT-003 @negative
  Skenario: Penolakan input logika tanggal terbalik (Start Date lebih besar dari End Date)
    Ketika admin memasukkan tanggal mulai activity "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada activity
    Maka sistem menampilkan notifikasi snackbar error activity "Start date tidak boleh melebihi end date"

  @SRC-ACT-001
  Skenario: Pencarian rekaman aktivitas berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian activity "Irwan"
    Dan admin menekan tombol Search pada activity
    Maka tabel activity menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel activity menampilkan 1 baris data

  @SRC-ACT-008
  Skenario: Pembersihan kriteria filter dan pencarian melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian activity "Ahmad"
    Dan admin menekan tombol Search pada activity
    Maka tabel activity menampilkan rekaman untuk karyawan "Ahmad Subagyo"
    Ketika admin menekan tombol Reset pada activity
    Maka seluruh data aktivitas kembali ditampilkan pada tabel

  @FLT-ACT-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan tipe aktivitas
    Ketika admin membuka modal filter activity
    Maka modal dialog filter activity terbuka pada layar
    Ketika admin memilih filter departemen activity "Quality Assurance"
    Dan admin memilih filter tipe aktivitas "Task / Pekerjaan Harian"
    Dan admin menekan tombol Terapkan pada modal filter activity
    Maka tabel activity menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel activity menampilkan 3 baris data

  @DTL-ACT-001
  Skenario: Peninjauan modal dialog detail aktivitas kegiatan karyawan
    Ketika admin mengklik tombol detail activity pada baris ke-1
    Maka modal dialog detail activity terbuka pada layar
    Dan modal menampilkan nama karyawan "Irwan Wahyudi" dan tipe aktivitas "Task"
    Ketika admin menutup modal detail activity
    Maka tabel data laporan activity terlihat pada halaman

  @EXP-ACT-001
  Skenario: Pengunduhan berkas rekapitulasi laporan activity
    Ketika admin menekan tombol Export activity
    Maka sistem menampilkan notifikasi snackbar sukses activity "Berhasil mengekspor Laporan Activity ke Excel (.xlsx)"

  @PAG-ACT-001
  Skenario: Pengaturan jumlah baris per halaman dan navigasi paginasi
    Ketika admin mengubah jumlah baris per halaman activity menjadi "5"
    Dan admin menekan tombol halaman berikutnya pada activity
    Maka informasi paginasi activity menampilkan "Menampilkan 6-10 dari 15 data"

