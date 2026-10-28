# language: id
@ui @divisi @edit-divisi
Fitur: Perubahan Data Divisi (Edit Divisi)
  Sebagai admin manajemen HADIR
  Saya ingin mengubah nama divisi yang sudah terdaftar
  Agar informasi divisi selalu mutakhir dan sesuai dengan penyesuaian organisasi

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @EDT-001
  Skenario: Membuka modal dialog Edit Divisi via tombol aksi langsung
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Maka modal dialog Edit Divisi terbuka
    Dan judul modal edit menampilkan "Edit Divisi"

  @EDT-002
  Skenario: Memeriksa judul pada modal dialog edit divisi
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Maka judul modal edit menampilkan "Edit Divisi"

  @EDT-003
  Skenario: Memeriksa nilai awal pada input nama divisi di modal edit (pre-filled)
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Maka field input nama divisi pada modal edit terisi dengan teks "Technology"

  @EDT-004
  Skenario: Mengubah nama divisi menjadi nama baru yang valid
    Ketika admin menekan tombol edit langsung pada baris divisi "Operations"
    Dan admin mengubah nama divisi menjadi "Global Operations"
    Dan admin menekan tombol Simpan pada modal edit
    Maka modal dialog Edit Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Edit Divisi"
    Dan nama divisi pada tabel terbarui menjadi "Global Operations"

  @EDT-005
  Skenario: Memeriksa pembaruan kolom TANGGAL UPDATE setelah berhasil edit
    Ketika admin menekan tombol edit langsung pada baris divisi "Customer Support"
    Dan admin mengubah nama divisi menjadi "Customer Experience"
    Dan admin menekan tombol Simpan pada modal edit
    Maka kolom tanggal update pada baris divisi "Customer Experience" diperbarui dengan waktu saat ini

  @EDT-006
  Skenario: Memeriksa kolom TANGGAL BUAT tetap tidak berubah setelah edit
    Ketika admin menekan tombol edit langsung pada baris divisi "Quality Assurance"
    Dan admin mengubah nama divisi menjadi "Software Quality Assurance"
    Dan admin menekan tombol Simpan pada modal edit
    Maka kolom tanggal buat pada baris divisi tersebut tetap sama seperti sebelum diedit

  @EDT-007
  Skenario: Mengosongkan field nama divisi pada modal edit lalu submit
    Ketika admin menekan tombol edit langsung pada baris divisi "Finance & Accounting"
    Dan admin menghapus seluruh teks nama divisi pada modal edit
    Dan admin menekan tombol Simpan pada modal edit
    Maka form edit menolak submit karena validasi input wajib diisi
    Dan perubahan tidak tersimpan

  @EDT-008
  Skenario: Mengubah nama divisi hanya dengan karakter spasi
    Ketika admin menekan tombol edit langsung pada baris divisi "Finance & Accounting"
    Dan admin mengubah nama divisi menjadi "     "
    Dan admin menekan tombol Simpan pada modal edit
    Maka form edit menolak submit entri kosong
    Dan modal dialog Edit Divisi tetap terbuka

  @EDT-009
  Skenario: Membatalkan perubahan nama divisi dengan tombol Batal
    Ketika admin menekan tombol edit langsung pada baris divisi "Human Resources"
    Dan admin mengubah nama divisi menjadi "People & Culture"
    Dan admin menekan tombol Batal pada modal edit
    Maka modal dialog Edit Divisi tertutup
    Dan nama divisi pada tabel tetap "Human Resources"

  @EDT-010
  Skenario: Menutup modal edit divisi dengan tombol silang (X)
    Ketika admin menekan tombol edit langsung pada baris divisi "Human Resources"
    Dan admin menekan tombol silang tutup pada modal edit
    Maka modal dialog Edit Divisi tertutup tanpa menyimpan perubahan

  @EDT-011
  Skenario: Menutup modal edit divisi dengan tombol Escape pada keyboard
    Ketika admin menekan tombol edit langsung pada baris divisi "Human Resources"
    Dan admin menekan tombol Escape pada keyboard
    Maka modal dialog Edit Divisi tertutup dan data tabel tidak berubah

  @EDT-012
  Skenario: Membuka modal edit divisi melalui menu dropdown aksi 3-titik
    Ketika admin menekan tombol menu 3-titik pada baris divisi "Product Management"
    Dan admin memilih opsi "Edit" pada dropdown aksi
    Maka modal dialog Edit Divisi terbuka dengan data nama "Product Management"

  @EDT-013
  Skenario: Mengedit nama divisi menjadi nama yang sudah digunakan divisi lain
    Ketika admin menekan tombol edit langsung pada baris divisi "Operations"
    Dan admin mengubah nama divisi menjadi "Technology"
    Dan admin menekan tombol Simpan pada modal edit
    Maka sistem memvalidasi keunikan nama divisi atau menampilkan notifikasi yang sesuai

  @EDT-014
  Skenario: Menyimpan form edit tanpa melakukan perubahan teks apa pun
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Dan admin menekan tombol Simpan pada modal edit
    Maka sistem memproses dengan wajar tanpa terjadi error integritas

  @EDT-015
  Skenario: Mengedit nama divisi dengan spasi di awal dan akhir teks
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Dan admin mengubah nama divisi menjadi "   Technology & Digital   "
    Dan admin menekan tombol Simpan pada modal edit
    Maka sistem melakukan trim dan menyimpan nama divisi sebagai "Technology & Digital"

  @EDT-016
  Skenario: Mengedit divisi dengan nama yang sangat panjang (100+ karakter)
    Ketika admin menekan tombol edit langsung pada baris divisi "Human Resources"
    Dan admin mengubah nama divisi menjadi "Divisi Sumber Daya Manusia dan Pembinaan Karir Karyawan Terpadu Tingkat Korporasi Regional"
    Dan admin menekan tombol Simpan pada modal edit
    Maka nama divisi yang panjang tersebut terbarui dan tampil rapi pada tabel

  @EDT-017
  Skenario: Melakukan submit form edit dengan menekan tombol Enter pada keyboard
    Ketika admin menekan tombol edit langsung pada baris divisi "Customer Support"
    Dan admin mengubah nama divisi menjadi "Customer Care Hub"
    Dan admin menekan tombol Enter pada input nama divisi modal edit
    Maka perubahan nama divisi langsung tersimpan
    Dan nama divisi pada tabel terbarui menjadi "Customer Care Hub"

  @EDT-018
  Skenario: Mengedit divisi pada halaman paginasi kedua
    Ketika admin memilih baris per halaman "5"
    Dan admin menekan tombol halaman selanjutnya pada paginasi divisi
    Dan admin menekan tombol edit langsung pada baris pertama di halaman kedua
    Dan admin mengubah nama divisi menjadi "Updated Division Page 2"
    Dan admin menekan tombol Simpan pada modal edit
    Maka perubahan nama divisi tersimpan pada entri yang tepat

  @EDT-019
  Skenario: Memeriksa notifikasi toast sukses setelah berhasil edit
    Ketika admin menekan tombol edit langsung pada baris divisi "Technology"
    Dan admin mengubah nama divisi menjadi "Core Technology"
    Dan admin menekan tombol Simpan pada modal edit
    Maka notifikasi toast menampilkan pesan "Berhasil Edit Divisi"

  @EDT-020
  Skenario: Mengedit nama divisi dengan karakter kombinasi alfanumerik dan simbol
    Ketika admin menekan tombol edit langsung pada baris divisi "Operations"
    Dan admin mengubah nama divisi menjadi "Ops & Security 24/7 (HQ)"
    Dan admin menekan tombol Simpan pada modal edit
    Maka nama divisi terbarui menjadi "Ops & Security 24/7 (HQ)"
