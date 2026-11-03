# language: id
@ui @client @client-cuti @cuti @lean
Fitur: Portal Client - Pengelolaan dan Validasi Cuti Berjenjang
  Sebagai perwakilan Client (Upliner V2 / Upliner V3)
  Saya ingin meninjau dan memvalidasi permohonan cuti staf binaan
  Agar pengajuan cuti dapat diproses sesuai batas wewenang berjenjang

  @tc-cl-cuti-all-01
  Skenario: Verifikasi halaman Semua Riwayat Cuti Staf
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/cuti/semua"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Cuti" pada portal client
    Maka jumlah baris data tabel lebih besar dari nol

  @tc-cl-cuti-pending-01
  Skenario: Verifikasi halaman Permohonan Cuti Pending
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/cuti/pending"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat

  @tc-cl-cuti-v2-01
  Skenario: Validasi Cuti Tahap 1 oleh Upliner V2 (Data Validator 1)
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/cuti/validator"
    Maka label menu sidebar validator cuti menampilkan "Data Validator 1"
    Dan tabel data pada halaman portal client terlihat
    Ketika pengguna membuka modal aksi validasi pada baris pertama
    Maka modal aksi validasi ditampilkan
    Ketika pengguna memasukkan catatan validasi "Disetujui oleh Data Validator 1"
    Dan pengguna menyetujui pengajuan validasi
    Maka notifikasi umpan balik validasi berhasil ditampilkan

  @tc-cl-cuti-v3-01
  Skenario: Validasi Cuti Tahap 2 oleh Upliner V3 (Data Validator 2)
    Dengan pengguna masuk sebagai peran "Upliner V3"
    Dan pengguna membuka halaman portal client "/client/cuti/validator"
    Maka label menu sidebar validator cuti menampilkan "Data Validator 2"
    Dan tabel data pada halaman portal client terlihat
    Ketika pengguna membuka modal aksi validasi pada baris pertama
    Maka modal aksi validasi ditampilkan
    Ketika pengguna memasukkan catatan validasi "Disetujui final oleh Data Validator 2"
    Dan pengguna menyetujui pengajuan validasi
    Maka notifikasi umpan balik validasi berhasil ditampilkan

