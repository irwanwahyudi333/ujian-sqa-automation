# language: id
@ui @leader-staff @staff @edit
Fitur: Pengujian Pembaruan Data Anggota Tim Staff
  Sebagai Leader
  Saya ingin memperbarui data jabatan, unit, atau status staf
  Agar informasi profil anggota tim staf selalu mutakhir dan akurat

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @EDT-001
  Skenario: Membuka modal dialog Edit Staff pada baris pertama
    Ketika leader menekan tombol Edit pada baris pertama tabel staff
    Maka modal edit data staff ditampilkan

  @EDT-002
  Skenario: Memastikan form edit menampilkan pre-filled data staf yang dipilih
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-001"
    Maka field NIK pada modal edit menampilkan "STF-001"
    Dan field Nama Lengkap pada modal edit menampilkan "Ahmad Fauzi"
    Dan field Email pada modal edit menampilkan "ahmad.fauzi@hadir.com"

  @EDT-003
  Skenario: Memperbarui nama lengkap staf menjadi nama baru yang valid
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-001"
    Dan leader mengubah nama lengkap staff menjadi "Ahmad Fauzi S.Kom"
    Dan leader menekan tombol Simpan Perubahan pada modal edit staff
    Maka modal edit data staff tertutup
    Dan notifikasi toast leader staff menampilkan pesan "Berhasil mengubah data staff"
    Dan baris staf dengan NIK "STF-001" menampilkan nama "Ahmad Fauzi S.Kom"

  @EDT-004
  Skenario: Memperbarui jabatan posisi staf
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-001"
    Dan leader mengubah posisi jabatan staff menjadi "Lead Frontend Engineer"
    Dan leader menekan tombol Simpan Perubahan pada modal edit staff
    Maka notifikasi toast leader staff menampilkan pesan "Berhasil mengubah data staff"

  @EDT-005
  Skenario: Memperbarui status staf dari Aktif menjadi Cuti
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-001"
    Dan leader mengubah status staff menjadi "Cuti"
    Dan leader menekan tombol Simpan Perubahan pada modal edit staff
    Maka notifikasi toast leader staff menampilkan pesan "Berhasil mengubah data staff"
    Dan baris staf dengan NIK "STF-001" memiliki status "Cuti"

  @EDT-006
  Skenario: Validasi kegagalan edit saat nama lengkap dikosongkan
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-001"
    Dan leader mengubah nama lengkap staff menjadi ""
    Dan leader menekan tombol Simpan Perubahan pada modal edit staff
    Maka notifikasi toast leader staff menampilkan pesan "Harap lengkapi semua data wajib!"
    Dan modal edit data staff tetap terbuka

  @EDT-007
  Skenario: Pembatalan pengeditan staf via tombol Batal
    Ketika leader menekan tombol Edit pada baris staf dengan NIK "STF-002"
    Dan leader mengubah nama lengkap staff menjadi "Nama Tidak Jadi Diubah"
    Dan leader menekan tombol Batal pada modal edit staff
    Maka modal edit data staff tertutup
    Dan baris staf dengan NIK "STF-002" tetap menampilkan nama "Bella Saphira"

  @EDT-008
  Skenario: Penutupan modal edit staf via tombol close silang X
    Ketika leader menekan tombol Edit pada baris pertama tabel staff
    Dan leader menekan tombol close X pada modal edit staff
    Maka modal edit data staff tertutup

  @EDT-009
  Skenario: Penutupan modal edit staf via tombol keyboard Escape
    Ketika leader menekan tombol Edit pada baris pertama tabel staff
    Dan leader menekan tombol keyboard Escape
    Maka modal edit data staff tertutup
