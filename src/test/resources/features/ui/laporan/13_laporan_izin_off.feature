# language: id
@ui @laporan @izin-off @lean
Fitur: Pengujian Fitur Laporan Izin Off — HADIR
  Sebagai Administrator atau Supervisor Tim Shift
  Saya ingin mengelola permohonan penyesuaian jadwal libur (day-off swap) dan tanggal pengganti
  Agar operasional rotasi shift tetap berjalan optimal dan transparan

  Latar Belakang:
    Dengan admin membuka halaman laporan izin off

  @UI-OFF-001 @smoke
  Skenario: Verifikasi ketersediaan elemen utama antarmuka halaman Laporan Izin Off
    Maka tabel data laporan izin off terlihat pada halaman
    Dan elemen kontrol tanggal, pencarian, dan tombol filter izin off terlihat

  @DTR-OFF-001
  Skenario: Penyaringan rekaman izin off berdasarkan rentang tanggal presisi
    Ketika admin memasukkan tanggal mulai izin off "2026-10-26" dan tanggal akhir "2026-10-26"
    Dan admin menekan tombol Search pada izin off
    Maka tabel izin off menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin off menampilkan 3 baris data

  @DTR-OFF-002 @negative
  Skenario: Penolakan input logika tanggal terbalik pada izin off
    Ketika admin memasukkan tanggal mulai izin off "2026-10-26" dan tanggal akhir "2026-10-20"
    Dan admin menekan tombol Search pada izin off
    Maka sistem menampilkan notifikasi snackbar error izin off "Start date tidak boleh melebihi end date"

  @SRC-OFF-001
  Skenario: Pencarian rekaman izin off berdasarkan nama karyawan
    Ketika admin memasukkan kata kunci pencarian izin off "Irwan"
    Dan admin menekan tombol Search pada izin off
    Maka tabel izin off menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Dan tabel izin off menampilkan 1 baris data

  @SRC-OFF-002
  Skenario: Pembersihan kriteria filter dan pencarian izin off melalui tombol Reset
    Ketika admin memasukkan kata kunci pencarian izin off "Irwan"
    Dan admin menekan tombol Search pada izin off
    Maka tabel izin off menampilkan rekaman untuk karyawan "Irwan Wahyudi"
    Ketika admin menekan tombol Reset pada izin off
    Maka seluruh data izin off kembali ditampilkan pada tabel

  @FLT-OFF-001
  Skenario: Penyaringan laporan multi-kriteria via modal filter departemen dan status persetujuan
    Ketika admin membuka modal filter izin off
    Maka modal dialog filter izin off terbuka pada layar
    Ketika admin memilih filter departemen izin off "Quality Assurance"
    Dan admin memilih filter status persetujuan izin off "Approved"
    Dan admin menekan tombol Terapkan pada modal filter izin off
    Maka tabel izin off menampilkan 2 baris data

  @ACT-OFF-001
  Skenario: Aksi persetujuan permohonan izin off pada baris pertama tabel
    Ketika admin menekan tombol Approve izin off pada baris pertama
    Maka sistem menampilkan notifikasi snackbar sukses izin off "Izin off disetujui"

  @PAG-OFF-001
  Skenario: Kontrol jumlah baris per halaman pada tabel izin off
    Ketika admin mengatur jumlah baris izin off menjadi "5" per halaman
    Maka tabel izin off menampilkan 5 baris data

  @EXP-OFF-001
  Skenario: Ekspor berkas laporan izin off ke format Excel
    Ketika admin menekan tombol Export izin off
    Maka sistem menampilkan notifikasi snackbar sukses izin off "Berhasil mengekspor Laporan Izin Off ke Excel (.xlsx)"

  @BUG-OFF-001 @DEFECT-OFF-001 @live
  Skenario: Verifikasi ketepatan label konteks pada menu aksi tabel Laporan Izin Off
    Ketika admin mengklik tombol aksi pada baris pertama tabel izin off
    Maka menu popover aksi tidak memuat label copy-paste "Approval Koreksi" atau "Reject Koreksi"


