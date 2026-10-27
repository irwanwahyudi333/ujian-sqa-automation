/**
 * Mock Data for Hadir Management - Registrasi User v2
 * Data ini diselaraskan dengan kontrak response API Hadir Management
 */

const MOCK_DATA = {
  divisions: [
    { id: "div-01", name: "IT & Digital Solution" },
    { id: "div-02", name: "Human Capital Management" },
    { id: "div-03", name: "Finance & Accounting" },
    { id: "div-04", name: "Operations & Logistics" },
    { id: "div-05", name: "Business Development & Sales" }
  ],

  units: [
    { id: "unit-01", name: "Quality Assurance (QA) Automation" },
    { id: "unit-02", name: "Software Engineering - Frontend" },
    { id: "unit-03", name: "Software Engineering - Backend" },
    { id: "unit-04", name: "DevOps & Cloud Infrastructure" },
    { id: "unit-05", name: "UI/UX Design & Product Research" },
    { id: "unit-06", name: "Talent Acquisition & Employee Relation" },
    { id: "unit-07", name: "General Affair & Facility" }
  ],

  positions: [
    { id: "pos-01", name: "SQA Automation Engineer" },
    { id: "pos-02", name: "Manual QA Tester" },
    { id: "pos-03", name: "Frontend Engineer (React/Next.js)" },
    { id: "pos-04", name: "Backend Engineer (Java/Node.js)" },
    { id: "pos-05", name: "Mobile Developer (Flutter/Android)" },
    { id: "pos-06", name: "DevOps Engineer" },
    { id: "pos-07", name: "Product Designer" },
    { id: "pos-08", name: "HR Officer" }
  ],

  jobLevels: [
    { id: "lvl-01", name: "Internship / Magang", level: 1 },
    { id: "lvl-02", name: "Junior Specialist", level: 2 },
    { id: "lvl-03", name: "Middle Specialist", level: 3 },
    { id: "lvl-04", name: "Senior Specialist", level: 4 },
    { id: "lvl-05", name: "Team Lead / Supervisor", level: 5 },
    { id: "lvl-06", name: "Engineering Manager", level: 6 },
    { id: "lvl-07", name: "Head of Department", level: 7 }
  ],

  upliners: [
    { id: "up-01", nik: "D6240001", fullname: "Irwan Wahyudi", level: 5, jobTitle: "QA Lead" },
    { id: "up-02", nik: "D6240002", fullname: "Budi Santoso", level: 6, jobTitle: "Engineering Manager" },
    { id: "up-03", nik: "D6240003", fullname: "Siti Rahmawati", level: 7, jobTitle: "Head of IT" },
    { id: "up-04", nik: "D6240004", fullname: "Hendra Wijaya", level: 6, jobTitle: "Operations Manager" },
    { id: "up-05", nik: "D7240010", fullname: "Dewi Lestari", level: 5, jobTitle: "Lead Frontend" },
    { id: "up-06", nik: "D7240015", fullname: "Rian Prasetyo", level: 5, jobTitle: "Lead Backend" }
  ],

  uplinersClientV2: [
    { id: "cl2-01", fullname: "Hendra Wijaya", job_departement: { name: "Client IT Operation" } },
    { id: "cl2-02", fullname: "Andi Pratama", job_departement: { name: "Delivery Banking Client" } },
    { id: "cl2-03", fullname: "Clarissa Putri", job_departement: { name: "Enterprise Solution" } },
    { id: "cl2-04", fullname: "Dika Pratama", job_departement: { name: "Client Architecture" } }
  ],

  uplinersClientV3: [
    { id: "cl3-01", fullname: "Bambang Pamungkas", job_departement: { name: "Client Division East" } },
    { id: "cl3-02", fullname: "Eko Yulianto", job_departement: { name: "Client Division West" } },
    { id: "cl3-03", fullname: "Nadia Safitri", job_departement: { name: "Regional Coordinator" } },
    { id: "cl3-04", fullname: "Siti Rahmawati", job_departement: { name: "Delivery Lead" } }
  ],

  contractTypes: [
    { id: "ct-01", name: "PKWT (Kontrak)" },
    { id: "ct-02", name: "PKWTT (Karyawan Tetap)" },
    { id: "ct-03", name: "Magang / Internship" },
    { id: "ct-04", name: "Freelance / Mitra Kerja" },
    { id: "ct-05", name: "Outsourcing" }
  ],

  locations: [
    { id: "loc-01", name: "Kantor Pusat Jakarta Selatan (Head Office)" },
    { id: "loc-02", name: "Kantor Cabang Bandung (Tech Hub)" },
    { id: "loc-03", name: "Kantor Cabang Surabaya" },
    { id: "loc-04", name: "Hybrid Hub Kuningan" },
    { id: "loc-05", name: "Remote / Work From Home (WFH)" }
  ],

  schedules: [
    { id: "sch-01", name: "Reguler Office (08:00 - 17:00 WIB, Senin-Jumat)" },
    { id: "sch-02", name: "Reguler Flexi (09:00 - 18:00 WIB, Senin-Jumat)" },
    { id: "sch-03", name: "Shift Pagi (06:00 - 14:00 WIB)" },
    { id: "sch-04", name: "Shift Siang (14:00 - 22:00 WIB)" },
    { id: "sch-05", name: "Shift Malam (22:00 - 06:00 WIB)" }
  ]
};

// Export to window object for browser access
if (typeof window !== "undefined") {
  window.MOCK_DATA = MOCK_DATA;
}
