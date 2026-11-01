# language: id
@ui @leader-izin-off @izin-off @delete
Fitur: Manajemen Aksi Pembatalan dan Penutupan Dialog Izin Off
  Sebagai Leader
  Saya ingin memastikan seluruh alur pembatalan, penutupan dialog, dan integritas data terjaga
  Agar tidak terjadi perubahan status yang tidak disengaja

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @DEL-001
  Skenario: Pembatalan approval berkali-kali tanpa memengaruhi status data
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol Batal pada modal approval leader izin off
    Dan leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader menekan tombol Batal pada modal approval leader izin off
    Maka seluruh data permohonan di tabel leader izin off tetap konsisten

  @DEL-002
  Skenario: Penutupan modal detail izin off via tombol Tutup
    Ketika leader menekan tombol Detail pada baris permohonan pertama
    Dan leader menekan tombol Tutup pada modal detail leader izin off
    Maka modal dialog Detail Izin Off tertutup

  @DEL-003
  Skenario: Penutupan modal detail izin off via tombol silang (X)
    Ketika leader menekan tombol Detail pada baris permohonan pertama
    Dan leader menekan tombol silang tutup pada modal detail leader izin off
    Maka modal dialog Detail Izin Off tertutup

  @DEL-004
  Skenario: Penutupan modal detail izin off via tombol keyboard Escape
    Ketika leader menekan tombol Detail pada baris permohonan pertama
    Dan leader menekan tombol keyboard Escape pada leader izin off
    Maka modal dialog Detail Izin Off tertutup

  @DEL-005
  Skenario: Verifikasi catatan penolakan pada modal detail untuk permohonan REJECTED
    Ketika leader menekan tombol Detail pada permohonan karyawan "Dedi Irawan"
    Maka catatan penolakan pada modal detail menampilkan alasan penolakan

  @DEL-006
  Skenario: Catatan penolakan tidak ditampilkan pada modal detail permohonan APPROVED
    Ketika leader menekan tombol Detail pada permohonan karyawan "Citra Dewi"
    Maka kotak catatan penolakan pada modal detail tersembunyi

  @DEL-007
  Skenario: Pembatalan pengajuan izin off baru berkali-kali
    Ketika leader menekan tombol Ajukan Izin Off
    Dan leader menekan tombol Batal pada modal pengajuan leader izin off
    Dan leader menekan tombol Ajukan Izin Off
    Dan leader menekan tombol Batal pada modal pengajuan leader izin off
    Maka tabel leader izin off tetap utuh tanpa data sampah

  @DEL-008
  Skenario: Penutupan toast notifikasi secara manual via tombol silang
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Dan leader menekan tombol silang tutup pada toast notifikasi leader izin off
    Maka toast notifikasi leader izin off tertutup seketika

  @DEL-009
  Skenario: Memastikan hidden input ID terisi sesuai baris permohonan
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Maka hidden input ID pada modal approval memiliki nilai ID yang valid

  @DEL-010
  Skenario: Memastikan hidden input type bernilai approve saat menyetujui
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Maka hidden input type pada modal approval bernilai "approve"

  @DEL-011
  Skenario: Memastikan hidden input type bernilai reject saat menolak
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Maka hidden input type pada modal approval bernilai "reject"

  @DEL-012
  Skenario: Reset isi textarea alasan penolakan saat modal dibuka kembali
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader mengisi alasan penolakan "Alasan sementara yang dibatalkan"
    Dan leader menekan tombol Batal pada modal approval leader izin off
    Dan leader menekan tombol Tolak pada baris permohonan pertama
    Maka textarea alasan penolakan dalam kondisi kosong bersih
