# language: id
@ui @leader-staff @staff @add
Fitur: Pengujian Penambahan Data Anggota Tim Staff
  Sebagai Leader
  Saya ingin mendaftarkan anggota staf baru ke dalam sistem
  Agar data penugasan anggota tim staf tercatat secara lengkap dan valid

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @ADD-001
  Skenario: Membuka modal dialog Tambah Data Staff
    Ketika leader menekan tombol Tambah Staff
    Maka modal tambah data staff ditampilkan

  @ADD-002
  Skenario: Menambahkan staf baru dengan seluruh data lengkap dan valid
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-013", nama "Rian Ardiansyah", email "rian.ardiansyah@hadir.com", unit "Technology", posisi "QA Engineer", phone "081298765432", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka modal tambah data staff tertutup
    Dan notifikasi toast leader staff menampilkan pesan "Berhasil menambahkan staff"
    Dan staf dengan nama "Rian Ardiansyah" tampil pada baris pertama tabel leader staff

  @ADD-003
  Skenario: Validasi kegagalan tambah staf saat field NIK dikosongkan
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "", nama "Budi Hartono", email "budi.hartono@hadir.com", unit "Marketing", posisi "Staff", phone "081234567899", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka notifikasi toast leader staff menampilkan pesan "Harap lengkapi semua data wajib!"
    Dan modal tambah data staff tetap terbuka

  @ADD-004
  Skenario: Validasi kegagalan tambah staf saat field Nama Lengkap dikosongkan
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-014", nama "", email "staff.kosong@hadir.com", unit "Technology", posisi "Developer", phone "081234567899", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka notifikasi toast leader staff menampilkan pesan "Harap lengkapi semua data wajib!"

  @ADD-005
  Skenario: Validasi kegagalan tambah staf saat field Email dikosongkan
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-015", nama "Hadi Pranoto", email "", unit "Operations", posisi "Officer", phone "081234567899", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka notifikasi toast leader staff menampilkan pesan "Harap lengkapi semua data wajib!"

  @ADD-006
  Skenario: Validasi kegagalan tambah staf saat dropdown Unit belum dipilih
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-016", nama "Junaedi", email "junaedi@hadir.com", unit "", posisi "Staff", phone "081234567899", dan status "Aktif"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka notifikasi toast leader staff menampilkan pesan "Harap lengkapi semua data wajib!"

  @ADD-007
  Skenario: Pembatalan penambahan staf via tombol Batal
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-999", nama "Staf Batal", email "batal@hadir.com", unit "Technology", posisi "Tester", phone "081299999999", dan status "Aktif"
    Dan leader menekan tombol Batal pada modal tambah staff
    Maka modal tambah data staff tertutup
    Dan staf dengan nama "Staf Batal" tidak ditambahkan ke tabel leader staff

  @ADD-008
  Skenario: Penutupan modal tambah staf via tombol close silang X
    Ketika leader menekan tombol Tambah Staff
    Dan leader menekan tombol close X pada modal tambah staff
    Maka modal tambah data staff tertutup

  @ADD-009
  Skenario: Penutupan modal tambah staf via tombol keyboard Escape
    Ketika leader menekan tombol Tambah Staff
    Dan leader menekan tombol keyboard Escape
    Maka modal tambah data staff tertutup

  @ADD-010
  Skenario: Menambahkan staf baru dengan unit Human Resources dan status Cuti
    Ketika leader menekan tombol Tambah Staff
    Dan leader mengisi form tambah staff dengan NIK "STF-017", nama "Siti Aminah", email "siti.aminah@hadir.com", unit "Human Resources", posisi "Recruiter", phone "081211112222", dan status "Cuti"
    Dan leader menekan tombol Simpan pada modal tambah staff
    Maka notifikasi toast leader staff menampilkan pesan "Berhasil menambahkan staff"
    Dan staf dengan NIK "STF-017" memiliki status "Cuti"
