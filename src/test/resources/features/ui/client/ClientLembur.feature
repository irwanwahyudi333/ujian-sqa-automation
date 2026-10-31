# language: id
@ui @client @lean
Fitur: Portal Client - Pengelolaan dan Validasi Lembur Berjenjang
  Sebagai perwakilan Client (Upliner V2 / Upliner V3)
  Saya ingin meninjau dan memvalidasi permohonan lembur staf binaan
  Agar kompensasi lembur dihitung akurat dan disetujui secara berjenjang

  @tc-cl-ot-all-01
  Skenario: Verifikasi halaman Semua Riwayat Lembur Staf
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/lembur/semua"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat
    Ketika pengguna mencari staf dengan kata kunci "Lembur" pada portal client
    Maka jumlah baris data tabel lebih besar dari nol

  @tc-cl-ot-pending-01
  Skenario: Verifikasi halaman Pengajuan Lembur Pending
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/lembur/pending"
    Maka tabel data pada halaman portal client terlihat
    Dan kontrol pencarian dan filter tanggal terlihat

  @tc-cl-ot-v2-01
  Skenario: Validasi Lembur Tahap 1 oleh Upliner V2 (Data Validator 1)
    Dengan pengguna masuk sebagai peran "Upliner V2"
    Dan pengguna membuka halaman portal client "/client/lembur/validator"
    Maka label menu sidebar validator lembur menampilkan "Data Validator 1"
    Dan tabel data pada halaman portal client terlihat
    Ketika pengguna membuka modal aksi validasi pada baris pertama
    Maka modal aksi validasi ditampilkan
    Ketika pengguna memasukkan catatan validasi "Lembur diverifikasi oleh Validator 1"
    Dan pengguna menyetujui pengajuan validasi
    Maka notifikasi umpan balik validasi berhasil ditampilkan

  @tc-cl-ot-v3-01
  Skenario: Validasi Lembur Tahap 2 oleh Upliner V3 (Data Validator 2)
    Dengan pengguna masuk sebagai peran "Upliner V3"
    Dan pengguna membuka halaman portal client "/client/lembur/validator"
    Maka label menu sidebar validator lembur menampilkan "Data Validator 2"
    Dan tabel data pada halaman portal client terlihat
    Ketika pengguna membuka modal aksi validasi pada baris pertama
    Maka modal aksi validasi ditampilkan
    Ketika pengguna memasukkan catatan validasi "Lembur disetujui final oleh Validator 2"
    Dan pengguna menyetujui pengajuan validasi
    Maka notifikasi umpan balik validasi berhasil ditampilkan

