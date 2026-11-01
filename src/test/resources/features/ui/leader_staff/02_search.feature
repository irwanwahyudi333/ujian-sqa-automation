# language: id
@ui @leader-staff @staff @search
Fitur: Pengujian Pencarian dan Penyaringan Data Staff
  Sebagai Leader
  Saya ingin mencari dan memfilter data anggota staf berdasarkan NIK, nama, jabatan, unit, dan status
  Agar saya dapat menemukan data staf tertentu dengan cepat dan akurat

  Latar Belakang:
    Dengan leader membuka halaman daftar staff

  @SRC-001
  Skenario: Pencarian staf dengan NIK tepat
    Ketika leader memasukkan kata kunci pencarian leader staff "STF-001"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan NIK "STF-001"

  @SRC-002
  Skenario: Pencarian staf dengan nama lengkap tepat
    Ketika leader memasukkan kata kunci pencarian leader staff "Bella Saphira"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Bella Saphira"

  @SRC-003
  Skenario: Pencarian staf dengan potongan nama awal (substring)
    Ketika leader memasukkan kata kunci pencarian leader staff "Ahmad"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Ahmad Fauzi"

  @SRC-004
  Skenario: Pencarian nama staf bersifat case-insensitive huruf kecil
    Ketika leader memasukkan kata kunci pencarian leader staff "farhan maulana"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Farhan Maulana"

  @SRC-005
  Skenario: Pencarian nama staf dengan huruf kapital penuh
    Ketika leader memasukkan kata kunci pencarian leader staff "CAHYO"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Cahyo Nugroho"

  @SRC-006
  Skenario: Pencarian staf berdasarkan alamat email
    Ketika leader memasukkan kata kunci pencarian leader staff "dewi.anggraini@hadir.com"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Dewi Anggraini"

  @SRC-007
  Skenario: Pencarian staf berdasarkan jabatan
    Ketika leader memasukkan kata kunci pencarian leader staff "Developer"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan staf dengan jabatan yang memuat "Developer"

  @SRC-008
  Skenario: Pencarian staf dengan NIK fiktif yang tidak terdaftar
    Ketika leader memasukkan kata kunci pencarian leader staff "STF-999XYZ"
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan pesan "Tidak Ada Data"

  @SRC-009
  Skenario: Eksekusi pencarian dengan menekan tombol keyboard Enter
    Ketika leader mengetik "Eko Prasetya" pada input pencarian leader staff
    Dan leader menekan tombol Enter pada keyboard untuk search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Eko Prasetya"

  @SRC-010
  Skenario: Pencarian dengan spasi di awal dan akhir kata kunci
    Ketika leader memasukkan kata kunci pencarian leader staff "   Farhan   "
    Dan leader menekan tombol Search leader staff
    Maka tabel leader staff menampilkan baris staf dengan nama "Farhan Maulana"

  @SRC-011
  Skenario: Reset pencarian via tombol Reset
    Ketika leader memasukkan kata kunci pencarian leader staff "Ahmad"
    Dan leader menekan tombol Search leader staff
    Dan leader menekan tombol Reset leader staff
    Maka input pencarian leader staff menjadi kosong
    Dan seluruh data staf kembali ditampilkan di tabel leader staff

  @SRC-012
  Skenario: Membuka modal dialog filter data staff
    Ketika leader menekan tombol Filter leader staff
    Maka modal filter data staff ditampilkan

  @SRC-013
  Skenario: Filter staf berdasarkan unit kerja Technology
    Ketika leader menekan tombol Filter leader staff
    Dan leader memilih filter unit "Technology" pada modal filter staff
    Dan leader menekan tombol Terapkan pada modal filter staff
    Maka tabel leader staff hanya menampilkan staf dengan unit "Technology"

  @SRC-014
  Skenario: Filter staf berdasarkan status Cuti
    Ketika leader menekan tombol Filter leader staff
    Dan leader memilih filter status "Cuti" pada modal filter staff
    Dan leader menekan tombol Terapkan pada modal filter staff
    Maka tabel leader staff hanya menampilkan staf dengan status "Cuti"

  @SRC-015
  Skenario: Batal filter staf via tombol Batal
    Ketika leader menekan tombol Filter leader staff
    Dan leader menekan tombol Batal pada modal filter staff
    Maka modal filter data staff tertutup tanpa mengubah data tabel
