# language: id
@ui @user-monitoring @edit
Fitur: Pembaruan Data User Monitoring (Edit User)
  Sebagai admin manajemen HADIR
  Saya ingin mengubah informasi nama, email, password, maupun unit kerja user monitoring
  Agar data pengguna selalu mutakhir dan sesuai struktur penugasan terbaru

  Latar Belakang:
    Dengan admin membuka halaman manajemen user monitoring

  @EDT-001
  Skenario: Membuka modal dialog Edit User Monitoring
    Ketika admin menekan tombol edit pada baris user "Ahmad Fauzi"
    Maka modal dialog Edit User Monitoring terbuka
    Dan judul modal edit user monitoring menampilkan "Edit User Monitoring"

  @EDT-002
  Skenario: Verifikasi nilai pre-filled pada form edit
    Ketika admin menekan tombol edit pada baris user "Ahmad Fauzi"
    Maka field edit nama user terisi dengan "Ahmad Fauzi"
    Dan field edit email terisi dengan "ahmad.fauzi@hadir.com"
    Dan dropdown edit unit memilih "Technology"

  @EDT-003
  Skenario: Memperbarui nama lengkap user monitoring
    Ketika admin menekan tombol edit pada baris user "Ahmad Fauzi"
    Dan admin mengubah nama user menjadi "Ahmad Fauzi Updated"
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka modal dialog Edit User Monitoring tertutup
    Dan notifikasi toast user monitoring menampilkan pesan "Berhasil ubah user monitoring"
    Dan user bernama "Ahmad Fauzi Updated" muncul pada tabel user monitoring

  @EDT-004
  Skenario: Memperbarui email user monitoring
    Ketika admin menekan tombol edit pada baris user "Bella Saphira"
    Dan admin mengubah email user menjadi "bella.new@hadir.com"
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka user bernama "Bella Saphira" memiliki email baru "bella.new@hadir.com" di tabel

  @EDT-005
  Skenario: Memperbarui unit departemen user monitoring
    Ketika admin menekan tombol edit pada baris user "Bella Saphira"
    Dan admin memilih unit "Finance & Accounting" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka user bernama "Bella Saphira" memiliki badge unit "Finance & Accounting"

  @EDT-006
  Skenario: Validasi field Nama User tidak boleh dikosongkan saat edit
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin mengosongkan nama user pada modal edit
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka form edit menolak submit karena nama user wajib diisi
    Dan modal dialog Edit User Monitoring tetap terbuka

  @EDT-007
  Skenario: Validasi field Email tidak boleh dikosongkan saat edit
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin mengosongkan email pada modal edit
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka form edit menolak submit karena email wajib diisi
    Dan modal dialog Edit User Monitoring tetap terbuka

  @EDT-008
  Skenario: Field password opsional saat edit data
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin membiarkan field password edit kosong
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka modal dialog Edit User Monitoring tertutup dan pembaruan berhasil disimpan

  @EDT-009
  Skenario: Mengubah password user pada modal edit
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin memasukkan password baru "PasswordBaru2026!" pada modal edit
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka notifikasi toast user monitoring menampilkan pesan "Berhasil ubah user monitoring"

  @EDT-010
  Skenario: Membatalkan pengeditan data via tombol Batal
    Ketika admin menekan tombol edit pada baris user "Cahyo Nugroho"
    Dan admin mengubah nama user menjadi "Cahyo Diubah Batal"
    Dan admin menekan tombol Batal pada modal edit user monitoring
    Maka modal dialog Edit User Monitoring tertutup
    Dan user bernama "Cahyo Nugroho" tetap tidak berubah di tabel

  @EDT-011
  Skenario: Menutup modal edit user via tombol silang (X)
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin menekan tombol silang tutup pada modal edit user monitoring
    Maka modal dialog Edit User Monitoring tertutup tanpa menyimpan perubahan

  @EDT-012
  Skenario: Menutup modal edit user via tombol Escape pada keyboard
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin menekan tombol keyboard Escape pada user monitoring
    Maka modal dialog Edit User Monitoring tertutup seketika

  @EDT-013
  Skenario: Edit user dengan nama memuat karakter khusus dan simbol
    Ketika admin menekan tombol edit pada baris user pertama
    Dan admin mengubah nama user menjadi "Dr. John Doe, M.Kom."
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka user bernama "Dr. John Doe, M.Kom." berhasil diperbarui di tabel

  @EDT-014
  Skenario: Edit user pada halaman kedua tabel
    Ketika admin berpindah ke halaman 2 pada paginasi user monitoring
    Dan admin menekan tombol edit pada baris user pertama di halaman 2
    Dan admin mengubah nama user menjadi "User Halaman Dua Updated"
    Dan admin menekan tombol Simpan pada modal edit user monitoring
    Maka user bernama "User Halaman Dua Updated" berhasil diperbarui

  @EDT-015
  Skenario: Memastikan ID user tersembunyi pada hidden input modal edit
    Ketika admin menekan tombol edit pada baris user pertama
    Maka hidden input ID pada modal edit memiliki nilai ID yang valid
