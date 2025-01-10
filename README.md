# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu proje, dağıtık bir sistem üzerinde çalışan sunucuların birbirleriyle TCP bağlantıları kurarak bir hata toleransı seviyesinde birlikte çalışmasını sağlamayı amaçlamaktadır. Projenin amacı, abonelik işlemlerini yönetmek ve sistemin kapasite durumunu gözlemleyerek bir grafik arayüzüne aktarmaktır. Projenin uygulanmasında, belirlenen işlevler yerine getirilmiş ancak bazı bölümler eksik bırakılmıştır. Bu raporda, tamamlanan ve eksik kalan özellikler detaylı bir şekilde açıklanacaktır.

## ServerX.java Özellikleri

### Başlama Komutları
- **Server1.java**, **Server2.java** ve **Server3.java** TCP soket bağlantıları ile birbirine bağlanır. Bu bağlantılar, herhangi bir veri gönderimi olmadan kurulmuştur ve toplamda 6 adet bağlantı oluşturulmuştur.

### Hata Toleransı
- `fault_tolerance_level` parametresi kullanılarak sunucuların hata toleransı yönetilmiştir.
- Sunucular başlama komutlarına cevap verecek şekilde yapılandırılmıştır:

    - **Başarılı Başlatma:**
      - `demand = STRT`
      - `response = YEP`
    
    - **Başarısız Başlatma:**
      - `demand = STRT`
      - `response = NOP`

## plotter.py Özellikleri

Projede plotlama işlemi yapılamamış ve grafikler sunulamamıştır.

## admin.rb Özellikleri

### Başlatma Komutu Gönderme
- **admin.rb**, `dist_subs.conf` dosyasından hata toleransı seviyesini okumuş ve sunuculara "STRT" komutu göndererek başlatma işlemini gerçekleştirmiştir.

### Sunucu Dönütlerini İşleme
- Sunuculardan gelen "YEP" ve "NOP" yanıtlarına göre işlem yapılmıştır.

### Capacity Sorgulama
- Başarılı yanıt veren sunuculara 5 saniyede bir kapasite sorgusu yapılmıştır.

## Eksik Kalan Özellikler

### Subscriber Yönetimi
- Abonelik işlemleri için tasarlanan `Subscriber` sınıfı tam olarak uygulanamamıştır.
- İstemcilerden gelen abonelik ve abonelikten çıkma taleplerinin işlenmesi sağlanamamıştır.

### Grafiksel Gösterim (Plotter.py)
- Kapasite durumlarının grafiksel gösterimi yapılmamış, dolayısıyla plotlama kısmı eksik kalmıştır.

## Ekip Üyeleri

- **22060392** İLAYDA ÇETİN
- **22060379** YASEMİN DELİCAN
- **22060348** HATİCE SENA BAYAR
- **22060352** RABİA GÜLEÇ
