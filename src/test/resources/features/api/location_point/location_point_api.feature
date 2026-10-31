# language: id
@api @management @location-point @api-location-point
Fitur: Pengujian Backend REST API Modul Location Point
  Sebagai sistem integrasi HADIR
  Saya ingin memastikan endpoint REST API lokasi presensi memvalidasi rentang koordinat dan batas radius
  Agar kalkulasi jarak geofencing presensi tidak menghasilkan anomali matematis

  @LOC-API-001
  Skenario: Mengambil daftar titik lokasi presensi dengan token valid
    Ketika client mengirim request GET ke endpoint location point "/api/locations" dengan token valid
    Maka response API location point mengembalikan status 200 OK
    Dan response API memuat daftar titik lokasi presensi

  @LOC-API-002
  Skenario: Menambahkan titik lokasi baru dengan koordinat dan radius valid
    Ketika client mengirim request POST ke endpoint "/api/locations" dengan nama "Kantor Dika Slipi", latitude "-6.2088", longitude "106.8456", radius "100"
    Maka response API location point mengembalikan status 200 atau 201
    Dan response API memuat lokasi presensi yang baru dibuat

  @LOC-API-003
  Skenario: Validasi penambahan lokasi dengan latitude di luar batas
    Ketika client mengirim request POST ke endpoint "/api/locations" dengan latitude tidak valid "150.0000"
    Maka response API location point mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan koordinat latitude di luar rentang valid

  @LOC-API-004
  Skenario: Validasi penambahan lokasi dengan radius negatif
    Ketika client mengirim request POST ke endpoint "/api/locations" dengan radius negatif "-50"
    Maka response API location point mengembalikan status 400 Bad Request
    Dan pesan error mengindikasikan radius harus bernilai positif

  @LOC-API-005
  Skenario: Menghapus titik lokasi presensi dengan ID valid
    Ketika client mengirim request DELETE ke endpoint location point "/api/locations/{id}" dengan ID valid
    Maka response API location point mengembalikan status 200 OK

