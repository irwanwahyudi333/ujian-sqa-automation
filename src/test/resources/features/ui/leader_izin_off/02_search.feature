# language: id
@ui @leader-izin-off @izin-off @search
Fitur: Pengujian Pencarian dan Penyaringan Permohonan Izin Off
  Sebagai Leader
  Saya ingin mencari dan memfilter data permohonan izin off berdasarkan nama, status, dan tanggal
  Agar saya dapat menemukan permohonan karyawan tertentu dengan cepat dan akurat

  Latar Belakang:
    Dengan leader membuka halaman manajemen leader izin off

  @SRC-001
  Skenario: Pencarian permohonan dengan nama karyawan lengkap
    Ketika leader memasukkan kata kunci pencarian leader izin off "Ahmad Fauzi"
    Dan leader menekan tombol Search leader izin off
    Maka tabel leader izin off hanya menampilkan permohonan karyawan "Ahmad Fauzi"

  @SRC-002
  Skenario: Pencarian permohonan dengan substring nama
    Ketika leader memasukkan kata kunci pencarian leader izin off "Budi"
    Dan leader menekan tombol Search leader izin off
    Maka permohonan karyawan "Budi Santoso" tampil dalam tabel leader izin off

  @SRC-003
  Skenario: Pencarian nama karyawan bersifat case-insensitive huruf kecil
    Ketika leader memasukkan kata kunci pencarian leader izin off "citra dewi"
    Dan leader menekan tombol Search leader izin off
    Maka permohonan karyawan "Citra Dewi" tampil dalam tabel leader izin off

  @SRC-004
  Skenario: Pencarian nama karyawan dengan huruf kapital penuh
    Ketika leader memasukkan kata kunci pencarian leader izin off "DEDI IRAWAN"
    Dan leader menekan tombol Search leader izin off
    Maka permohonan karyawan "Dedi Irawan" tampil dalam tabel leader izin off

  @SRC-005
  Skenario: Eksekusi pencarian dengan menekan tombol keyboard Enter
    Ketika leader mengetik "Eka Permata" pada input pencarian leader izin off
    Dan leader menekan tombol Enter pada keyboard untuk search leader izin off
    Maka tabel leader izin off menampilkan permohonan karyawan "Eka Permata"

  @SRC-006
  Skenario: Pencarian permohonan dengan nama fiktif yang tidak terdaftar
    Ketika leader memasukkan kata kunci pencarian leader izin off "NamaFiktif999XYZ"
    Dan leader menekan tombol Search leader izin off
    Maka tabel leader izin off menampilkan pesan "Tidak Ada Data"

  @SRC-007
  Skenario: Reset pencarian via tombol Reset
    Ketika leader memasukkan kata kunci pencarian leader izin off "Ahmad"
    Dan leader menekan tombol Search leader izin off
    Dan leader menekan tombol Reset leader izin off
    Maka input pencarian leader izin off kembali kosong
    Dan tabel leader izin off menampilkan kembali seluruh data permohonan

  @SRC-008
  Skenario: Filter permohonan berdasarkan status PENDING
    Ketika leader memilih status filter "PENDING" pada dropdown leader izin off
    Maka seluruh baris yang tampil di tabel leader izin off berstatus "PENDING"

  @SRC-009
  Skenario: Filter permohonan berdasarkan status APPROVED
    Ketika leader memilih status filter "APPROVED" pada dropdown leader izin off
    Maka seluruh baris yang tampil di tabel leader izin off berstatus "APPROVED"

  @SRC-010
  Skenario: Filter permohonan berdasarkan status REJECTED
    Ketika leader memilih status filter "REJECTED" pada dropdown leader izin off
    Maka seluruh baris yang tampil di tabel leader izin off berstatus "REJECTED"

  @SRC-011
  Skenario: Filter kombinasi nama karyawan dan status
    Ketika leader memasukkan kata kunci pencarian leader izin off "Citra"
    Dan leader memilih status filter "APPROVED" pada dropdown leader izin off
    Dan leader menekan tombol Search leader izin off
    Maka baris permohonan "Citra Dewi" tampil dengan status "APPROVED"

  @SRC-012
  Skenario: Filter permohonan berdasarkan rentang tanggal
    Ketika leader mengisi rentang tanggal leader izin off dari "2024-11-01" sampai "2024-11-20"
    Maka baris permohonan di tabel leader izin off berada dalam rentang tanggal tersebut

  @SRC-013
  Skenario: Filter rentang tanggal masa lampau tanpa data
    Ketika leader mengisi rentang tanggal leader izin off dari "2020-01-01" sampai "2020-01-10"
    Maka tabel leader izin off menampilkan pesan "Tidak Ada Data"

  @SRC-014
  Skenario: Pencarian berdasarkan kata kunci pada alasan izin
    Ketika leader memasukkan kata kunci pencarian leader izin off "paspor"
    Dan leader menekan tombol Search leader izin off
    Maka baris permohonan dengan alasan paspor tampil dalam tabel leader izin off

  @SRC-015
  Skenario: Reset seluruh filter mengembalikan tampilan semula dan memunculkan snackbar info
    Ketika leader memilih status filter "APPROVED" pada dropdown leader izin off
    Dan leader menekan tombol Reset leader izin off
    Maka notifikasi toast leader izin off menampilkan pesan "Filter pencarian berhasil direset"
    Dan tabel leader izin off menampilkan kembali seluruh data permohonan
