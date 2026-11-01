# language: id
@ui @leader-izin-off @izin-off @edit
Fitur: Proses Persetujuan (Approval) dan Penolakan (Reject) Izin Off
  Sebagai Leader
  Saya ingin dapat menyetujui atau menolak permohonan izin off anggota tim secara akurat
  Agar status kehadiran dan hak libur karyawan tercatat sesuai kebijakan perusahaan

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @EDT-001
  Skenario: Membuka modal persetujuan izin off via tombol Setujui
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Maka modal dialog Approval Modal Izin Off terbuka

  @EDT-002
  Skenario: Verifikasi teks konfirmasi pada modal persetujuan
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Maka teks konfirmasi approval menampilkan "Apakah anda ingin menyetujui permintaan request izin off?"

  @EDT-003
  Skenario: Menyetujui permohonan izin off berstatus PENDING
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Berhasil menyetujui izin off"

  @EDT-004
  Skenario: Verifikasi perubahan status badge menjadi APPROVED setelah disetujui
    Ketika leader menekan tombol Setujui pada permohonan karyawan "Budi Santoso"
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka status permohonan "Budi Santoso" di tabel berubah menjadi "APPROVED"

  @EDT-005
  Skenario: Membuka modal penolakan izin off via tombol Tolak
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Maka modal dialog Approval Modal Izin Off terbuka
    Dan textarea alasan penolakan izin off ditampilkan

  @EDT-006
  Skenario: Verifikasi teks konfirmasi pada modal penolakan
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Maka teks konfirmasi approval menampilkan "Apakah anda ingin menolak permintaan request izin off?"

  @EDT-007
  Skenario: Verifikasi textarea alasan penolakan wajib diisi
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Maka textarea alasan penolakan berstatus wajib diisi

  @EDT-008
  Skenario: Validasi submit tolak tanpa mengisi alasan penolakan
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader mengosongkan alasan penolakan izin off
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka modal dialog Approval Modal Izin Off tetap terbuka

  @EDT-009
  Skenario: Menolak permohonan izin off dengan alasan yang valid
    Ketika leader menekan tombol Tolak pada permohonan karyawan "Eka Permata"
    Dan leader mengisi alasan penolakan "Shift operasional sedang padat kegiatan"
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Berhasil menolak izin off"

  @EDT-010
  Skenario: Verifikasi perubahan status badge menjadi REJECTED setelah ditolak
    Ketika leader menekan tombol Tolak pada permohonan karyawan "Gita Safitri"
    Dan leader mengisi alasan penolakan "Kuota libur tim minggu ini sudah penuh"
    Dan leader menekan tombol konfirmasi pada modal approval leader izin off
    Maka status permohonan "Gita Safitri" di tabel berubah menjadi "REJECTED"

  @EDT-011
  Skenario: Batalkan persetujuan permohonan via tombol Batal
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol Batal pada modal approval leader izin off
    Maka modal dialog Approval Modal Izin Off tertutup seketika

  @EDT-012
  Skenario: Batalkan penolakan permohonan via tombol Batal
    Ketika leader menekan tombol Tolak pada baris permohonan pertama
    Dan leader menekan tombol Batal pada modal approval leader izin off
    Maka modal dialog Approval Modal Izin Off tertutup seketika

  @EDT-013
  Skenario: Tutup modal approval via tombol silang (X)
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol silang tutup pada modal approval leader izin off
    Maka modal dialog Approval Modal Izin Off tertutup seketika

  @EDT-014
  Skenario: Tutup modal approval via tombol keyboard Escape
    Ketika leader menekan tombol Setujui pada baris permohonan pertama
    Dan leader menekan tombol keyboard Escape pada leader izin off
    Maka modal dialog Approval Modal Izin Off tertutup seketika

  @EDT-015
  Skenario: Membuka modal detail permohonan izin off via tombol Detail
    Ketika leader menekan tombol Detail pada baris permohonan pertama
    Maka modal dialog Detail Izin Off terbuka menampilkan informasi permohonan
