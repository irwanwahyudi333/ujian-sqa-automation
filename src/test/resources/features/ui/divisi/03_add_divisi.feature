# language: id
@ui @divisi @add-divisi
Fitur: Penambahan Divisi Baru (Tambah Divisi)
  Sebagai admin manajemen HADIR
  Saya ingin menambahkan divisi baru ke dalam struktur organisasi
  Agar dapat mengatur pembagian kerja dan penugasan karyawan pada divisi yang sesuai

  Latar Belakang:
    Dengan admin membuka halaman manajemen divisi

  @ADD-001
  Skenario: Membuka modal dialog Tambah Divisi
    Ketika admin menekan tombol Tambahkan divisi
    Maka modal dialog Tambah Divisi terbuka
    Dan judul modal tambah menampilkan "Tambah Divisi"

  @ADD-002
  Skenario: Memeriksa auto-focus kursor pada input nama divisi saat modal tambah terbuka
    Ketika admin menekan tombol Tambahkan divisi
    Maka kursor otomatis berfokus pada field input nama divisi

  @ADD-003
  Skenario: Memeriksa teks placeholder pada input nama divisi di modal tambah
    Ketika admin menekan tombol Tambahkan divisi
    Maka input nama divisi memiliki placeholder "Nama Divisi"

  @ADD-004
  Skenario: Menambahkan divisi baru dengan nama yang valid
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Cyber Security"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka modal dialog Tambah Divisi tertutup
    Dan notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"
    Dan divisi bernama "Cyber Security" muncul pada tabel data divisi

  @ADD-005
  Skenario: Memeriksa otomatisasi tanggal buat dan tanggal update saat divisi baru ditambahkan
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Cloud Architecture"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka baris divisi "Cloud Architecture" memiliki tanggal buat dan tanggal update yang valid

  @ADD-006
  Skenario: Validasi field nama divisi wajib diisi (required) saat tambah
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin mengosongkan input nama divisi
    Dan admin menekan tombol Simpan pada modal tambah
    Maka form tambah menolak submit karena validasi input wajib diisi
    Dan data divisi baru tidak ditambahkan ke tabel

  @ADD-007
  Skenario: Menambahkan divisi dengan input hanya berisi spasi kosong
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "     "
    Dan admin menekan tombol Simpan pada modal tambah
    Maka form tambah menolak submit entri kosong
    Dan modal dialog Tambah Divisi tetap terbuka

  @ADD-008
  Skenario: Membatalkan penambahan divisi baru dengan tombol Batal
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Divisi Uji Batal"
    Dan admin menekan tombol Batal pada modal tambah
    Maka modal dialog Tambah Divisi tertutup
    Dan divisi bernama "Divisi Uji Batal" tidak muncul di tabel

  @ADD-009
  Skenario: Menutup modal tambah divisi dengan tombol silang (X)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin menekan tombol silang tutup pada modal tambah
    Maka modal dialog Tambah Divisi tertutup tanpa menyimpan data

  @ADD-010
  Skenario: Menutup modal tambah divisi dengan tombol Escape pada keyboard
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin menekan tombol Escape pada keyboard
    Maka modal dialog Tambah Divisi tertutup seketika

  @ADD-011
  Skenario: Menambahkan divisi dengan nama batas bawah (2 karakter)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "IT"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"
    Dan divisi bernama "IT" muncul pada tabel data divisi

  @ADD-012
  Skenario: Menambahkan divisi dengan nama panjang (100 karakter)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Divisi Riset Transformasi Digital dan Pengembangan Kecerdasan Buatan Terpadu Skala Global Nasional"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka notifikasi toast menampilkan pesan "Berhasil Menambahkan Divisi"
    Dan divisi dengan nama panjang tersebut tersimpan dengan baik di tabel

  @ADD-013
  Skenario: Menambahkan divisi dengan kombinasi huruf dan angka
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Industry 4.0 Transformation"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka divisi bernama "Industry 4.0 Transformation" tersimpan pada tabel divisi

  @ADD-014
  Skenario: Menambahkan divisi dengan karakter simbol ampersand (&)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Research & Development"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka divisi bernama "Research & Development" tersimpan dan karakter simbol tampil utuh

  @ADD-015
  Skenario: Menambahkan divisi dengan nama yang sama persis (duplikat)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Technology"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka sistem mendeteksi duplikasi nama divisi atau menampilkan respons yang sesuai

  @ADD-016
  Skenario: Menambahkan divisi dengan nama duplikat berbeda kapitalisasi
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "technology"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka sistem memproses nama divisi sesuai aturan validasi keunikan

  @ADD-017
  Skenario: Menambahkan divisi dengan spasi berlebih di awal dan akhir nama
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "   Supply Chain Logistics   "
    Dan admin menekan tombol Simpan pada modal tambah
    Maka sistem melakukan trim dan menyimpan divisi dengan nama "Supply Chain Logistics"

  @ADD-018
  Skenario: Membuka kembali modal tambah setelah dibatalkan
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Draft Teks Batal"
    Dan admin menekan tombol Batal pada modal tambah
    Dan admin menekan tombol Tambahkan divisi kembali
    Maka input nama divisi dalam keadaan bersih tanpa teks sebelumnya

  @ADD-019
  Skenario: Melakukan submit form tambah divisi dengan menekan tombol Enter
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Legal & Compliance"
    Dan admin menekan tombol Enter pada field input nama divisi
    Maka form tambah divisi otomatis tersubmit
    Dan divisi bernama "Legal & Compliance" muncul di tabel

  @ADD-020
  Skenario: Menambahkan divisi baru saat berada di halaman paginasi lain
    Ketika admin memilih baris per halaman "5"
    Dan admin menekan tombol halaman selanjutnya pada paginasi divisi
    Dan admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Internal Audit"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka divisi "Internal Audit" tersimpan dan total paginasi bertambah

  @ADD-021
  Skenario: Menambahkan divisi dengan karakter tanda kurung
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Corporate Secretary (Corsec)"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka divisi bernama "Corporate Secretary (Corsec)" berhasil ditambahkan

  @ADD-022
  Skenario: Menambahkan divisi dengan karakter tanda hubung (hyphen)
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Public-Private Partnership"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka divisi bernama "Public-Private Partnership" berhasil ditambahkan

  @ADD-023
  Skenario: Memeriksa durasi penutupan otomatis notifikasi toast sukses tambah
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "DevOps"
    Dan admin menekan tombol Simpan pada modal tambah
    Maka notifikasi toast tampil dan otomatis menghilang setelah durasi timeout

  @ADD-024
  Skenario: Menutup notifikasi toast tambah secara manual dengan tombol silang
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Data Science"
    Dan admin menekan tombol Simpan pada modal tambah
    Dan admin menekan tombol tutup pada notifikasi toast
    Maka notifikasi toast langsung tertutup seketika

  @ADD-025
  Skenario: Mencegah duplikasi submit saat menekan tombol Tambah berulang kali dengan cepat
    Ketika admin menekan tombol Tambahkan divisi
    Dan admin memasukkan nama divisi "Blockchain Innovation"
    Dan admin menekan tombol Simpan pada modal tambah secara cepat
    Maka hanya satu entri divisi "Blockchain Innovation" yang ditambahkan ke tabel
