# language: id
@ui @monthly-schedule @edit-schedule
Fitur: Pembaruan Data Jadwal Kerja Bulanan (Edit Schedule)
  Sebagai admin manajemen HADIR
  Saya ingin memperbarui shift kerja atau tanggal jadwal karyawan
  Agar jadwal kerja karyawan selalu mencerminkan penugasan operasional terbaru

  Latar Belakang:
    Dengan admin membuka halaman manajemen monthly schedule

  @EDT-001
  Skenario: Membuka modal dialog Edit Schedule
    Ketika admin menekan tombol Edit pada baris ke-1
    Maka modal dialog Edit Schedule ditampilkan
    Dan judul modal edit schedule menampilkan "Edit Data schedule"

  @EDT-002
  Skenario: Verifikasi field Nama Lengkap berstatus disabled
    Ketika admin menekan tombol Edit pada baris ke-1
    Maka field nama lengkap pada modal edit berstatus disabled dan tidak dapat diedit

  @EDT-003
  Skenario: Verifikasi pre-filled data shifting
    Ketika admin menekan tombol Edit pada baris ke-1
    Maka dropdown shift terisi otomatis sesuai data shift pada baris tersebut

  @EDT-004
  Skenario: Verifikasi pre-filled data tanggal jadwal
    Ketika admin menekan tombol Edit pada baris ke-1
    Maka field tanggal jadwal terisi otomatis sesuai tanggal pada baris tersebut

  @EDT-005
  Skenario: Ubah shifting menjadi "Regular Siang"
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "Regular Siang" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"
    Dan modal dialog edit ditutup
    Dan baris ke-1 menampilkan shift "Regular Siang"

  @EDT-006
  Skenario: Ubah shifting menjadi "Shift Malam"
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "Shift Malam" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"
    Dan baris ke-1 menampilkan shift "Shift Malam"

  @EDT-007
  Skenario: Ubah shifting menjadi "General Office"
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "General Office" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"
    Dan baris ke-1 menampilkan shift "General Office"

  @EDT-008
  Skenario: Ubah tanggal jadwal ke tanggal baru valid
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin mengatur tanggal jadwal menjadi "2026-11-25" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"
    Dan baris ke-1 menampilkan tanggal "2026-11-25"

  @EDT-009
  Skenario: Simpan tanpa mengubah data apapun
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"
    Dan modal dialog edit ditutup

  @EDT-010
  Skenario: Batal edit via tombol Cancel
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "Shift Malam" pada modal edit
    Dan admin menekan tombol Cancel pada modal edit
    Maka modal dialog edit ditutup
    Dan data jadwal pada baris ke-1 tidak berubah

  @EDT-011
  Skenario: Tutup modal edit via tombol icon X
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol close icon X pada modal edit
    Maka modal dialog edit ditutup tanpa menyimpan perubahan

  @EDT-012
  Skenario: Tutup modal edit via tombol Escape
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol keyboard Escape
    Maka modal dialog edit ditutup tanpa menyimpan perubahan

  @EDT-013
  Skenario: Kosongkan field tanggal jadwal saat edit
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin mengosongkan field tanggal jadwal pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka form mencegah submit karena tanggal jadwal bersifat wajib

  @EDT-014
  Skenario: Kosongkan pilihan shifting saat edit
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih opsi shift kosong pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka form mencegah submit karena shift kerja bersifat wajib

  @EDT-015
  Skenario: Edit jadwal pada baris di halaman kedua
    Ketika admin berpindah ke halaman kedua tabel
    Dan admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "Regular Pagi" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka notifikasi toast muncul dengan pesan "Berhasil memperbarui data schedule"

  @EDT-016
  Skenario: Sinkronisasi jam kerja otomatis saat ganti shift
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin memilih shift kerja "Regular Siang" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka kolom jam mulai dan jam berakhir otomatis terupdate sesuai aturan shift

  @EDT-017
  Skenario: State tombol Simpan saat submitting
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka tombol Simpan menampilkan state loading atau disabled sesaat

  @EDT-018
  Skenario: Verifikasi teks pesan toast sukses edit
    Ketika admin menekan tombol Edit pada baris ke-1
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka toast notifikasi memuat teks "Berhasil memperbarui data schedule"

  @EDT-019
  Skenario: Edit jadwal setelah pencarian aktif
    Ketika admin mencari jadwal dengan nama "Ahmad"
    Dan admin menekan tombol Edit pada baris hasil pencarian
    Dan admin mengubah tanggal jadwal menjadi "2026-11-28"
    Dan admin menekan tombol Simpan pada modal edit schedule
    Maka baris hasil pencarian tersebut terupdate dengan tanggal "2026-11-28"

  @EDT-020
  Skenario: Buka modal edit beberapa kali berurutan
    Ketika admin membuka dan menutup modal edit pada beberapa baris berbeda
    Maka data yang ditampilkan di modal selalu sinkron dengan baris yang diklik
