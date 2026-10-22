# โปรเจกต์ APK แสงตะวันแอร์

แอป Android แบบ WebView สำหรับเว็บไซต์:
https://saengtawan-air-service.blogspot.com/

- ชื่อแอป: แสงตะวันแอร์
- โทร: 0993618518
- LINE ID: @558turjd
- Android package: th.co.saengtawan.airservice
- ระบบสร้าง APK อัตโนมัติ: GitHub Actions

## วิธีสร้าง APK โดยใช้โทรศัพท์ Android

1. แตกไฟล์ ZIP นี้ในโทรศัพท์ (หรือเก็บ ZIP ไว้แล้วอัปโหลดไป GitHub ได้)
2. เข้าสู่ https://github.com/ และสมัคร/เข้าสู่ระบบ
3. สร้าง repository ใหม่ ตั้งชื่อ `saengtawan-air-apk` และเลือก Public หรือ Private
4. อัปโหลดไฟล์และโฟลเดอร์ทั้งหมดจาก ZIP นี้เข้า repository โดยรักษาโครงสร้างโฟลเดอร์เดิม
   - ต้องมี `.github/workflows/build-apk.yml`
   - หากหน้าเว็บ GitHub บนมือถือไม่สะดวก ให้เปิดเมนู Chrome > เว็บไซต์เดสก์ท็อป
5. เปิดแท็บ **Actions** ของ repository แล้วอนุญาตให้เปิดใช้งาน Actions หากระบบถาม
6. เลือก workflow **Build Saengtawan Air APK** > **Run workflow** > **Run workflow**
7. รอให้ workflow แสดงสถานะสำเร็จ (เครื่องหมายถูกสีเขียว)
8. เปิดรายการ workflow run ที่สำเร็จ เลื่อนลงไปที่ **Artifacts** แล้วดาวน์โหลด `saengtawan-air-debug-apk`
9. แตกไฟล์ artifact ZIP แล้วติดตั้ง `app-debug.apk` บนโทรศัพท์ Android
10. หาก Android ถาม ให้เปิดอนุญาตติดตั้งแอปจากแหล่งที่มานี้เฉพาะเมื่อคุณเชื่อถือไฟล์ที่สร้างเอง

## สิ่งสำคัญ

- ในโปรเจกต์นี้มี workflow สำหรับสร้าง **debug APK** ที่ลงชื่อทดสอบอัตโนมัติ เหมาะกับการติดตั้งทดสอบและแจกจ่ายแบบจำกัด ไม่ใช่ release key สำหรับ Google Play
- คุณต้องทำขั้นตอนสร้าง repository และกด Run workflow ด้วยตนเองอย่างน้อยครั้งแรก ผมไม่สามารถเข้าถึงบัญชี GitHub ของคุณหรือกดอนุญาตแทนได้จากแชตนี้
- แอปต้องใช้อินเทอร์เน็ตเพื่อโหลดเว็บไซต์
- เว็บไซต์ภายนอกบางหน้าหรือการเข้าสู่ระบบอาจเปิดในเบราว์เซอร์แทน WebView ตามนโยบาย Android/เว็บไซต์
- ก่อนเผยแพร่จริง ควรทดสอบปุ่มโทร, LINE, เมนูสินค้า และการแสดงผลบนเครื่อง Android หลายขนาด
